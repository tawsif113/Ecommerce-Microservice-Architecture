package com.tawsif.ecommerce.kafka.payment;

import com.tawsif.ecommerce.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
