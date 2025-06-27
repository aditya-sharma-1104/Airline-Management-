# Airline-Management-
✈️ Airline Reservation System
A simple console-based airline reservation system in Java that allows users to:

Reserve a seat

Cancel a reservation

Display all passenger records

Save records to a text file

📁 File Structure
AirlineSystem.java: Main Java file containing all functionalities.

mufti_records.txt: Output file storing passenger records (created upon exit).

🛠 Features
1. Reservation
Collects passenger details: Passport number, Name, Email, Destination.

Automatically assigns the next available seat.

Prevents duplicate reservations using passport number validation.

Seat numbers follow the format: A-<number>.

2. Cancellation
Deletes a passenger record using their passport number.

3. Display Records
Displays all current reservations in a formatted output.

4. Save to File
On exit, all reservation records are saved to mufti_records.txt.
