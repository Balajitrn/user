package com.eshop.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
