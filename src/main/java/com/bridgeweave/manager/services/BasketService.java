package com.bridgeweave.manager.services;

import com.bridgeweave.manager.data.Basket;
import com.bridgeweave.manager.data.BasketRepository;
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
public class BasketService {

    private final BasketRepository repository;

    public BasketService(BasketRepository repository) {
        this.repository = repository;
    }

    public Optional<Basket> get(Long id) {
        return repository.findById(id);
    }

    public Basket update(Basket entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Basket> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Basket> list(Pageable pageable, Specification<Basket> filter) {
        return repository.findAll(filter, pageable);
    }

    public  ArrayList<String> getAllNames() {
        List<Basket> all = repository.findAll();
        ArrayList<String> names = new ArrayList<>();
        for(Basket b: all){
            names.add(b.getBasketName());
        }
        return names;
    }
    public ArrayList<Basket> getAll(){
        return (ArrayList<Basket>) repository.findAll();
    }

    public int count() {
        return (int) repository.count();
    }

}
