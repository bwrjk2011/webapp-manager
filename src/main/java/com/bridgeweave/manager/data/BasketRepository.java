package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository
        extends
            JpaRepository<Basket, Long>,
            JpaSpecificationExecutor<Basket> {






}
