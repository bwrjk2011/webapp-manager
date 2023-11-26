package com.bridgeweave.manager.views;

import com.bridgeweave.manager.data.User;
import com.bridgeweave.manager.data.UserNotifications;
import com.bridgeweave.manager.security.AuthenticatedUser;
import com.bridgeweave.manager.services.UserNotificationService;
import com.bridgeweave.manager.views.about.AboutView;
import com.bridgeweave.manager.views.basketdetail.BasketDetailView;
import com.bridgeweave.manager.views.basketdetail.Baskets2View;
import com.bridgeweave.manager.views.basketlist.BasketList;
import com.bridgeweave.manager.views.helloworld.HelloWorldView;
import com.bridgeweave.manager.views.home.HomeView;
import com.bridgeweave.manager.views.modelportfoliodetail.ModelPortfolioDetailView;
import com.bridgeweave.manager.views.user.notifications.ListUserNotifications;
import com.bridgeweave.manager.views.wrapper.WrapperView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;
    private final UserNotificationService userNotificationService;

    private User user;

    private int notificationCount = 0; // Replace with the actual count of notifications
    private Span badge;


    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }


    private int fetchNotificationCount() {
        int numberOfMessages = 0;
        if (user != null){
            Long userId = user.getId();
            numberOfMessages = userNotificationService.getAllUnSeenNotifications(userId).size();
        } else {
            Random random = new Random();
            numberOfMessages =random.nextInt(11);
        }
        return numberOfMessages;
    }

    private void updateNotifications() {
        // Replace this with your actual logic to get the number of notifications
        // For example, you might fetch the count from a data source
        int newNotificationCount = fetchNotificationCount();

        // Update the UI with the new count
        getUI().ifPresent(ui -> ui.access(() -> {
            notificationCount = newNotificationCount;
            badge.setText(String.valueOf(notificationCount));
        }));
    }

    public MainLayout(UserNotificationService userNotificationService,
                      AuthenticatedUser authenticatedUser,
                      AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        this.userNotificationService = userNotificationService;

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            user = maybeUser.get();
        }


        addToNavbar(createHeaderContent());
        setDrawerOpened(false);

        // Schedule the async task to update notifications every 10 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateNotifications, 0, 3, TimeUnit.SECONDS);
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("Manager");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

//            Hack job rjk
            Integer notificationCount = 3;
            badge = new Span(String.valueOf(notificationCount));
            badge.getElement().getThemeList().add("badge");
            badge.addClickListener(spanClickEvent -> {
                badge.getUI().ifPresent(ui -> ui.navigate(
                        ListUserNotifications.class));
            });

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(badge);
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
//            userName.getSubMenu().addItem("Admin", e -> {
//                authenticatedUser.logout();
//            });
            userName.getSubMenu().addItem("Notifications", e -> {
                userName.getUI().ifPresent(ui -> ui.navigate(
                        ListUserNotifications.class));
            });
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });
            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            if (accessChecker.hasAccess(menuItem.getView())) {
                list.add(menuItem);
            }

        }

        header.add(layout, nav);
        return header;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Home", LineAwesomeIcon.CHART_AREA_SOLID.create(), HomeView.class), //
                new MenuItemInfo("Baskets",LineAwesomeIcon.BABY_CARRIAGE_SOLID.create(),BasketList.class),
//                new MenuItemInfo("User Notifications",LineAwesomeIcon.BABY_CARRIAGE_SOLID.create(), ListUserNotifications.class),

//                new MenuItemInfo("Baskets2", LineAwesomeIcon.BRIEFCASE_SOLID.create(), Baskets2View.class), //
//
//                new MenuItemInfo("Basket Detail", LineAwesomeIcon.USER.create(), BasketDetailView.class), //
//
//                new MenuItemInfo("Wrapper", LineAwesomeIcon.PENCIL_RULER_SOLID.create(), WrapperView.class), //
//
//                new MenuItemInfo("Model Portfolio Detail", LineAwesomeIcon.FILTER_SOLID.create(),
//                        ModelPortfolioDetailView.class), //

//                new MenuItemInfo("Hello World", LineAwesomeIcon.GLOBE_SOLID.create(), HelloWorldView.class), //
//                new MenuItemInfo("About", LineAwesomeIcon.FILE.create(), AboutView.class), //

        };
    }

}
