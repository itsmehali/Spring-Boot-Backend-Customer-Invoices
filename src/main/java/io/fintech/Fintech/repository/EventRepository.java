package io.fintech.Fintech.repository;

import io.fintech.Fintech.domain.UserEvent;
import io.fintech.Fintech.enumeration.EventType;

import java.util.Collection;

public interface EventRepository {
    Collection<UserEvent> getEventsByUserId(Long userId);
    void addUserEvent(String email, EventType eventType, String device, String ipAddress);
    void addUserEvent(Long userId, EventType eventType, String device, String ipAddress);
}
