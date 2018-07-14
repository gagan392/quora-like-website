package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Notification;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    @Query(nativeQuery = true, value = "select * from notification where user_id = (?1)")
    ArrayList<Notification> getAllNotifications(long userId);

    @Query(nativeQuery = true, value = "select * from notification where user_id = (?1) and read = false")
    ArrayList<Notification> getNewNotifications(long userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update notification set read = true")
    void markNewNotificationsAsRead();
}
