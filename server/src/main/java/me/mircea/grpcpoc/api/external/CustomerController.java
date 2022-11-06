package me.mircea.grpcpoc.api.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mircea.grpcpoc.service.CustomerService;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    public List<CustomerDto> findCustomers() {
        return customerService.findAll();
    }


    @GetMapping("/{customer-id}")
    public CustomerDto findCustomer(@PathVariable("customer-id") UUID id) {
        return customerService.findById(id);
    }


}
