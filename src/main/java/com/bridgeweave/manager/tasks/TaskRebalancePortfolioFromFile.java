package com.bridgeweave.manager.tasks;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRebalancePortfolioFromFile  {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);



    public void startAsyncTask(String basketId, String filename) {
//        long basketId = 1L; // Replace with the actual basketId
//        String filename = "path/to/your/file.txt"; // Replace with the actual file path
        System.out.println("Starting Task");
        CompletableFuture.runAsync(() -> processFile(basketId, filename), executorService).thenRun(()->notifyUser("Done"));
        System.out.println("Completed Task");
    }

    private void notifyUser(String message) {
        System.out.println("Notify User Method has been invoked");
        UI.getCurrent().access(() -> {
            Notification.show(message, 3000, Notification.Position.MIDDLE);
        });
    }

    @Transactional
    private void processFile(String basketId, String filename) {
        try {
            // Simulate some processing time
            System.out.println("Sleeping");
            Thread.sleep(10000);
            System.out.println("Awake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                 Update JPA object (replace with your logic)
//                BasketEntity basket = entityManager.find(BasketEntity.class, basketId);
//                basket.addLine(line); // Assume BasketEntity has a method to add a line
//
//                // Update UI
//                UI.getCurrent().access(() -> {
//                    // Update UI components as needed
//                    // For example, notify the user about the progress
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
