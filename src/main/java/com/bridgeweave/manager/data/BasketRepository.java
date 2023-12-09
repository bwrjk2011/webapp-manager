package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface BasketRepository
        extends
            JpaRepository<Basket, Long>,
            JpaSpecificationExecutor<Basket> {

    @Query("SELECT mp FROM ModelPortfolio mp WHERE mp.bid = :bid and mp.hasError=True")
    public List<ModelPortfolio> getEquityErrorsByBasketId(@Param("bid") Long bid);



    @Query("SELECT b FROM Basket b WHERE b.ownerCompanyId = :ownerCompanyId")
    ArrayList<Basket> getBasketsByOwnerCompanyId(String ownerCompanyId);
}
