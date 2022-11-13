package me.mircea.grpcpoc.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderStatus {
    @JsonProperty("shippingAddress")
    private String shippingAddress;
}
