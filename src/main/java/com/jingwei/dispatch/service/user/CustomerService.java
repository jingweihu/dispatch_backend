package com.jingwei.dispatch.service.user;


import com.jingwei.dispatch.db.dao.CustomerDao;
import com.jingwei.dispatch.db.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerDao.findByID(id));
    }

    @Override
    public Optional<Customer> findByUserName(String username) {
        return Optional.ofNullable(customerDao.findByUserName(username));
    }

    @Override
    public Optional<Customer> save(String username, String password) {
        return Optional.ofNullable(customerDao.save(username, password));
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        return Optional.ofNullable(customerDao.update(customer));
    }

    @Override
    public Optional<Customer> remove(Long id) {
        return Optional.ofNullable(customerDao.remove(id));
    }
}
