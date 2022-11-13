package me.mircea.grpcpoc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mircea.grpcpoc.customer.CustomerContactEdit;
import me.mircea.grpcpoc.customer.CustomerRegistration;
import me.mircea.grpcpoc.customer.IDRequest;
import me.mircea.grpcpoc.mapper.CustomerEntityToDtoMapper;
import me.mircea.grpcpoc.model.Customer;
import me.mircea.grpcpoc.repository.CustomerRepository;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerEntityToDtoMapper mapper;


    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDto findById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return mapper.convert(customer);
    }

    @Transactional
    public CustomerDto create(CustomerRegistration registrationRequest) {
        log.info("Registering {}...", registrationRequest);
        Customer customer = new Customer();
        customer.setName(registrationRequest.getName());
        customer.setEmail(registrationRequest.getEmail());
        customer.setPhoneNumber(registrationRequest.getPhoneNumber());
        customer.setGender(registrationRequest.getGender());
        customer.setAddress(registrationRequest.getAddress());
        customer.setRegisteredOn(Instant.now());

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDto response = mapper.convert(savedCustomer);
        log.info("Registered {}", response);
        return response;
    }

    @Transactional
    public CustomerDto edit(CustomerContactEdit request) {
        log.info("Editing customer based on {}...", request);
        Customer customer = customerRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow();

        customer.setAddress(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDto response = mapper.convert(savedCustomer);
        log.info("Edited customer to {}", response);
        return response;
    }


    @Transactional
    public void deleteByID(IDRequest request) {
        customerRepository.deleteById(UUID.fromString(request.getId()));
    }
}
