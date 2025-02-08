package com.tawsif.ecommerce.payment.controllers;


import com.tawsif.ecommerce.payment.models.PaymentRequest;
import com.tawsif.ecommerce.payment.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Integer>createPayment(
            @RequestBody @Valid PaymentRequest request
    ){
        return ResponseEntity.ok(paymentService.createPayment(request));

    }

}
