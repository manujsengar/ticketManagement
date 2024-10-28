package com.ticketManagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Details about the user")
public class User {
    
    @Schema(description = "First name of the user")
    @NotBlank
    private String firstName;

    @Schema(description = "Last name of the user")
    private String lastName;

    @Schema(description = "Email of the user")
    @Email
    private String email;
}