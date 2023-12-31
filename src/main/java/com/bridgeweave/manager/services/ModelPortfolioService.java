package com.bridgeweave.manager.services;


import com.bridgeweave.manager.data.ModelPortfolio;
import com.bridgeweave.manager.data.ModelPortfolioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelPortfolioService {
    private final ModelPortfolioRepository repository;

    public ModelPortfolioService(ModelPortfolioRepository repository) {
        this.repository = repository;
    }

    public Optional<ModelPortfolio> get(Long id) {
        return repository.findById(id);
    }

    public ModelPortfolio update(ModelPortfolio entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<ModelPortfolio> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<ModelPortfolio> list(Pageable pageable, Specification<ModelPortfolio> filter) {
        return repository.findAll(filter, pageable);
    }

    public  ArrayList<String> getAllNames() {
        List<ModelPortfolio> all = repository.findAll();
        ArrayList<String> names = new ArrayList<>();
        for(ModelPortfolio b: all){
            names.add(b.getName());
        }
        return names;
    }
    public ArrayList<ModelPortfolio> getAll(){
        return (ArrayList<ModelPortfolio>) repository.findAll();
    }

    public ArrayList<ModelPortfolio> getByBid(Long basketId){
        return (ArrayList<ModelPortfolio>) repository.findByBid(basketId);
    }


    public void deleteTicketsForBasketIt(Long basketId){
        repository.deleteTickersForBasketId(basketId);
        System.out.println("All Tickers for Basket Id have been deleted " + basketId);
    }


    public int count() {
        return (int) repository.count();
    }



    public List<ModelPortfolio> getEquityErrorsByBasketId(Long basketId){
        List<ModelPortfolio> equityErrorsByBasketId = null;
        try{
            equityErrorsByBasketId = repository.getEquityErrorsByBasketId(basketId);
        } catch (Exception e){
            System.out.print("public List<ModelPortfolio> getEquityErrorsByBasketId(String basketId) has an exception " + e);
        }
        return equityErrorsByBasketId;
    }



    }
