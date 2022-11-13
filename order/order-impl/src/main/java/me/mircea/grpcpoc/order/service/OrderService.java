package me.mircea.grpcpoc.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mircea.grpcpoc.customer.CustomerReply;
import me.mircea.grpcpoc.customer.CustomerServiceGrpc;
import me.mircea.grpcpoc.customer.IDRequest;
import me.mircea.grpcpoc.order.dto.OrderStatus;
import me.mircea.grpcpoc.order.dto.ShoppingCart;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    @GrpcClient("customer")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceStub;

    public OrderStatus create(ShoppingCart cart) {
        log.info("Creating order for {}", cart);
        CustomerReply reply = customerServiceStub.findCustomer(
                IDRequest.newBuilder()
                .setId(cart.getCustomer().toString())
                .build()
        );
        log.info("Received {}", reply);
        var orderStatus = new OrderStatus();
        orderStatus.setShippingAddress(reply.getAddress());

        log.info("Created {}", orderStatus);
        return orderStatus;
    }
}
