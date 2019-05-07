package com.vapecenter.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

    @Autowired
    JdbcTemplate template;

    Logger log = Logger.getLogger(CustomerRepoImpl.class.getName());

}

