package com.vapecenter.demo.repositories;

import com.vapecenter.demo.models.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo {
    Users getUser(int id);
}
