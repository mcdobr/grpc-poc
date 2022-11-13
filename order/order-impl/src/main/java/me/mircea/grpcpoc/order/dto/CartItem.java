package me.mircea.grpcpoc.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartItem {
    @JsonProperty("product")
    private UUID product;

    @JsonProperty("quantity")
    private Long quantity;
}
