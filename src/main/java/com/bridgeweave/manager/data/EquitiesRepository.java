package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquitiesRepository
        extends
            JpaRepository<Equities, Long>,
            JpaSpecificationExecutor<Equities> {

}
