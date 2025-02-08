package com.tawsif.ecommerce.payment.services;

import com.tawsif.ecommerce.notification.NotificationProducer;
import com.tawsif.ecommerce.notification.PaymentNotificationRequest;
import com.tawsif.ecommerce.payment.repositories.PaymentRepository;
import com.tawsif.ecommerce.payment.models.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {


    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Autowired
    public PaymentService(PaymentRepository repository, PaymentMapper mapper, NotificationProducer notificationProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();

    }
}
