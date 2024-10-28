package com.ticketManagement.service;

import java.util.List;

import com.ticketManagement.model.Ticket;

public interface TicketService {

	// Purchase a ticket with automatic seat allocation
	Ticket purchaseTicket(Ticket ticket);

	// Remove a user and free up the seat
	void removeUser(String ticketId);

	// Modify a user's seat
	void modifySeat(String ticketId, String newSeat);

	List<Ticket> getUsersBySection(String section);

	Ticket getBySeatNo(String seatNo);

}