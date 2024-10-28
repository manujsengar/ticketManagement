package com.ticketManagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Details about the user")
public class User {
    
    @Schema(description = "First name of the user")
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @Schema(description = "Last name of the user")
    private String lastName;

    @Schema(description = "Email of the user")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;
}