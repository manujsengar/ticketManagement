
🎟️ Ticket Management System

This is a Ticket Management System built using Spring Boot. It allows users to purchase tickets, manage seating, and retrieve ticket details. The project uses in-memory storage for tickets and provides simple seat allocation and management by section.

Postman Collection : https://api.postman.com/collections/30303764-4b683b14-f2a0-410b-abd8-98052eaf5992?access_key=PMAT-01JBA635D0BP6GXVZT6Q3RTWZX

🛠️ Features

	•	Purchase Tickets: Automatically allocates the next available seat.
	•	Modify Seats: Change the allocated seat of an existing ticket.
	•	Remove Tickets: Free up seats and remove users.
	•	Get Users by Section: List all users by seat section (e.g., A or B).
	•	In-Memory Storage: Uses ConcurrentHashMap to manage tickets and seat availability.

📂 Project Structure
src

├── main

│   ├── java

│   │   ├── com.ticketManagement

│   │   │   ├── controller       // REST controllers

│   │   │   ├── model            // Models for Ticket, User, Section

│   │   │   ├── repository       // Repository for managing tickets

│   │   │   ├── service          // Business logic and service classes

│   │   │   └── TicketManagementApplication.java // Main app entry point

│   └── resources

│       └── application.properties // App configurations

├── test

│   └── java                     // Unit and integration tests

└── README.md                    // Project documentation

🚀 Getting Started

Prerequisites

	•	Java 11 or later
	•	Maven installed
	•	An IDE (e.g., IntelliJ IDEA, Eclipse)

 📋 API Endpoints

1. Purchase a Ticket

	•	Endpoint: /api/tickets/purchase
	•	Method: POST
	•	Request Body:
{
  "event": "FRANCE",
  "user": {
    "firstName": "Manuj",
    "lastName": "Sengar",
    "email": "manuj.sengar@hotmail.com"
  },
  "price": 5.0,
  "section": "A"
}
	•	Response:
{
  "ticketId": "123e4567-e89b-12d3-a456-426614174000",
  "seat": "A1",
  "user": {
    "firstName": "Manuj",
    "lastName": "Sengar",
    "email": "manuj.sengar@hotmail.com"
  }
}
2. Remove a Ticket

	•	Endpoint: /api/tickets/remove/{ticketId}
	•	Method: DELETE
	•	Response: Ticket removed successfully.

3. Modify Seat

	•	Endpoint: /api/tickets/modify-seat/{ticketId}
	•	Method: PUT
	•	Parameters:
	•	newSeat: The new seat number (e.g., A2)
	•	Response: Seat updated successfully.

4. Get Users by Section

	•	Endpoint: /api/tickets/section/{section}
	•	Method: GET
	•	Response: 
[
  {
    "user": {
      "firstName": "Manuj",
      "lastName": "Sengar",
      "email": "manuj.sengar@hotmail.com"
    },
    "seat": "A1"
  }
]

⚙️ Technologies Used

	•	Java 11
	•	Spring Boot
	•	JUnit (for unit testing)
	•	Maven
	•	ConcurrentHashMap (for thread-safe in-memory storage)

Requirements : 

Functional Requirements:

	1.	Ticket Purchase: Users should be able to purchase a ticket with origin, destination, user details, and price.
	2.	Seat Allocation: Automatic seat allocation based on sections (A or B) with a limited number of seats.
	3.	Ticket Details Retrieval: Ability to retrieve ticket details by seat number.
	4.	User List by Section: List users allocated to specific train sections (A or B).
	5.	Ticket Cancellation: Remove a ticket and free up the allocated seat.
	6.	Seat Modification: Change the allocated seat for a user, respecting seat availability.

Non-Functional Requirements:

	1.	Performance: Fast API responses for ticket purchase, retrieval, and modification, especially with high request volumes.
	2.	Reliability: Ensure consistency in seat allocation and ticket removal operations to prevent data issues.
	3.	Scalability: In-memory data handling for this app is simple, but future scaling might benefit from using a persistent database.
	4.	Usability: Clear API documentation for ease of integration, using Swagger annotations for API descriptions.
	5.	Maintainability: Modular service and repository layers for easy updates and debugging.
	6.	Security: Basic input validation and secure user data handling, especially for email formats.


Improvisations: 

Functional Improvements

	1.	Enhanced Seat Selection: Allow users to pick seats based on preference, such as window or aisle, if available.
	2.	Real-time Ticket Availability: Display remaining seats in each section to users before booking.
	3.	Waitlist Feature: Implement a waitlist if sections are full, with notifications if seats open up.
	4.	Ticket Reservation: Allow users to reserve seats temporarily, holding seats until payment is confirmed.

Non-Functional Improvements

	1.	Scalability: Use load balancing and distributed databases to handle large user volumes.
	2.	Performance Optimization: Implement caching (e.g., Redis) for frequent data access like seat availability.
	3.	Enhanced Validation and Security
	4.	Logging and Monitoring: Enable centralized logging and real-time alerts for issue detection and diagnostics.
	5.  Persistent Database Integration: Introduce JPA or Hibernate to support relational data and ease future migrations.


	

