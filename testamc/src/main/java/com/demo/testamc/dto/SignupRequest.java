package com.demo.testamc.dto;
import lombok.Data;

@Data
public class SignupRequest {
    private String fullName;
    private String address;
    private String email;
    private String contactNumber;
    private String pan;
    private String password;
}