package com.ticketManagement.model;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the ticket")
public class Ticket {
 
 @Schema(description = "Origin station")
 @NotBlank
 private String from;

 private UUID ticketId = UUID.randomUUID(); 

 @Schema(description = "Destination station")
 @NotBlank
 private String to;

 @Schema(description = "User associated with the ticket")
 private User user;

 @Schema(description = "Price of the ticket")
 private double price;

 @Schema(description = "Allocated seat in the train")
 private String seat;
 
 @Schema(description = "Allocated seat in the train")
 @NotBlank
 private Section section;
 
 private LocalDateTime bookedDate=LocalDateTime.now();
 
 private LocalDateTime updatedDate;
}
