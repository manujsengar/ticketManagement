package com.ticketManagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ticketManagement.model.Section;

class TicketRepositoryTest {

	private TicketRepository ticketRepository;

	@BeforeEach
	void setUp() {
		ticketRepository = new TicketRepository();
	}

	@Test
	void allocateSeat_ShouldReturnFirstAvailableSeat() {
		String seat = ticketRepository.allocateSeat(Section.A);
		assertNotNull(seat);
		assertTrue(seat.startsWith("A"));
	}

	@Test
	void freeSeat_ShouldMakeSeatAvailableAgain() {
		String seat = ticketRepository.allocateSeat(Section.A);
		ticketRepository.freeSeat(seat);
		String newSeat = ticketRepository.allocateSeat(Section.A);
		assertEquals(seat, newSeat);
	}

	@Test
	void isSeatOccupied_ShouldReturnTrueForOccupiedSeat() {
		String seat = ticketRepository.allocateSeat(Section.A);
		assertTrue(ticketRepository.isSeatOccupied(seat));
	}
}