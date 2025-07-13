package com.demo.testamc.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(schema = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String email;
    private String address;
    private String contactNumber;
    private String pan;
    private String password;
    private String role;
}