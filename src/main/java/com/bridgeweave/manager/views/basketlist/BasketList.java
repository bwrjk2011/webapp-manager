package com.bridgeweave.manager.views.basketlist;

import com.bridgeweave.manager.data.Basket;
import com.bridgeweave.manager.data.User;
import com.bridgeweave.manager.security.AuthenticatedUser;
import com.bridgeweave.manager.services.BasketService;
import com.bridgeweave.manager.views.MainLayout;
import com.bridgeweave.manager.views.basketdetail.BasketDetailView;
import com.bridgeweave.manager.views.modelportfoliodetail.ModelPortfolioDetailView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@PageTitle("BasketList")
@PermitAll
@Route(value = "basket-list", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class BasketList extends Composite<VerticalLayout> {
    private BasketService basketService;

    private AuthenticatedUser authenticatedUser;
    String ownerCompanyId;
    private AccessAnnotationChecker accessChecker;

    private Grid<Basket> grid;


    private Grid<Basket> getGrid(String ownerCompanyId){
        Grid<Basket> grid = new Grid<>(Basket.class, false);
        grid.addColumn(Basket::getBasketId).setHeader("Basket Id");
        grid.addColumn(Basket::getBasketName).setHeader("Basket Name");
        grid.addColumn(Basket::getManager).setHeader("Manager");
        grid.addColumn(Basket::getBenchMark).setHeader("Bench Mark");
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setWidth("100%");
        grid.getStyle().set("flex-grow", "0");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // Add a button column to the grid using TemplateRenderer

        grid.setItems(this.basketService.getBasketsByOwnerCompanyId(ownerCompanyId));

//        grid.addColumn(
//                new NativeButtonRenderer<>("Details",
//                        clickedItem -> {
//                            // remove the item
//                            String message = "Details for " + clickedItem.getId() + " " + clickedItem.getBasketName() + " " + clickedItem.getBenchMark();
//                            Notification.show(message, 3000, Notification.Position.MIDDLE);
//
//                            grid.getUI().ifPresent(ui -> ui.navigate(
//                                    BasketDetailView.class,clickedItem.getId().toString()));
//
//                        })
//        ).setHeader("Details");

        grid.addColumn(
                new NativeButtonRenderer<>("Portfolio",
                        clickedItem -> {
                            // remove the item
                            String message = "Portfolio " + clickedItem.getId() + " " + clickedItem.getBasketName() + " " + clickedItem.getBenchMark();
                            Notification.show(message, 3000, Notification.Position.MIDDLE);

                            grid.getUI().ifPresent(ui -> ui.navigate(
                                    ModelPortfolioDetailView.class,clickedItem.getId()));

                        })
        ).setHeader("Portfolio");

        grid.getColumns().forEach(column -> column.setSortable(true));

        return grid;
    }


    public BasketList(BasketService basketService,
                      AuthenticatedUser authenticatedUser,
                      AccessAnnotationChecker accessChecker

    ) {
        this.basketService = basketService;
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;


        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            System.out.print("User Name " + user.getName());
            ownerCompanyId = user.getOwnerCompanyId();
            System.out.print("--> User.getOwnerCompanyId() " + ownerCompanyId);

            //  Render the baskets
            grid = getGrid(ownerCompanyId);
            getContent().add(
                    new H3("Your baskets"),
                    grid);
        }

    }

}
