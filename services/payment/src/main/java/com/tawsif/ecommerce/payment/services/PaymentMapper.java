package com.tawsif.ecommerce.payment.services;

import com.tawsif.ecommerce.payment.models.Payment;
import com.tawsif.ecommerce.payment.models.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(@Valid PaymentRequest request) {
//        return Payment.builder()
//                .id(request.id())
//                .amount(request.amount())
//                .orderId(request.orderId())
//                .paymentMethod(request.paymentMethod())
//                .build();
        Payment payment = new Payment();
        payment.setId(request.id());
        payment.setAmount(request.amount());
        payment.setOrderId(request.orderId());
        payment.setPaymentMethod(request.paymentMethod());
        return payment;
    }
}
