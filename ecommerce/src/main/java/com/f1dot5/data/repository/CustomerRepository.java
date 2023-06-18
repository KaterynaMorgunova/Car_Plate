package com.f1dot5.data.repository;

import com.f1dot5.data.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    Customer findByNameAndPassword(String name, String password);
}
