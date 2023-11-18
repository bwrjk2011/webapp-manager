package com.bridgeweave.manager.views.test;

import com.bridgeweave.manager.data.Hobby;
import com.bridgeweave.manager.services.HobbyService;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Optional;


@AnonymousAllowed
@PageTitle("hobbysave")
@Route(value = "hobbysave", layout = MainLayout.class)

public class HobbySave extends VerticalLayout implements HasUrlParameter<Long> {

    private final HobbyService hobbyService;
    private Hobby hobby = new Hobby();
    Binder<Hobby> binder = new Binder<>(Hobby.class);
    private TextField textFieldName;
    private TextField textFieldDangerLevel;
    private Button buttonClear;
    private Button buttonRandomise;
    private Button buttonSave;
    private VerticalLayout populateForm() {
        VerticalLayout layout = new VerticalLayout();
        textFieldName = new TextField();
        textFieldDangerLevel = new TextField();

        binder.bind(textFieldName,Hobby::getName,Hobby::setName);
        binder.bind(textFieldDangerLevel,Hobby::getDangerLevel,Hobby::setDangerLevel);

        buttonClear = new Button("Clear");
        buttonRandomise = new Button("Randomise");
        buttonSave = new Button("Save");

        buttonClear.addClickListener(buttonClickEvent -> {
            textFieldName.setValue("");
            textFieldDangerLevel.setValue("");
        });

        buttonSave.addClickListener(buttonClickEvent -> {
            try {
                binder.writeBean(hobby);
                Hobby update = hobbyService.update(hobby);
//                resetBean();
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }


            Notification notification = new Notification("Saved", 3000, Notification.Position.TOP_START);
            notification.open();
        });

        layout.add(
                    textFieldName,
                    textFieldDangerLevel,
                    buttonClear,
                    buttonRandomise,
                    buttonSave

                );

        return layout;
    }


    private void resetBean(){

        Long id = 1l;
        Optional<Hobby> hobby1 = hobbyService.get(id);

        hobby = hobby1.orElseGet(()->hobby);
        binder.readBean(hobby);

    }
    public HobbySave(HobbyService hobbyService){
        this.hobbyService = hobbyService;

        VerticalLayout form = (VerticalLayout) populateForm();
        add(form);
//        resetBean();
    }



    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        try {
            Optional<Hobby> hobby1 = hobbyService.get(id);
            hobby = hobby1.orElseGet(() -> hobby);
        } catch (Exception e){
            System.out.println(e);
        } finally{
            binder.readBean(hobby);

        }
    }
}
