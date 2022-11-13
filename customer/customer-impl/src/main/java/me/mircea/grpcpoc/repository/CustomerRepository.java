package me.mircea.grpcpoc.repository;

import me.mircea.grpcpoc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
