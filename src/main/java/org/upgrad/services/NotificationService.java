package org.upgrad.services;

import org.upgrad.models.Notification;

import java.util.ArrayList;
import java.util.List;

public interface NotificationService {

    List<Notification> getNewNotifications(Long userId);
    List<Notification> getAllNotifications(Long userId);
}
