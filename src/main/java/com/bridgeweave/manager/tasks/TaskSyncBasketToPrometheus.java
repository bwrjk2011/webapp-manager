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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSyncBasketToPrometheus {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static PortfolioRequest createPortfolioRequest(String portfolioId, ArrayList<ModelPortfolio> equities) {
        ArrayList<Constituent> constituents = new ArrayList<>();

        PortfolioRequest request = new PortfolioRequest();
        request.setPortfolioId(portfolioId);
        request.setStartDate("2024-01-01");
        request.setEndDate("2024-01-30");
        request.setStatus("active");
        request.setCreatedBy("Rich");

        for(int i=0;i<equities.size();i++){
            ModelPortfolio modelPortfolio = equities.get(i);

            Constituent constituent = new Constituent();
            constituent.setSymbol(modelPortfolio.getSymbol());
            constituent.setWeight(0.2d);
            constituent.setIsin("entity");
            constituent.setStatus("active");
            constituents.add(constituent);
        }

        request.setConstituents(constituents);
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

    private void syncModelPortfolio(String portfolioId, ArrayList<ModelPortfolio> equities) {
        try {
            System.out.println("Starting syncModelPortfolio");
            PortfolioRequest portfolioRequest = createPortfolioRequest(portfolioId,equities);
            postRequest(portfolioRequest);
            System.out.println("Completed syncModelPortfolio");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void startAsyncTask(String portfolioId, ArrayList<ModelPortfolio> equities) {
        System.out.println("Starting Task for portfolioId " + portfolioId);
        System.out.println("Equities size " + equities.size());

        CompletableFuture.runAsync(() -> syncModelPortfolio(portfolioId, equities), executorService).thenRun(()->notifyUser("Done"));
        System.out.println("Completed Task");
    }

    private void notifyUser(String done) {
        System.out.println("Synced with Prometheus");
    }


    public TaskSyncBasketToPrometheus(){
    }

}
