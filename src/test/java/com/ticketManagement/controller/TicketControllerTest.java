package com.ticketManagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketManagement.model.Section;
import com.ticketManagement.model.Ticket;
import com.ticketManagement.model.User;
import com.ticketManagement.service.TicketService;

@WebMvcTest(value=TicketController.class)
class TicketControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private TicketService ticketService;


 private final ObjectMapper objectMapper = new ObjectMapper();

 @Test
 void purchaseTicket_ShouldReturnCreatedTicket() throws Exception {
     Ticket ticket = new Ticket(null, UUID.randomUUID(), null, new User("John", "Doe", "john.doe@example.com"), 5.0, null, Section.A, null);
     when(ticketService.purchaseTicket(any(Ticket.class))).thenReturn(ticket);
     mockMvc.perform(post("/api/tickets/purchase")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(ticket)))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.user.firstName").value("John"));
 }

 @Test
 void removeUser_ShouldReturnSuccessMessage() throws Exception {
     mockMvc.perform(delete("/api/tickets/remove/{ticketId}", UUID.randomUUID().toString()))
             .andExpect(status().isOk())
             .andExpect(content().string("Ticket removed successfully."));
 }

 @Test
 void modifySeat_ShouldReturnSuccessMessage() throws Exception {
     mockMvc.perform(put("/api/tickets/modify-seat/{ticketId}", UUID.randomUUID().toString())
             .param("newSeat", "A2"))
             .andExpect(status().isOk())
             .andExpect(content().string("Seat updated successfully."));
 }
}