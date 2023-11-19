package com.bridgeweave.manager.views.user.notifications;

import com.bridgeweave.manager.data.UserNotifications;
import com.bridgeweave.manager.services.UserNotificationService;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;

@PageTitle("User Notifications")
@Route(value = "notifications", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class ListUserNotifications extends VerticalLayout {

    private final UserNotificationService userNotificationService;

    public ListUserNotifications(UserNotificationService userNotificationService){
        this.userNotificationService = userNotificationService;


        Grid<UserNotifications> grid = new Grid<>(UserNotifications.class, true);
//        grid.addColumn(Person::getFirstName).setHeader("First name");
//        grid.addColumn(Person::getLastName).setHeader("Last name");
//        grid.addColumn(Person::getEmail).setHeader("Email");
//        grid.addColumn(Person::getProfession).setHeader("Profession");

        List<UserNotifications> userNotifications = userNotificationService.getAllUnSeenNotifications();
        grid.setItems(userNotifications);


        add(grid);
    }



}
