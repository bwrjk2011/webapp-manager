package com.bridgeweave.manager.services;

import com.bridgeweave.manager.data.UserNotificationRepository;
import com.bridgeweave.manager.data.UserNotifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
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

    public  List<UserNotifications> getAllUnSeenNotifications(Long userId){
        List<UserNotifications> unseenNotificationsForUser = repository.getUnseenNotificationsForUser(userId);
        System.out.println(unseenNotificationsForUser);

        return unseenNotificationsForUser;
    }

    public void setAllNotificationsAsSeenForUser(Long userId){
        repository.setAllNotificationsAsSeenForUser(userId);
        System.out.println("All notifications set as seen for user " + userId);
    }



    public int count() {
        return (int) repository.count();
    }

}
