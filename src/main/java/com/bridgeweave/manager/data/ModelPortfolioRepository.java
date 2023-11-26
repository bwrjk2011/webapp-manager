package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModelPortfolioRepository
        extends
            JpaRepository<ModelPortfolio, Long>,
            JpaSpecificationExecutor<ModelPortfolio> {

            List<ModelPortfolio> findByBid(Long bid);

    @Modifying
    @Transactional
    @Query("Delete ModelPortfolio mp WHERE mp.bid = :bid")
    public void deleteTickersForBasketId(@Param("bid") Long bid);


    @Query("SELECT mp FROM ModelPortfolio mp WHERE mp.bid = :bid and mp.hasError=True")
    public List<ModelPortfolio> getEquityErrorsByBasketId(@Param("bid") Long bid);

}
