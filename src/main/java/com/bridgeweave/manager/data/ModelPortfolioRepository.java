package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ModelPortfolioRepository
        extends
            JpaRepository<ModelPortfolio, Long>,
            JpaSpecificationExecutor<ModelPortfolio> {

            List<ModelPortfolio> findByBid(String bid);

}
