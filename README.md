🛠️ Core Feature: Admin-User Order Synchronization
This project implements a real-time data flow between two user roles:

User Role: Can place orders and view their personalized order history.

Admin Role: Can view all system orders and update statuses.

Synchronization: Uses a shared MySQL backend with Spring Data JPA to ensure that as soon as an Admin updates an order, the change is reflected on the User's dashboard.
