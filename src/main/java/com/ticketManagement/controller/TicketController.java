package com.ticketManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketManagement.model.Ticket;
import com.ticketManagement.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket API", description = "APIs for managing train tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Operation(summary = "Purchase a ticket")
	@PostMapping("/purchase")
	public Ticket purchaseTicket(@Valid @RequestBody Ticket ticket) {
		return ticketService.purchaseTicket(ticket);
	}

	@Operation(summary = "Get ticket details by Seat")
	@GetMapping("/getSeatDetails/{seatNo}")
	public Ticket purchaseTicket(@PathVariable String seatNo) {
		return ticketService.getBySeatNo(seatNo);
	}

	@Operation(summary = "Get users by section")
	@GetMapping("/section/{section}")
	public List<Ticket> getUsersBySection(@PathVariable String section) {
		return ticketService.getUsersBySection(section);
	}

	@Operation(summary = "Remove a Ticket by Ticket ID")
	@DeleteMapping("/remove/{ticketId}")
	public String removeUser(@PathVariable String ticketId) {
		ticketService.removeUser(ticketId);
		return "Ticket removed successfully.";
	}

	@Operation(summary = "Modify seat allocation")
	@PutMapping("/modify-seat/{ticketId}")
	public String modifySeat(@PathVariable String ticketId, @RequestParam String newSeat) {
		ticketService.modifySeat(ticketId, newSeat);
		return "Seat updated successfully.";
	}
}