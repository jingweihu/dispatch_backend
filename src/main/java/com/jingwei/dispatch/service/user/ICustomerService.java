package com.jingwei.dispatch.service.user;

import com.jingwei.dispatch.db.model.Customer;
import java.util.Optional;

public interface ICustomerService {

    Optional<Customer> findById(Long id);

    Optional<Customer> findByUserName(String username);

    Optional<Customer> save(String username, String password);

    Optional<Customer> update(Customer customer);

    Optional<Customer> remove(Long customerId);
}
