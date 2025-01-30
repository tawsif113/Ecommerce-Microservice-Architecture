package com.tawsif.ecommerce.order;

import com.tawsif.ecommerce.customer.CustomerClient;
import com.tawsif.ecommerce.kafka.OrderConfirmation;
import com.tawsif.ecommerce.kafka.OrderProducer;
import com.tawsif.ecommerce.orderline.OrderLineRequest;
import com.tawsif.ecommerce.orderline.OrderLineService;
import com.tawsif.ecommerce.payment.PaymentClient;
import com.tawsif.ecommerce.payment.PaymentRequest;
import com.tawsif.ecommerce.product.ProductClient;
import com.tawsif.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(()->new BusinessException("Can not Create Order:: No customer Exist with provided id: " + request.customerId()));

        var purchasedProducts =this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new BusinessException("Order Not Found with ID: " + orderId));
    }


}
