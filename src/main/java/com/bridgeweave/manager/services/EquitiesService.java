package com.bridgeweave.manager.services;

import com.bridgeweave.manager.data.Equities;
import com.bridgeweave.manager.data.EquitiesRepository;
import com.bridgeweave.manager.data.Hobby;
import com.bridgeweave.manager.data.HobbyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquitiesService {

    private final EquitiesRepository repository;

    public EquitiesService(EquitiesRepository repository) {
        this.repository = repository;
    }

    public Optional<Equities> get(Integer id) {
        return repository.findById(id);
    }

    public Equities update(Equities entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<Equities> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Equities> list(Pageable pageable, Specification<Equities> filter) {
        return repository.findAll(filter, pageable);
    }

    public ArrayList<Equities> getAll(){
        return (ArrayList<Equities>) repository.findAll();
    }

    public int count() {
        return (int) repository.count();
    }


    public Equities getEquityBySymbol(String symbol){
        Equities equityBySymbol = repository.getEquityBySymbol(symbol);
        return equityBySymbol;
    }

}
