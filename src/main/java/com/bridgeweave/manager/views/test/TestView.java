package com.bridgeweave.manager.views.test;

import com.bridgeweave.manager.data.SamplePerson;
import com.bridgeweave.manager.data.SamplePersonRepository;
import com.bridgeweave.manager.services.SamplePersonService;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AnonymousAllowed
@PageTitle("aaaa")
@Route(value = "aaaa", layout = MainLayout.class)
public class TestView extends VerticalLayout {


    private SamplePersonService samplePersonService;
    ComboBox<String> comboBox = new ComboBox<>("Country");
    VerticalLayout todosList = new VerticalLayout();



    public TestView(SamplePersonService samplePersonService) {


        comboBox.addValueChangeListener(event->{
            todosList.add(event.getValue().toString());
        });

        this.samplePersonService = samplePersonService;
        System.out.println(samplePersonService.count());
        List<SamplePerson> all = samplePersonService.getAll();
        List<String> allNames = new ArrayList<>();
        for (SamplePerson person : all) {
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());

            System.out.println("Last Name: " + person.getLastName());
            allNames.add(person.getFirstName());
        }
        comboBox.setItems(allNames);


        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());

            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);


        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);

            // Define the directory where you want to save the file
            String uploadDirectory = "./uploads/";

            // Create the directory if it doesn't exist
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file
            File file = new File(uploadDirectory + fileName);

            try {
                // Create an output stream to write the file
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                // Copy the content from the input stream to the output stream
                byte[] bufferArray = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(bufferArray)) != -1) {
                    fileOutputStream.write(bufferArray, 0, bytesRead);
                }

                // Close streams
                inputStream.close();
                fileOutputStream.close();

                // Do something with the file data
                // processFile(file, fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });



        add(
                new H1("Portfolio Definition"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                ),
                upload,
                comboBox
        );
    }

}
