package me.mircea.grpcpoc.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mircea.grpcpoc.order.dto.OrderStatus;
import me.mircea.grpcpoc.order.dto.ShoppingCart;
import me.mircea.grpcpoc.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public OrderStatus create(@RequestBody ShoppingCart cart) {
        return orderService.create(cart);
    }
}
