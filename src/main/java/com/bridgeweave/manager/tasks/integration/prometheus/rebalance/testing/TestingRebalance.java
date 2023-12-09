package com.bridgeweave.manager.tasks.integration.prometheus.rebalance.testing;

import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.tasks.integration.prometheus.rebalance.TaskSyncBasketToPrometheus;

import java.util.ArrayList;

public class TestingRebalance {



    public static void main(String args[]){
        System.out.println("Testing");

        String portfolioId = new String("ABC123A3");
        ArrayList<ModelPortfolio> modelPolfolioConstituents = new ArrayList<>();

        ModelPortfolio modelPortfolio = new ModelPortfolio();
        modelPortfolio.setOwnerCompanyId("5000");
        modelPortfolio.setOwnerCompanyName("InvestorAI");
        modelPortfolio.setBid(100l);
        modelPortfolio.setSymbol("bbb");
        modelPortfolio.setName("BBB Ltd.");
        modelPortfolio.setAllocation(1.0);


        modelPolfolioConstituents.add(modelPortfolio);
        String baseURL = "https://portfolio-catalogue-service-dev.bridgeweave.net:4439";
        new TaskSyncBasketToPrometheus().startAsyncTask(portfolioId, modelPolfolioConstituents,baseURL);

    }
}
