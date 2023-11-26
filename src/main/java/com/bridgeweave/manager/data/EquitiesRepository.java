package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquitiesRepository
        extends
            JpaRepository<Equities, Integer>,
            JpaSpecificationExecutor<Equities> {

    @Query("SELECT eq FROM Equities eq WHERE eq.symbol = :symbol")
    public Equities getEquityBySymbol(@Param("symbol") String symbol);






}
