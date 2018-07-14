package org.upgrad.services;

import org.upgrad.models.Notification;

import java.util.ArrayList;

public interface NotificationService {

    ArrayList<Notification> getNewNotifications(Long userId);
    ArrayList<Notification> getAllNotifications(Long userId);
}
