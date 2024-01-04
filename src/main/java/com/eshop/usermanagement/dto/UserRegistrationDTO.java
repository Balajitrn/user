package com.eshop.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDTO {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;   //Shilpa check
    private String firstName;
    private String lastName;
    private String dateOfBirth;

}
