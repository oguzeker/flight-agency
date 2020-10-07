
-- AIRLINE
INSERT INTO AIRLINE (name, short_code) VALUES ('KLM Royal Dutch Airlines', 'KL');
INSERT INTO AIRLINE (name, short_code) VALUES ('Ryan International Airlines', 'RD');
INSERT INTO AIRLINE (name, short_code) VALUES ('Qatar Airways', 'QR');
INSERT INTO AIRLINE (name, short_code) VALUES ('Air France', 'AF');
INSERT INTO AIRLINE (name, short_code) VALUES ('United Airlines', 'UA');

-- AIRPORT
INSERT INTO AIRPORT (city, name, short_code) VALUES ('Istanbul', 'Istanbul Airport', 'IST');
INSERT INTO AIRPORT (city, name, short_code) VALUES ('Amsterdam', 'Amsterdam Airport Schiphol', 'AMS');
INSERT INTO AIRPORT (city, name, short_code) VALUES ('Cairo', 'Cairo International Airport', 'CAI');

-- ROUTE
INSERT INTO ROUTE (airline_id, short_code, from_airport_id, to_airport_id) VALUES (1, 'IA', 1, 2);
INSERT INTO ROUTE (airline_id, short_code, from_airport_id, to_airport_id) VALUES (1, 'IA', 2, 1);

-- FLIGHT
INSERT INTO FLIGHT (arrival_date, capacity, departure_date, unit_price, current_price, route_id, current_percentile_of_flight_capacity, created_on, updated_on) VALUES ('2020-11-12 14:03:01.146', 20, '2020-11-11 14:03:01.146', 10, 10, 1, 10, '2020-09-09 14:03:01.146', '2020-09-09 14:03:01.146');
INSERT INTO FLIGHT (arrival_date, capacity, departure_date, unit_price, current_price, route_id, current_percentile_of_flight_capacity, created_on, updated_on) VALUES ('2020-11-12 14:03:01.146', 20, '2020-11-11 14:03:01.146', 10, 10, 2, 10, '2020-09-09 14:03:01.146', '2020-09-09 14:03:01.146');
