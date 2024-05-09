package com.testtask.testtask.controller;
import com.testtask.testtask.exceptions.UserAlreadyExistException;
import com.testtask.testtask.model.User;
import com.testtask.testtask.repository.UserRepository;
import com.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        try {

            return ResponseEntity.ok("All users");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Get All Users | Some Error");
        }
    }

    @PostMapping("/new")
    public ResponseEntity createNewUser(@RequestBody User user ){
        try {
            userService.createUser(user);
            return ResponseEntity.ok("New user created. Everything is OK");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Create New User | Some Error");
        }
    }

    @PostMapping("/update/all")
    public ResponseEntity updateUserById(){
        try {
            return ResponseEntity.ok("Server is going to update a user");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Update User | Some Error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUserById(){
        try {
            return ResponseEntity.ok("Server is going to delete a user #");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete User | Some Error while deleting user");
        }
    }
}
