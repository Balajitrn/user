package com.eshop.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Length(min = 4 , max = 12)
    private String username;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)   //Shilpa check
    @Length(min = 8 , max = 128)
    private String passwordHash;

    @Column(length = 50,nullable = false)
    @Length(min = 4 , max = 12)
    private String firstName;

    @Column(length = 50,nullable = false)
    @Length(min = 4 , max = 12)
    private String lastName;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @PrePersist
    public void onCreate(){
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = new Date();
    }
}
