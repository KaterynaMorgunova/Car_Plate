package com.f1dot5.data.repository;

import com.f1dot5.data.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer findByCredentials(String name, String password);

    Customer save(Customer customer);
}
