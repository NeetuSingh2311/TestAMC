package com.demo.testamc.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    private String address;
    private String email;
    private String contactNumber;
    private String pan;
    private String passwordHash;
    private String role; // e.g., "USER" or "ADMIN"
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", pan='" + pan + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}