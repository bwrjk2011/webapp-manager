package com.bridgeweave.manager.services;

import com.bridgeweave.manager.data.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HobbyService {

    private final HobbyRepository repository;

    public HobbyService(HobbyRepository repository) {
        this.repository = repository;
    }

    public Optional<Hobby> get(Long id) {
        return repository.findById(id);
    }

    public Hobby update(Hobby entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Hobby> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Hobby> list(Pageable pageable, Specification<Hobby> filter) {
        return repository.findAll(filter, pageable);
    }

    public  ArrayList<String> getAllNames() {
        List<Hobby> all = repository.findAll();
        ArrayList<String> names = new ArrayList<>();
        for(Hobby h: all){
            names.add(h.getName());
        }
        return names;
    }
    public ArrayList<Hobby> getAll(){
        return (ArrayList<Hobby>) repository.findAll();
    }

    public int count() {
        return (int) repository.count();
    }

}
