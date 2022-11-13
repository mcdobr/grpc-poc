package me.mircea.grpcpoc.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShoppingCart {
    @JsonProperty("customer")
    private UUID customer;

    @JsonProperty("items")
    private List<CartItem> items;
}
