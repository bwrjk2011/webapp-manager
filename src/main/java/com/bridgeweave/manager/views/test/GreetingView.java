package com.bridgeweave.manager.views.test;

import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("greet")
@Route(value = "greet", layout = MainLayout.class)
public class GreetingView extends VerticalLayout implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        System.out.println(String.format("Hello, %s!", parameter));
    }
}
