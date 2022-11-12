package me.mircea.grpcpoc.mapper;

import lombok.NoArgsConstructor;
import me.mircea.grpcpoc.model.Customer;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerEntityToDtoMapper {
    public CustomerDto convert(Customer customer) {
        return new CustomerDto();
    }
}
