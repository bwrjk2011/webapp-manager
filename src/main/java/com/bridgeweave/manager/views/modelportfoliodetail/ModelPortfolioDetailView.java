package com.bridgeweave.manager.views.modelportfoliodetail;

import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.data.SamplePerson;
import com.bridgeweave.manager.services.ModelPortfolioService;
import com.bridgeweave.manager.services.SamplePersonService;
import com.bridgeweave.manager.tasks.TaskRebalancePortfolioFromFile;
import com.bridgeweave.manager.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

@PageTitle("Model Portfolio Detail")
@Route(value = "model-portfolio-detail", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class ModelPortfolioDetailView extends Div implements HasUrlParameter<String>  {

    private String basketId;


    private Grid<ModelPortfolio> grid;
    private Filters filters;
    private final ModelPortfolioService modelPortfolioService;

    private Upload upload;
    ConfirmDialog confirmDialog;
    @Override
    public void setParameter(BeforeEvent beforeEvent, String event) {
        basketId = event;
        System.out.println(event);
        if (basketId != null){
            grid.setItems(modelPortfolioService.getByBid(basketId));
        }
    }


    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (filters.getClassNames().contains("visible")) {
                filters.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                filters.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
    }


    public static class Filters extends Div implements Specification<SamplePerson> {

        private final TextField name = new TextField("Name");
        private final TextField phone = new TextField("Phone");
        private final DatePicker startDate = new DatePicker("Date of Birth");
        private final DatePicker endDate = new DatePicker();
        private final MultiSelectComboBox<String> occupations = new MultiSelectComboBox<>("Occupation");
        private final CheckboxGroup<String> roles = new CheckboxGroup<>("Role");

        public Filters(Runnable onSearch) {

            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);
            name.setPlaceholder("First or last name");

            occupations.setItems("Insurance Clerk", "Mortarman", "Beer Coil Cleaner", "Scale Attendant");

            roles.setItems("Worker", "Supervisor", "Manager", "External");
            roles.addClassName("double-width");

            // Action buttons
            Button resetBtn = new Button("Reset");
            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            resetBtn.addClickListener(e -> {
                name.clear();
                phone.clear();
                startDate.clear();
                endDate.clear();
                occupations.clear();
                roles.clear();
                onSearch.run();
            });
            Button searchBtn = new Button("Search");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> onSearch.run());

            Div actions = new Div(resetBtn, searchBtn);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("actions");

            add(name, phone, createDateRangeFilter(), occupations, roles, actions);
        }

        private Component createDateRangeFilter() {
            startDate.setPlaceholder("From");

            endDate.setPlaceholder("To");

            // For screen readers
            startDate.setAriaLabel("From date");
            endDate.setAriaLabel("To date");

            FlexLayout dateRangeComponent = new FlexLayout(startDate, new Text(" – "), endDate);
            dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
            dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);

            return dateRangeComponent;
        }

        @Override
        public Predicate toPredicate(Root<SamplePerson> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if (!name.isEmpty()) {
                String lowerCaseFilter = name.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
            }
            if (!phone.isEmpty()) {
                String databaseColumn = "phone";
                String ignore = "- ()";

                String lowerCaseFilter = ignoreCharacters(ignore, phone.getValue().toLowerCase());
                Predicate phoneMatch = criteriaBuilder.like(
                        ignoreCharacters(ignore, criteriaBuilder, criteriaBuilder.lower(root.get(databaseColumn))),
                        "%" + lowerCaseFilter + "%");
                predicates.add(phoneMatch);

            }
            if (startDate.getValue() != null) {
                String databaseColumn = "dateOfBirth";
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(databaseColumn),
                        criteriaBuilder.literal(startDate.getValue())));
            }
            if (endDate.getValue() != null) {
                String databaseColumn = "dateOfBirth";
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.literal(endDate.getValue()),
                        root.get(databaseColumn)));
            }
            if (!occupations.isEmpty()) {
                String databaseColumn = "occupation";
                List<Predicate> occupationPredicates = new ArrayList<>();
                for (String occupation : occupations.getValue()) {
                    occupationPredicates
                            .add(criteriaBuilder.equal(criteriaBuilder.literal(occupation), root.get(databaseColumn)));
                }
                predicates.add(criteriaBuilder.or(occupationPredicates.toArray(Predicate[]::new)));
            }
            if (!roles.isEmpty()) {
                String databaseColumn = "role";
                List<Predicate> rolePredicates = new ArrayList<>();
                for (String role : roles.getValue()) {
                    rolePredicates.add(criteriaBuilder.equal(criteriaBuilder.literal(role), root.get(databaseColumn)));
                }
                predicates.add(criteriaBuilder.or(rolePredicates.toArray(Predicate[]::new)));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }

        private String ignoreCharacters(String characters, String in) {
            String result = in;
            for (int i = 0; i < characters.length(); i++) {
                result = result.replace("" + characters.charAt(i), "");
            }
            return result;
        }

        private Expression<String> ignoreCharacters(String characters, CriteriaBuilder criteriaBuilder,
                Expression<String> inExpression) {
            Expression<String> expression = inExpression;
            for (int i = 0; i < characters.length(); i++) {
                expression = criteriaBuilder.function("replace", String.class, expression,
                        criteriaBuilder.literal(characters.charAt(i)), criteriaBuilder.literal(""));
            }
            return expression;
        }

    }

    private Component createGrid() {
        grid = new Grid<>(ModelPortfolio.class, false);
        grid.addColumn("bid").setAutoWidth(true);
        grid.addColumn("ticker").setAutoWidth(true);
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("allocation").setAutoWidth(true);
        grid.setAllRowsVisible(true);
//        grid.setItems(query -> samplePersonService.list(
//                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)),
//                filters).stream());
//
        if (basketId != null){
            grid.setItems(modelPortfolioService.getByBid(basketId));
        }

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }


    private void openConfirmationDialog() {
         confirmDialog = new ConfirmDialog("Confirmation",
                "Do you really want to proceed with the rebalance?",
                "Yes", // Text for the confirm button
                event -> {
                    // User clicked "Yes", perform the action
                    new TaskRebalancePortfolioFromFile().startAsyncTask("basketId","somefile.csv");

                    confirmDialog.close();
                },
                "No", // Text for the cancel button
                event -> {
                    // User clicked "No" or closed the dialog
                    confirmDialog.close();
                });

        confirmDialog.open();
    }

    private Component createUpload() {
        VerticalLayout layoutUpload = new VerticalLayout();

        //        Upload Component
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        upload = new Upload(buffer);

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

                // Send Notification
                Notification.show("Upload Success");

                openConfirmationDialog();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        //        Header

        Span title = new Span(new H4("Overview"));
        Span text1 = new Span("You are able to rebalance the model portfolio via the use of json files");
        Span text2 = new Span("On uploading the portfolio, you are asked to confirm and the portfolio is rebalanced immeditely");

        VerticalLayout content = new VerticalLayout(title, text1, text2,upload);
        content.setSpacing(false);
        content.setPadding(false);

        Details details = new Details("Rebalance Model Portfolio", content);
        details.setOpened(false);

        //        Return the components to the UI
        layoutUpload.add(details);
        return layoutUpload;
    }
    private void refreshGrid() {
        grid.getDataProvider().refreshAll();

    }

    public ModelPortfolioDetailView(ModelPortfolioService modelPortfolioService)  {
        this.modelPortfolioService = modelPortfolioService;
        System.out.println("Hi");
        System.out.println(basketId);

        setSizeFull();
        addClassNames("model-portfolio-detail-view");

        filters = new Filters(() -> refreshGrid());
//        VerticalLayout layout = new VerticalLayout(createMobileFilters(), filters, createGrid());
        VerticalLayout layout = new VerticalLayout(createGrid(),createUpload());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }




}
