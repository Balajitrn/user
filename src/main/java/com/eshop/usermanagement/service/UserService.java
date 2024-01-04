package com.eshop.usermanagement.service;

import com.eshop.usermanagement.dto.UserAuthenticateDTO;
import com.eshop.usermanagement.dto.UserProfileUpdateDTO;
import com.eshop.usermanagement.dto.UserRegistrationDTO;
import com.eshop.usermanagement.entity.User;
import com.eshop.usermanagement.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to Register New User
     * @param userRegistrationDTO
     * @return userRegistrationDTO
     */
    public UserRegistrationDTO registerUser(UserRegistrationDTO userRegistrationDTO){

        if(userRepository.findByEmail(userRegistrationDTO.getEmail())!=null){
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPasswordHash(userRegistrationDTO.getPasswordHash());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setDateOfBirth(userRegistrationDTO.getDateOfBirth());
        User savedUser = userRepository.save(user);
        return entityToDTO(savedUser);
    }

    /**
     * Utility method to convert user entity to DTO
     * @param user entity
     * @return UserRegistrationDTO
     */
    UserRegistrationDTO entityToDTO(User user){
        return new UserRegistrationDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getFirstName(),user.getLastName(), user.getDateOfBirth());
    }

    /**
     * Method takes inputs(email,password) and authenticates
     * @param userAuthenticateDTO
     * @return
     */
    public String authenticateUser(UserAuthenticateDTO userAuthenticateDTO){
        User user = userRepository.findByEmail(userAuthenticateDTO.getEmail());
        System.out.println(user.getEmail());
       if(!Objects.equals(user.getPasswordHash(), userAuthenticateDTO.getPasswordHash())){
           return "Password doesn't match! Authentication failed";
       }
       return "User authenticated";
    }


    public UserProfileUpdateDTO updateUserById(UserProfileUpdateDTO userProfileUpdateDTO, Long id){

        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found with id = "+id));

        user.setUsername(userProfileUpdateDTO.getUsername());
        user.setEmail(userProfileUpdateDTO.getEmail());
        user.setPasswordHash(userProfileUpdateDTO.getPasswordHash());
        user.setFirstName(userProfileUpdateDTO.getFirstName());
        user.setLastName(userProfileUpdateDTO.getLastName());
        user.setDateOfBirth(userProfileUpdateDTO.getDateOfBirth());
        User savedUser = userRepository.save(user);
        return entityToDTOProfileUpdate(savedUser);
    }

    UserProfileUpdateDTO entityToDTOProfileUpdate (User user){
        return new UserProfileUpdateDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPasswordHash(), user.getFirstName(),user.getLastName(), user.getDateOfBirth());
    }

    public List<UserRegistrationDTO> getUserList(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public UserRegistrationDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found "+id));
        return entityToDTO(user);
    }

    public void deleteById(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Product not found with id "+id);
        }
        userRepository.deleteById(id);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

}
