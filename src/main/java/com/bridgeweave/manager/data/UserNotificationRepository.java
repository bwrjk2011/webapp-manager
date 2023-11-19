package com.bridgeweave.manager.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserNotificationRepository
        extends
            JpaRepository<UserNotifications, Long>,
            JpaSpecificationExecutor<UserNotifications> {


//    @Query("select c from Contact c " +
//            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
//            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")

    @Query("SELECT un FROM UserNotifications un WHERE un.userId = :userId AND un.hasBeenSeen = false")
    public List<UserNotifications> getUnseenNotificationsForUser(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserNotifications un SET un.hasBeenSeen = true WHERE un.userId = :userId")
    public void setAllNotificationsAsSeenForUser(@Param("userId") Long userId);






}
