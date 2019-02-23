package com.gaurav.repository;

import com.gaurav.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findById(Long customerId);

    Customer save(Customer customer);

    void deleteByCustomerId(Long customerId);

}
