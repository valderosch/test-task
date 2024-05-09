package com.testtask.testtask.service;
import com.testtask.testtask.exceptions.UserAdultException;
import com.testtask.testtask.exceptions.UserAlreadyExistException;
import com.testtask.testtask.model.User;
import com.testtask.testtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws UserAlreadyExistException, UserAdultException {
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistException("User With this email already exist");
        }
//        else if(user.getBirthDate() < 18) {
//            throw new UserAdultException("User is not adult. Age must be older than 18");
//        }
        return userRepository.save(user);
    }

//    public User getAllUsers() {
//        return ;
//    }
}
