use master;
create database flight;

-- user table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(10) NOT NULL,
    user_type VARCHAR(10) NOT NULL
);


-- Creating the aircraft table
CREATE TABLE aircraft (
    aircraft_id INT PRIMARY KEY AUTO_INCREMENT,
    aircraft_name VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL 
);

-- Creating the flight table
CREATE TABLE flights (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_date DATE NOT NULL,
    sourcee VARCHAR(10) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    aircraft_id INT NOT NULL,
    FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id)
);


-- Creating the booking table
CREATE TABLE booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    class_type VARCHAR(10) NOT NULL,
    seats_booked INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

-- Adding a new user
INSERT INTO users (user_id, username, password, user_type)
VALUES (1, 'ahmed', 'ahmed333', 'admin');

-- Adding a new aircraft
INSERT INTO aircraft ( aircraft_name, total_seats)
VALUES ('myaircraft', 100);

-- Adding a new flight
INSERT INTO flights (flight_id, flight_date, sourcee, destination, aircraft_id)
VALUES (1, '2023-05-09', 'cairo', 'aswan', 1);

-- Updating an aircraft's details
UPDATE aircraft SET total_seats = 300 WHERE aircraft_id = 1;

-- Updating a flight's details
UPDATE flights SET destination = 'usa' WHERE flight_id = 1;

-- Showing a list of available flights that satisfy certain criteria
SELECT f.flight_id, f.flight_date, f.sourcee, f.destination, a.aircraft_name, a.total_seats FROM flights f INNER JOIN aircraft a ON f.aircraft_id = a.aircraft_id WHERE f.flight_date = '2023-06-01' AND f.sourcee = 'cairo' AND f.destination = 'usa' AND a.total_seats >= 2;

-- Booking a flight
INSERT INTO booking (booking_id, user_id, flight_id, class_type, seats_booked) VALUES (1, 1, 1, 'business', 2);

-- Cancelling a booking
DELETE FROM booking WHERE booking_id = 1;

-- Changing flight class
UPDATE booking SET class_type = 'first class' WHERE booking_id = 1;