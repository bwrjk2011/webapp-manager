package com.bridgeweave.manager.tasks;

import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.data.UserNotifications;
import com.bridgeweave.manager.services.ModelPortfolioService;
import com.bridgeweave.manager.services.UserNotificationService;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.csv.CSVReader;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRebalancePortfolioFromFile  {
    private final UserNotificationService userNotificationService;
    private final ModelPortfolioService modelPortfolioService;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);


    private void notifyUser(String message) {
        System.out.println("Notify User Method has been invoked");
        UI.getCurrent().access(() -> {
            Notification.show(message, 3000, Notification.Position.MIDDLE);
        });
    }

    @Transactional
    private void processFile(Long userId, Long basketId, String filename) {
        try {
            // Simulate some processing time
            System.out.println("Sleeping");

            //  Step1.  Remove existing tickers - working
            System.out.println("About to Delete Tickets for basketId " + basketId);
            modelPortfolioService.deleteTicketsForBasketIt(basketId);

            //  Step2.  Process File
            System.out.println("About to Read File and process ticker Ids for basketId " + basketId);
            try (CSVReader csvReader = new CSVReader(new FileReader(filename))) {
                ICommonsList<ICommonsList<String>> rows = csvReader.readAll();

                // Assuming the first row is a header, so start from index 1
                for (int i = 0; i < rows.size(); i++) {
                    ICommonsList<String> row = rows.get(i);

                    // Assuming row[0] is ticker id and row[1] is the allocation as a string
                    String tickerId = row.get(0);
                    String companyName = row.get(1);
                    Float allocation = Float.parseFloat(row.get(2));

                    // Create ModelPortfolio object
                    ModelPortfolio modelPortfolio = new ModelPortfolio();
                    modelPortfolio.setBid(basketId);
                    modelPortfolio.setTicker(tickerId);
                    modelPortfolio.setName(companyName);
                    modelPortfolio.setAllocation(allocation);

                    modelPortfolioService.update(modelPortfolio);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



            //  Step3. Send Notification with Results
            UserNotifications un = new UserNotifications(String.valueOf(userId),"someMessage");
            userNotificationService.update(un);
            System.out.println("Check Notification DB!!!");
            System.out.println("Awake");
        } catch (Exception e) {
            e.printStackTrace();
        }


        //        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

    }

    public void startAsyncTask(Long userId, Long basketId, String filename) {
//        long basketId = 1L; // Replace with the actual basketId
//        String filename = "path/to/your/file.txt"; // Replace with the actual file path
        System.out.println("Starting Task for user " + userId);
        System.out.println("BasketId  " + basketId);
        System.out.println("filename  " + filename);
        CompletableFuture.runAsync(() -> processFile(userId, basketId, filename), executorService).thenRun(()->notifyUser("Done"));
        System.out.println("Completed Task");
    }



    public TaskRebalancePortfolioFromFile(UserNotificationService userNotificationService,
                                          ModelPortfolioService modelPortfolioService){
        this.userNotificationService = userNotificationService;
        this.modelPortfolioService=modelPortfolioService;
    }

}
