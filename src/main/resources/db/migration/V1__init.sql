CREATE TABLE clients (id serial PRIMARY KEY, name varchar(100) not null, phone varchar(100) not null);

insert into clients (name, phone) values ('Anna', '11-11-11'), ('Kate', '22-22-22'), ('Max', '33-33-33');


CREATE TABLE rooms (id serial PRIMARY KEY, number INT NOT NULL CHECK (number > 0), price DECIMAL(10, 2) NOT NULL CHECK (price > 0));

insert into rooms (number, price) values (201, 3000), (202, 3000), (203, 3000);

CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          client_id BIGINT NOT NULL,
                          room_id BIGINT NOT NULL,
                          nights INT NOT NULL,
                          total_price DECIMAL(10, 2) NOT NULL,
                          FOREIGN KEY (client_id) REFERENCES clients(id),
                          FOREIGN KEY (room_id) REFERENCES rooms(id)
);

