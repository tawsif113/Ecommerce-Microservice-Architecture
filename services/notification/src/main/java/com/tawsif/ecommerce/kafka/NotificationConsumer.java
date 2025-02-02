package com.tawsif.ecommerce.kafka;

import com.tawsif.ecommerce.email.EmailService;
import com.tawsif.ecommerce.kafka.order.OrderConfirmation;
import com.tawsif.ecommerce.kafka.payment.NotificationRepository;
import com.tawsif.ecommerce.kafka.payment.PaymentConfirmation;
import com.tawsif.ecommerce.notification.Notification;
import com.tawsif.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.tawsif.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @Autowired
    public NotificationConsumer(NotificationRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        System.out.println("Consuming payment success notification for payment"+paymentConfirmation);
//        repository.save(
//                Notification.builder()
//                        .type(PAYMENT_CONFIRMATION)
//                        .notificationDate(LocalDateTime.now())
//                        .paymentConfirmation(paymentConfirmation)
//                        .build()
//        );
        Notification notification = new Notification();
        notification.setType(NotificationType.PAYMENT_CONFIRMATION);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setPaymentConfirmation(paymentConfirmation);
        repository.save(notification);

        ///  send email
        var customerName = paymentConfirmation.customerFirstName()+" " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        System.out.println("Consuming order success notification for payment "+ orderConfirmation);
//        repository.save(
//                Notification.builder()
//                        .type(PAYMENT_CONFIRMATION)
//                        .notificationDate(LocalDateTime.now())
//                        .orderConfirmation(orderConfirmation)
//                        .build()
//        );

        Notification notification = new Notification();
        notification.setType(NotificationType.ORDER_CONFIRMATION);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setOrderConfirmation(orderConfirmation);
        repository.save(notification);

        var customerName = orderConfirmation.customer().firstName()+" " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }
}
