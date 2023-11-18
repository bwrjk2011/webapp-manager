package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SamplePersonRepository2
        extends
            JpaRepository<SamplePerson2, Long>,
            JpaSpecificationExecutor<SamplePerson2> {

}
