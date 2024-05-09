package com.testtask.testtask.repository;

import com.testtask.testtask.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
