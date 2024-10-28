
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

 💡 Future Enhancements

	•	Add database support (e.g., MySQL, MongoDB) for persistence.
	•	Implement user authentication and authorization.
	•	Add seat reservation timeout functionality.
