package com.tus.womensafety.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class UserPostDTO {
    Long id;


    @NotBlank
    @Pattern(regexp = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$", message = "name must have only letters")
    String name;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must have only digits and 10 digits atleast")
    String phoneNumber;

    List<Long> relatives;
}
