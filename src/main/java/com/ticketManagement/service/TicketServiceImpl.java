package com.ticketManagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ticketManagement.model.Section;
import com.ticketManagement.model.Ticket;
import com.ticketManagement.repository.TicketRepository;

@Service
@Primary
public class TicketServiceImpl implements TicketService {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public Ticket purchaseTicket(Ticket ticket) {
		purchaseTicketExt(ticket);
		logger.info("Ticket purchased: {}", ticket);
		return ticket;
	}

	@Override
	public List<Ticket> getUsersBySection(String section) {
		List<Ticket> result = new ArrayList<>();
		ticketRepository.getTickets().values().stream().filter(ticket -> ticket.getSeat().startsWith(section))
				.forEach(result::add);
		return result;
	}

	// Remove a user and free up the seat
	@Override
	public void removeUser(String ticketId) {
		Ticket ticket = ticketRepository.getTickets().remove(ticketId);
		if (ticket == null) {
			throw new RuntimeException("Ticket not found with ID: " + ticketId);
		}

		ticketRepository.freeSeat(ticket.getSeat()); // Free the allocated seat
		logger.info("Ticket removed with ID: {}", ticketId);
	}

	// Modify a user's seat
	public void modifySeat(String ticketId, String newSeat) {
		Ticket ticket = Optional.ofNullable(ticketRepository.getTickets().get(ticketId))
				.orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + ticketId));

		if (ticketRepository.isSeatOccupied(newSeat)) {
			throw new RuntimeException("Seat " + newSeat + " is already occupied.");
		}

		ticketRepository.freeSeat(ticket.getSeat()); // Free the old seat
		ticket.setSeat(newSeat);
		ticket.setUpdatedDate(LocalDateTime.now());
		ticket.setSection(newSeat.charAt(0) == 'A' ? Section.A : Section.B);// Assign the new seat
		ticketRepository.occupySeat(newSeat); // Mark the new seat as occupied
		logger.info("Seat modified for ticket {}: New seat - {}", ticketId, newSeat);
	}

	// Purchase a ticket with automatic seat allocation
	public Ticket purchaseTicketExt(Ticket ticket) {
		String allocatedSeat = ticketRepository.allocateSeat(ticket.getSection()); // Get the next avail seat
		if (allocatedSeat == null) {
			throw new RuntimeException("No seats available in section " + ticket.getSection());
		}
		ticketRepository.getTickets().put(ticket.getTicketId().toString(), ticket);
		ticket.setSeat(allocatedSeat);
		logger.info("Ticket purchased: {}", ticket);
		return ticket;
	}

	@Override
	public Ticket getBySeatNo(String seatNo) {
		if (seatNo.startsWith("A") || seatNo.startsWith("B")) {
			return ticketRepository.getSeatDetails(seatNo);
		} else {
			throw new RuntimeException("Seat " + seatNo + " does not exist");
		}
	}

}