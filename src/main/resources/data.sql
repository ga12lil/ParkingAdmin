INSERT INTO owners(first_name, last_name, phone, email) VALUES
('Ivan','Ivanov','+79530008866','ivan@example.com'),
('Petr','Petrov','+79780008877','petr@example.com');


INSERT INTO cars(owner_id, license_plate, make, model, color) VALUES
(1,'a777aa777','Toyota','Corolla','Silver'),
(2,'a777aa161','BMW','X3','Black');


INSERT INTO parking_spots(spot_number, level, is_available) VALUES
('A1',0,TRUE), ('A2',0,TRUE), ('B1',1,TRUE), ('B2',1,TRUE);


-- example reservation
INSERT INTO reservations(spot_id, car_id, owner_id, start_time, end_time, paid) VALUES
(1,1,1,now() - interval '2 hours', now() + interval '2 hours', false);