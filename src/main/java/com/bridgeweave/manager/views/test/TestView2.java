package com.bridgeweave.manager.views.test;

import com.bridgeweave.manager.data.Hobby;
import com.bridgeweave.manager.data.SamplePerson;
import com.bridgeweave.manager.data.User;
import com.bridgeweave.manager.security.AuthenticatedUser;
import com.bridgeweave.manager.services.HobbyService;
import com.bridgeweave.manager.services.SamplePersonService;
import com.bridgeweave.manager.views.MainLayout;
import com.bridgeweave.manager.views.basketdetail.BasketDetailView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoIcon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AnonymousAllowed
@PageTitle("bbbb")
@Route(value = "bbbb", layout = MainLayout.class)
public class TestView2 extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;
    private HobbyService hobbyService;
    private Grid<Hobby> grid;
    private Button button;

    private Hobby selectHobby;

    public TestView2(AuthenticatedUser authenticatedUser,
                     HobbyService hobbyService) {
        this.hobbyService = hobbyService;
        this.authenticatedUser = authenticatedUser;

        grid = setupGrid();
        button = setupButton();
        User u = getUserInfo();

        Icon lumoIcon = LumoIcon.PHOTO.create();
        lumoIcon.addClickListener(event->{System.out.println("Clicked");});

        Icon vaadinIcon = VaadinIcon.PHONE.create();
        add(lumoIcon, vaadinIcon);

        Button buttonNav = new Button("Navigate to company");
        buttonNav.addClickListener(e ->
                buttonNav.getUI().ifPresent(ui ->
                        ui.navigate("model-portfolio-detail"))
        );
        add(buttonNav);

        Button editButton = new Button("Edit user details");
//        editButton.addClickListener(e ->
//                editButton.getUI().ifPresent(
//                        ui -> ui.navigate(GreetingView.class,selectHobby.getName())
//                )
//        );


        editButton.addClickListener(e -> {
            if (selectHobby != null) {
                editButton.getUI().ifPresent(ui ->
                        ui.navigate(GreetingView.class, selectHobby.getName())
                );
            }
        });


        add(editButton);



        if (u!=null){
            add(
                    new H1(u.getUsername() + " " + u.getCompany())
            );
        }
        add(
                new H1("Portfolio Definition2"),
                grid,
                button


        );
    }

    private User getUserInfo() {
        User user = null;
        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            user = maybeUser.get();
        }

        return user;
    }

    private Button setupButton(){
        button = new Button("Button");
        button.addClickListener(clickEvent -> {
            String message = "Hi there";
            Notification notification = new Notification(message, 3000, Notification.Position.TOP_START);
            notification.open();
        });
        return button;

    }

    private Grid<Hobby> setupGrid() {
        Grid<Hobby> grid = new Grid<>(Hobby.class, false);
        grid.addColumn(Hobby::getName).setHeader("Name");
        grid.addColumn(Hobby::getDangerLevel).setHeader("Danger Levele");

        List<Hobby> hobbies = hobbyService.getAll();
        grid.setItems(hobbies);
        grid.addItemClickListener(event->{
            selectHobby = event.getItem();
        });
        return grid;
    }


}