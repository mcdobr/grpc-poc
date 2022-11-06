package me.mircea.grpcpoc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mircea.grpcpoc.mapper.CustomerEntityToDtoMapper;
import me.mircea.grpcpoc.model.Customer;
import me.mircea.grpcpoc.repository.CustomerRepository;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    private CustomerEntityToDtoMapper mapper;

    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }

    public CustomerDto findById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return mapper.convert(customer);
    }
}
