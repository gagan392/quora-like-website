package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.repositories.NotificationRepository;

import java.util.ArrayList;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ArrayList<Notification> getNewNotifications(Long userId) {
        ArrayList<Notification> notifications = notificationRepository.getNewNotifications(userId);
        notificationRepository.markNewNotificationsAsRead();
        return notifications;
    }

    @Override
    public ArrayList<Notification> getAllNotifications(Long userId) {
        ArrayList<Notification> notifications = notificationRepository.getAllNotifications(userId);
        notificationRepository.markNewNotificationsAsRead();
        return notifications;
    }
}
