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
    public Customer findByCredentials(String name, String password) {
        List<Customer> results = jdbcTemplate.query(
                "select name, phone, email, created_at from Customer where name=? and password=?",
                this::mapRowToCustomer,
                name,
                password);
        return results.size() == 0 ? null : results.get(0);
    }

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update(
                "insert into Customer (name, phone, email, password, created_at) values (?, ?, ?, ?, ?)",
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
                new java.util.Date(row.getDate("created_at").getTime()),
                row.getString("name"),
                null, // don't use saved password
                row.getString("phone"),
                row.getString("email")
        );
    }

}
