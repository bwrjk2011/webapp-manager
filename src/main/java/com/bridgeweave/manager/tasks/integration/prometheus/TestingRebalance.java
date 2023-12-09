package com.bridgeweave.manager.tasks.integration.prometheus;

import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.tasks.TaskSyncBasketToPrometheus;

import java.util.ArrayList;

public class TestingRebalance {



    public static void main(String args[]){
        System.out.println("Testing");

        String portfolioId = "ABC123A3";
        ArrayList<ModelPortfolio> modelPolfolioConstituents = new ArrayList<>();

        ModelPortfolio modelPortfolio = new ModelPortfolio();
        modelPortfolio.setOwnerCompanyId("5000");
        modelPortfolio.setOwnerCompanyName("InvestorAI");
        modelPortfolio.setBid(100l);
        modelPortfolio.setSymbol("bbb");
        modelPortfolio.setName("BBB Ltd.");
        modelPortfolio.setAllocation(1f);


        modelPolfolioConstituents.add(modelPortfolio);

        new TaskSyncBasketToPrometheus().startAsyncTask(portfolioId, modelPolfolioConstituents);


    }
}
