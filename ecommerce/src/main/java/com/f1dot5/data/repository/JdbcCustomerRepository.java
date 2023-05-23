package com.f1dot5.data.repository;

import com.f1dot5.data.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        List<Customer> results = jdbcTemplate.query(
                "select name, phone, email, password, createdAt from Customer where name=?",
                this::mapRowToCustomer,
                name);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update(
                "insert into Customer (name, phone, email, password, createdAt) values (?, ?, ?, ?, ?)",
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getPassword(),
                new Date()
        );
        return customer;
    }

    private Customer mapRowToCustomer(ResultSet row, int rowNum)
            throws SQLException {
        return new Customer(
                new java.util.Date(row.getDate("createdAt").getTime()),
                row.getString("name"),
                row.getString("password"),
                row.getString("phone"),
                row.getString("email")
        );
    }

}
