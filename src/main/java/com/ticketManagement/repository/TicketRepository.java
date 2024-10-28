package com.ticketManagement.repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

import com.ticketManagement.model.Section;
import com.ticketManagement.model.Ticket;

@Repository
public class TicketRepository {
	// In-memory storage for tickets
	private static final Map<String, Ticket> tickets = new ConcurrentHashMap<>();
	// Track occupied seats for both sections
	private static final Set<String> sectionASeats = new CopyOnWriteArraySet<>();
	private static final Set<String> sectionBSeats = new CopyOnWriteArraySet<>();

	// Initialize seat availability (72 seats in each section)
	public TicketRepository() {
		IntStream.rangeClosed(1, 72).forEach(i -> {
			sectionASeats.add("A" + i); // A1 to A72
			sectionBSeats.add("B" + i); // B1 to B72
		});
	}

	public Map<String, Ticket> getTickets() {
		return this.tickets;
	}

	// Allocate the next available seat from the requested section
	public String allocateSeat(Section section) {
		Set<String> availableSeats = section.equals(Section.A) ? sectionASeats : sectionBSeats;

		String seat = availableSeats.stream().findFirst().orElse(null); // Get the first available seat
		occupySeat(seat);
		return seat;
	}

	// Get seat details
	public Ticket getSeatDetails(String seat) {
		if ((seat.startsWith("A") && !sectionASeats.contains(seat))
				|| seat.startsWith("B") && !sectionBSeats.contains(seat)) {
			return tickets.values().stream().filter(ticket -> ticket.getSeat().equals(seat)).findFirst()
					.orElseThrow(() -> new RuntimeException("Ticket not found for seat: " + seat));

		} else {
			throw new RuntimeException("Seat " + seat + " does not exist");

		}
	}

	// Free up a seat
	public void freeSeat(String seat) {
		if (seat.startsWith("A")) {
			sectionASeats.add(seat);
		} else if (seat.startsWith("B")) {
			sectionBSeats.add(seat);
		}
	}

	// Mark a seat as occupied
	public void occupySeat(String seat) {
		if (seat.startsWith("A")) {
			sectionASeats.remove(seat);
		} else if (seat.startsWith("B")) {
			sectionBSeats.remove(seat);
		}
	}

	// Check if a seat is occupied
	public boolean isSeatOccupied(String seat) {
		return !sectionASeats.contains(seat) && !sectionBSeats.contains(seat);
	}
}