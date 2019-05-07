package com.vapecenter.demo.service;

import com.vapecenter.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    JdbcTemplate template;

    Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

}
