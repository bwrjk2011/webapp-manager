package com.bridgeweave.manager.services;

import com.bridgeweave.manager.data.Basket;
import com.bridgeweave.manager.data.BasketRepository;
import com.bridgeweave.manager.data.UserNotificationRepository;
import com.bridgeweave.manager.data.UserNotifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserNotificationService {

    private final UserNotificationRepository repository;

    public UserNotificationService(UserNotificationRepository repository) {
        this.repository = repository;
    }

    public Optional<UserNotifications> get(Long id) {
        return repository.findById(id);
    }

    public UserNotifications update(UserNotifications entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<UserNotifications> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<UserNotifications> list(Pageable pageable, Specification<UserNotifications> filter) {
        return repository.findAll(filter, pageable);
    }


//    Stub Implementations
    public ArrayList<UserNotifications> getAll(){
        return (ArrayList<UserNotifications>) repository.findAll();
    }

    public ArrayList<UserNotifications> getAllUnSeenNotifications(){
        return (ArrayList<UserNotifications>) repository.findAll();
    }

    public Integer getAllUnSeenNotificationsCount(){
        return 10;
    }

    public int count() {
        return (int) repository.count();
    }

}
