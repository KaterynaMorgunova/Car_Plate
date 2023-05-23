package com.f1dot5.data.repository;

import com.f1dot5.data.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByName(String name);

    Customer save(Customer customer);
}
