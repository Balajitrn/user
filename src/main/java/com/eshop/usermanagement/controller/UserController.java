package com.eshop.usermanagement.controller;

import com.eshop.usermanagement.dto.UserAuthenticateDTO;
import com.eshop.usermanagement.dto.UserProfileUpdateDTO;
import com.eshop.usermanagement.dto.UserRegistrationDTO;
import com.eshop.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO){

        try{
            return new ResponseEntity<>(userService.registerUser(userRegistrationDTO), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }


    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAuthenticateDTO userAuthenticateDTO){
        return new ResponseEntity<>(userService.authenticateUser(userAuthenticateDTO),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@RequestBody UserProfileUpdateDTO userProfileUpdateDTO,@PathVariable Long userId){
        try {
            return new ResponseEntity<>(userService.updateUserById(userProfileUpdateDTO, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserRegistrationDTO>> getUserList(){
        return new ResponseEntity<>(userService.getUserList(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
        }catch (Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(userService.deleteById(userId),HttpStatus.OK);
           // userService.deleteById(userId);
           // return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }
}
