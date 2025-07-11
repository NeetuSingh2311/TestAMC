package com.demo.testamc.dto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {
    @NonNull
    @NotBlank
    private String fullName;
    @NonNull
    @NotBlank
    private String address;
    @Email
    private String email;
    @NonNull
    @NotBlank
    @Pattern(regexp = "^(\\+91)?[6-9]\\d{9}$")
    private String contactNumber;
    @NonNull
    @NotBlank
    private String pan;
    @NonNull
    @NotBlank
    private String password;
}