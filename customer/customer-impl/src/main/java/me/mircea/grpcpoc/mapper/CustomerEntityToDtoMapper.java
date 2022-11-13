package me.mircea.grpcpoc.mapper;

import lombok.NoArgsConstructor;
import me.mircea.grpcpoc.common.Gender;
import me.mircea.grpcpoc.model.Customer;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Component
@NoArgsConstructor
public class CustomerEntityToDtoMapper {
    public CustomerDto convert(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setGender(customer.getGender());
        customerDto.setAddress(customer.getAddress());
        customerDto.setRegisteredOn(customer.getRegisteredOn());
        return customerDto;
    }
}
