package com.vapecenter.demo.service;

import com.vapecenter.demo.models.Users;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Users getUser(int id);
}
