package com.tawsif.ecommerce.notification;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String,PaymentNotificationRequest> kafkaTemplate;

    @Autowired
    public NotificationProducer(KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(PaymentNotificationRequest request){
        System.out.println("Sending notification for payment "+ request);
        Message<PaymentNotificationRequest>message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC,"payment-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
