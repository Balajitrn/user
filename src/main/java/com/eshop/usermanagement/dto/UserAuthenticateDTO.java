package com.eshop.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticateDTO {
    private String email;
    private String passwordHash;
}
