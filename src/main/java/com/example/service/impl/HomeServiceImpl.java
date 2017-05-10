package com.example.service.impl;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Morgane TROYSI on 22/04/2017.
 */

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Map<String, Object> getResource() {
        /*Customer customer = new Customer();
        customer.setName("toto");
        customer.setEmail("toto@toto.to");
        customerRepository.save(customer);*/
        List<Customer> customerList = customerRepository.findAllByName("toto");
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
}
