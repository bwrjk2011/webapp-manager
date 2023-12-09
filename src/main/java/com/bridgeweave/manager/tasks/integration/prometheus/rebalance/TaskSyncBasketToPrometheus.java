package com.bridgeweave.manager.tasks.integration.prometheus.rebalance;

import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.tasks.integration.prometheus.rebalance.Constituent;
import com.bridgeweave.manager.tasks.integration.prometheus.rebalance.PortfolioRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSyncBasketToPrometheus {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Value("${app.integration.api.baseurl}")
    private static String baseURL;

    private static PortfolioRequest createPortfolioRequest(String portfolioId, ArrayList<ModelPortfolio> equities) {
        ArrayList<Constituent> constituents = new ArrayList<>();

        // Get today and the date in two weeks
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksLater = today.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = today.format(formatter);
        String twoWeeksLaterString = twoWeeksLater.format(formatter);

        PortfolioRequest request = new PortfolioRequest();
        request.setPortfolioId(portfolioId);
        request.setStartDate(todayString);
        request.setEndDate(twoWeeksLaterString);
        request.setStatus("active");
        request.setCreatedBy("Rich");

        for(int i=0;i<equities.size();i++){
            ModelPortfolio modelPortfolio = equities.get(i);

            Constituent constituent = new Constituent();
            constituent.setSymbol(modelPortfolio.getSymbol());
            constituent.setWeight(modelPortfolio.getAllocation());
            constituent.setIsin("entity");
            constituent.setStatus("active");
            constituents.add(constituent);
        }

        request.setConstituents(constituents);
        return request;
    }

    private static void postRequest(PortfolioRequest request, String baseURL) {

        String endPoint = baseURL + "/portfolio-catalogue/rebalance";
        System.out.println("BaseURL = " +baseURL );
        System.out.println("endPoint = " +endPoint );

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

    private void syncModelPortfolio(String portfolioId, ArrayList<ModelPortfolio> equities, String baseURL) {
        try {
            System.out.println("Starting syncModelPortfolio");
            PortfolioRequest portfolioRequest = createPortfolioRequest(portfolioId,equities);

            postRequest(portfolioRequest,baseURL);
            System.out.println("Completed syncModelPortfolio");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void startAsyncTask(String portfolioId, ArrayList<ModelPortfolio> equities, String baseURL) {
        System.out.println("Starting Task for portfolioId " + portfolioId);
        System.out.println("Equities size " + equities.size());

        CompletableFuture.runAsync(() -> syncModelPortfolio(portfolioId, equities, baseURL), executorService).thenRun(()->notifyUser("Done"));
        System.out.println("Completed Task");
    }

    private void notifyUser(String done) {
        System.out.println("Synced with Prometheus");
    }


    public TaskSyncBasketToPrometheus(){
    }

}
