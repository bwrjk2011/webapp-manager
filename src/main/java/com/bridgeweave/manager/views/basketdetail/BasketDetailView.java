package com.bridgeweave.manager.views.basketdetail;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.bridgeweave.manager.components.pricefield.PriceField;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;


@PageTitle("Basket Detail")
@Route(value = "basket-detail", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class BasketDetailView extends Composite<VerticalLayout> implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        try{
            System.out.println(s);
        } catch (Exception e) {
            System.out.print(e);
        }

    }


    public BasketDetailView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField textField3 = new TextField();
        EmailField emailField = new EmailField();
        TextField textField4 = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        PriceField price = new PriceField();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        ComboBox comboBox3 = new ComboBox();
        ComboBox comboBox4 = new ComboBox();
        ComboBox comboBox5 = new ComboBox();
        ComboBox comboBox6 = new ComboBox();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        TextField textField5 = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Basket Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("First Name");
        textField2.setLabel("Last Name");
        datePicker.setLabel("Birthday");
        textField3.setLabel("Phone Number");
        emailField.setLabel("Email");
        textField4.setLabel("Occupation");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        price.setLabel("Price");
        price.setWidth("min-content");
        comboBox.setLabel("Combo Box");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setLabel("Combo Box");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        comboBox3.setLabel("Combo Box");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData(comboBox3);
        comboBox4.setLabel("Combo Box");
        comboBox4.setWidth("min-content");
        setComboBoxSampleData(comboBox4);
        comboBox5.setLabel("Combo Box");
        comboBox5.setWidth("min-content");
        setComboBoxSampleData(comboBox5);
        comboBox6.setLabel("Combo Box");
        comboBox6.setWidth("min-content");
        setComboBoxSampleData(comboBox6);
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        textField5.setLabel("Text field");
        textField5.setWidth("min-content");
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(datePicker);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        formLayout2Col.add(textField4);
        layoutColumn2.add(layoutRow);
        layoutRow.add(price);
        layoutRow.add(comboBox);
        layoutRow.add(comboBox2);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(comboBox3);
        layoutRow2.add(comboBox4);
        layoutRow2.add(comboBox5);
        layoutRow2.add(comboBox6);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(textField5);
        layoutColumn2.add(layoutRow4);
        layoutRow4.add(buttonPrimary);
        layoutRow4.add(buttonSecondary);
    }


    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
