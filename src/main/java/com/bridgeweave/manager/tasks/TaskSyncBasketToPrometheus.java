package com.bridgeweave.manager.tasks;

import com.bridgeweave.manager.data.Equities;
import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.data.UserNotifications;
import com.bridgeweave.manager.services.EquitiesService;
import com.bridgeweave.manager.services.ModelPortfolioService;
import com.bridgeweave.manager.services.UserNotificationService;
import com.bridgeweave.manager.tasks.integration.prometheus.Constituent;
import com.bridgeweave.manager.tasks.integration.prometheus.PortfolioRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.csv.CSVReader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSyncBasketToPrometheus {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static PortfolioRequest createPortfolioRequest() {
        PortfolioRequest request = new PortfolioRequest();
        request.setPortfolioId("rjk002");
        request.setStartDate("2024-01-01");
        request.setEndDate("2024-01-30");
        request.setStatus("active");
        request.setCreatedBy("Rich");

        Constituent constituent1 = new Constituent();
        constituent1.setSymbol("SUNPHARMA");
        constituent1.setWeight(0.3);
        constituent1.setIsin("entity");
        constituent1.setStatus("active");

        Constituent constituent2 = new Constituent();
        constituent2.setSymbol("TCS");
        constituent2.setWeight(0.3);
        constituent2.setIsin("entity");
        constituent2.setStatus("active");

        Constituent constituent3 = new Constituent();
        constituent3.setSymbol("TATACONSUM");
        constituent3.setWeight(0.3);
        constituent3.setIsin("entity");
        constituent3.setStatus("active");

        Constituent constituent4 = new Constituent();
        constituent4.setSymbol("TATAMOTORS");
        constituent4.setWeight(0.1);
        constituent4.setIsin("entity");
        constituent4.setStatus("active");

        request.setConstituents(List.of(constituent1, constituent2,constituent3,constituent4 ));

        return request;
    }

    private static void postRequest(PortfolioRequest request) {
        String endPoint = "https://portfolio-catalogue-service-dev.bridgeweave.net:4439/portfolio-catalogue/rebalance";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable indentation and line breaks

            String requestBody = null;
            try {
                requestBody = objectMapper.writeValueAsString(request);
                System.out.println(requestBody);
                HttpResponse<JsonNode> response = Unirest.post(endPoint)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .asJson();

                System.out.println("Response status: " + response.getStatus());
                System.out.println("Response body: " + response.getBody().toString());
            } catch (JsonProcessingException | UnirestException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void syncModelPortfolio(Long basketId, ArrayList<ModelPortfolio> equities) {
        try {
            System.out.println("Starting syncModelPortfolio");
            PortfolioRequest portfolioRequest = createPortfolioRequest();
            postRequest(portfolioRequest);
            System.out.println("Completed syncModelPortfolio");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void startAsyncTask(Long basketId, ArrayList<ModelPortfolio> equities) {
        System.out.println("Starting Task for basketId " + basketId);
        System.out.println("Equities size " + equities.size());

        CompletableFuture.runAsync(() -> syncModelPortfolio(basketId, equities), executorService).thenRun(()->notifyUser("Done"));
        System.out.println("Completed Task");
    }

    private void notifyUser(String done) {
        System.out.println("Synced with Prometheus");
    }


    public TaskSyncBasketToPrometheus(){
    }

}
