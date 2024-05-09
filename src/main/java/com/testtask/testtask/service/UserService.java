package com.testtask.testtask.service;
import com.testtask.testtask.exceptions.NoUsersException;
import com.testtask.testtask.exceptions.UserAdultException;
import com.testtask.testtask.exceptions.UserAlreadyExistException;
import com.testtask.testtask.exceptions.UserIsNotFoundException;
import com.testtask.testtask.model.User;
import com.testtask.testtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Value("${user.minAge}")
    private int minAge;

    @Autowired
    private UserRepository userRepository;

    private int calculateAge(Date birthDate) {
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    public User createUser(User user) throws UserAlreadyExistException, UserAdultException {
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistException("User With this email already exist");
        }
        int age = calculateAge(user.getBirthDate());
        if (age < minAge) {
            throw new UserAdultException("User must be at least " + minAge + " years old");
        }
        return userRepository.save(user);
    }

    public User getUserById (Long id) throws UserIsNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null){
            throw new UserIsNotFoundException("User is not found. Try another parameters");
        }
        return user;
    }

    public User updateUserById(Long id, User updatedUser) throws UserIsNotFoundException {
        User userToUpdate = userRepository.findById(id).get();
        if (userToUpdate == null){
            throw new UserIsNotFoundException("User is not found.");
        }

        if (updatedUser.getEmail() != null) {
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getFirstName() != null) {
            userToUpdate.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            userToUpdate.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getBirthDate() != null) {
            userToUpdate.setBirthDate(updatedUser.getBirthDate());
        }
        if (updatedUser.getAddress() != null) {
            userToUpdate.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        return userRepository.save(userToUpdate);
    }

    public Long deleteUserById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public List<User> getAllUsers() throws NoUsersException {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> users = new ArrayList<>();
        userIterable.forEach(users::add);
        if (users.isEmpty()) {
            throw new NoUsersException("No users found. Maybe you need to add one");
        }
        return users;
    }

    public void updateAllUsers(User updatedUser) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        for (User user : users) {
            if (updatedUser.getFirstName() != null) {
                user.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                user.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getBirthDate() != null) {
                user.setBirthDate(updatedUser.getBirthDate());
            }
            if (updatedUser.getAddress() != null) {
                user.setAddress(updatedUser.getAddress());
            }
            if (updatedUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }
        }
        userRepository.saveAll(users);
    }
}
