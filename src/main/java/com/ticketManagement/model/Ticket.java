package com.ticketManagement.model;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the ticket")
public class Ticket {

	@Schema(description = "Origin station")
	@NotBlank(message = "From location cannot be empty")
	private String from;

	private UUID ticketId = UUID.randomUUID();

	@Schema(description = "Destination station")
	@NotBlank(message = "To location cannot be empty")
	private String to;

	@Schema(description = "User associated with the ticket")
	@NotNull(message = "User details must be provided")
	private User user;

	@Schema(description = "Price of the ticket")
	@Positive(message = "Price must be positive")
	private double price;

	@Schema(description = "Allocated seat in the train")
	private String seat;

	@Schema(description = "Allocated seat in the train")
	@NotNull(message = "Section must be selected")
	private Section section;

	LocalDateTime bookedDate = LocalDateTime.now();

	private LocalDateTime updatedDate;
}
