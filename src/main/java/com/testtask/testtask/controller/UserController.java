package com.testtask.testtask.controller;
import com.testtask.testtask.exceptions.NoUsersException;
import com.testtask.testtask.exceptions.UserAdultException;
import com.testtask.testtask.exceptions.UserAlreadyExistException;
import com.testtask.testtask.exceptions.UserIsNotFoundException;
import com.testtask.testtask.model.User;
import com.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (NoUsersException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getUserById(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        }catch (UserIsNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity createNewUser(@RequestBody User user ){
        try {
            userService.createUser(user);
            return ResponseEntity.ok("New user created. Everything is OK");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserAdultException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateUserById(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User updatedUserInfo = userService.updateUserById(id, updatedUser);
            return ResponseEntity.ok(updatedUserInfo);
        } catch (UserIsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update/all")
    public ResponseEntity updateAllUsers(@RequestBody User updatedUser) {
        try {
            userService.updateAllUsers(updatedUser);
            return ResponseEntity.ok("All users updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User was deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity searchUsersByBirthDateRange(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        try {
            List<User> users = userService.searchUsersByBirthDateRange(fromDate, toDate);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
