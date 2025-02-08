package com.tawsif.ecommerce.kafka;

import com.tawsif.ecommerce.models.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerEventProducer {

    private final KafkaTemplate<String,Object>kafkaTemplate;

    public CustomerEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String eventType, Customer customer) {
        CustomerEvent customerEvent = new CustomerEvent();
        customerEvent.setEventType(eventType);
        customerEvent.setTimestamp(Instant.now().toString());
        customerEvent.setData(customer);
        System.out.println("Publishing customer event: "+customerEvent);

        kafkaTemplate.send("customer-events", customerEvent);
    }

}
