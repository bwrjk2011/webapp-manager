package com.bridgeweave.manager.tasks.integration.prometheus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public class AppInvokeServices {

    public static void main(String args[]){
        System.out.println("HI");

        try {
            // new AppInvokeServices().sendit();
            PortfolioRequest portfolioRequest = createPortfolioRequest();
            postRequest(portfolioRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static PortfolioRequest createPortfolioRequest() {
        PortfolioRequest request = new PortfolioRequest();
        request.setPortfolioId("rjk002");
        request.setStartDate("2024-01-01");
        request.setEndDate("2024-01-30");
        request.setStatus("active");
        request.setCreatedBy("chetan");

        Constituent constituent1 = new Constituent();
        constituent1.setSymbol("KOTAKBANK");
        constituent1.setWeight(0.3f);
        constituent1.setIsin("entity");
        constituent1.setStatus("active");

        Constituent constituent2 = new Constituent();
        constituent2.setSymbol("MARUTI");
        constituent2.setWeight(0.3f);
        constituent2.setIsin("entity");
        constituent2.setStatus("active");

        Constituent constituent3 = new Constituent();
        constituent3.setSymbol("TATAMOTORS");
        constituent3.setWeight(0.3f);
        constituent3.setIsin("entity");
        constituent3.setStatus("active");

        Constituent constituent4 = new Constituent();
        constituent4.setSymbol("WIPRO");
        constituent4.setWeight(0.1f);
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
//            objectMapper.enable(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);

            String requestBody = null;
            try {
                requestBody = objectMapper.writeValueAsString(request);
                System.out.println(requestBody);
                HttpResponse<JsonNode> response = Unirest.post(endPoint)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .asJson();

//
//                String jsonString = objectMapper.writeValueAsString(request);
//                jsonString = jsonString.replace("\n", "\\r\\n");


//                HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(endPoint)
//                        .header("Content-Type", "application/json")
//                        .body(jsonString)
//                        .asJson();
//

                System.out.println("Response status: " + response.getStatus());
                System.out.println("Response body: " + response.getBody().toString());
            } catch (JsonProcessingException | UnirestException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
