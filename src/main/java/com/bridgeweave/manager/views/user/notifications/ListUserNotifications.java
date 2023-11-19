package com.bridgeweave.manager.views.user.notifications;

import com.bridgeweave.manager.data.User;
import com.bridgeweave.manager.data.UserNotifications;
import com.bridgeweave.manager.security.AuthenticatedUser;
import com.bridgeweave.manager.services.UserNotificationService;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;
import java.util.Optional;

@PageTitle("User Notifications")
@Route(value = "notifications", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class ListUserNotifications extends VerticalLayout {

    private final UserNotificationService userNotificationService;
    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;
    public ListUserNotifications(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, UserNotificationService userNotificationService){
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        this.userNotificationService = userNotificationService;

        Grid<UserNotifications> grid = new Grid<>(UserNotifications.class, true);
//        grid.addColumn(Person::getFirstName).setHeader("First name");
//        grid.addColumn(Person::getLastName).setHeader("Last name");
//        grid.addColumn(Person::getEmail).setHeader("Email");
//        grid.addColumn(Person::getProfession).setHeader("Profession");


        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            Long userId = user.getId();
            System.out.println("Getting user notifications for user id " + userId);
            List<UserNotifications> userNotifications = userNotificationService.getAllUnSeenNotifications(userId);

            grid.setItems(userNotifications);
            grid.setAllRowsVisible(true);

            userNotificationService.setAllNotificationsAsSeenForUser(userId);
        }

        add(grid);
    }



}
