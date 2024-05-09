package com.testtask.testtask.repository;

import com.testtask.testtask.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByBirthDateBetween(Date fromDate, Date toDate);
}
