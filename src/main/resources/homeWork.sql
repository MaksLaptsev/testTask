--Task 1
--Вывести к каждому самолету класс обслуживания и количество мест этого класса

select aircraft_code, fare_conditions, count(seat_no) from seats
group by aircraft_code, fare_conditions order by aircraft_code,fare_conditions;

--Task 2
--Найти 3 самых вместительных самолета (модель + кол-во мест)

select (aircrafts_data.model::jsonb->'ru'),count(seat_no) from aircrafts_data
inner join seats s on aircrafts_data.aircraft_code = s.aircraft_code
group by (aircrafts_data.model::jsonb->'ru') order by count(seat_no) desc limit 3;

--Task 3
--Вывести код,модель самолета и места не эконом класса для самолета 'Аэробус A321-200' с сортировкой по местам

select aircrafts_data.aircraft_code,(aircrafts_data.model::jsonb->>'ru'),seats.seat_no from aircrafts_data
inner join seats on aircrafts_data.aircraft_code = seats.aircraft_code
where seats.fare_conditions != 'Economy' and (aircrafts_data.model::jsonb->>'ru' = 'Аэробус A321-200')
group by aircrafts_data.aircraft_code, (aircrafts_data.model::jsonb->'ru'), seats.seat_no order by seat_no;

--Task 4
--Вывести города в которых больше 1 аэропорта ( код аэропорта, аэропорт, город)

select a.airport_code, a.airport_name::jsonb->>'ru',a.city::jsonb->>'ru'
from(
        select city from airports_data
        group by city having count(airport_code) > 1
    )as cities
        join airports_data as a on cities.city = a.city
group by a.airport_code, a.airport_name::jsonb->>'ru', a.city::jsonb->>'ru' order by a.city;

--Task 5
-- Найти ближайший вылетающий рейс из Екатеринбурга в Москву, на который еще не завершилась регистрация

select ff.flight_no,ff.scheduled_departure,ff.scheduled_arrival,ff.departure_airport,ff.arrival_airport,ff.status  from
    (select status from flights
     where status = 'Scheduled' or status = 'On Time' or status = 'Delayed'
     group by status
    )as f
        join flights as ff on ff.status = f.status
where ff.departure_airport in (
    select airport_code from airports_data
    where city::jsonb->>'ru' = 'Екатеринбург'
    group by airport_code
) and ff.arrival_airport in(
    select airport_code from airports_data
    where city::jsonb->>'ru' = 'Москва'
    group by airport_code
) and ff.scheduled_departure > bookings.now()
group by ff.flight_no, ff.scheduled_departure, ff.scheduled_arrival, ff.departure_airport, ff.arrival_airport, ff.status
order by ff.scheduled_departure limit 1;

--только потом заметил табличку flights_v :D
select f.flight_id,f.flight_no,f.scheduled_departure,f.scheduled_departure_local,f.scheduled_arrival,
       f.scheduled_arrival_local,f.scheduled_duration,f.departure_airport,f.departure_airport_name,f.departure_city,
       f.arrival_airport,f.arrival_airport_name,f.arrival_city,f.status,f.aircraft_code from flights_v f
where
        f.departure_city = 'Екатеринбург' and
        f.arrival_city = 'Москва' and(
            f.status = 'Scheduled' or f.status = 'On Time' or f.status = 'Delayed') and
        f.scheduled_departure > bookings.now()
order by f.scheduled_departure limit 1;

--Task 6
--Вывести самый дешевый и дорогой билет и стоимость ( в одном результирующем ответе)
select ticket_no,flight_id,fare_conditions,amount from (
                  select ticket_no,flight_id,fare_conditions,amount from ticket_flights
                  where amount = (select max(amount) from ticket_flights)
                  limit 1
              ) as "tf*" union (
    select ticket_no,flight_id,fare_conditions,amount from ticket_flights
    where amount = (select min(amount) from ticket_flights)
    limit 1
);

select ticket_no,flight_id,fare_conditions,amount from (
                  select ticket_no,flight_id,fare_conditions,amount from ticket_flights
                  order by amount desc
                  limit 1
              ) as "tf*" union (
    select ticket_no,flight_id,fare_conditions,amount from ticket_flights
    order by amount
    limit 1
);

--Task 7,8,9,10
-- Написать DDL таблицы Customers , должны быть поля id , firstName, LastName, email , phone. Добавить ограничения на поля ( constraints) .
-- Написать DDL таблицы Orders , должен быть id, customerId,	quantity. Должен быть внешний ключ на таблицу customers + ограничения
-- Написать 5 insert в эти таблицы
-- удалить таблицы


create schema if not exists homeTasks;
comment on schema homeTasks is 'Create homeTasks schema';
--Customers
create table if not exists homeTasks.Customers (
                                                   id bigserial primary key ,
                                                   firstName varchar(30) not null check ( firstName !='' ),
                                                   lastName varchar(30) not null check ( lastName!='' ),
                                                   email varchar not null,
                                                   phone varchar
);
--Orders
create table if not exists homeTasks.Orders(
                                               id bigserial primary key ,
                                               customerId integer references homeTasks.Customers(id),
                                               quantity integer not null check ( quantity >= 0 )
);
--Inserts
insert into homeTasks.Customers(firstName, lastName, email, phone)
values ('Gabriel','Angel','gabriel@god.com','103');
insert into homeTasks.Customers(firstName, lastName, email, phone)
values ('Mihail','Boyarskiy','Goreloc@god.com','777');
insert into homeTasks.Customers(firstName, lastName, email, phone)
values ('Chappy','Journo','platinum@god.com','999');
insert into homeTasks.Customers(firstName, lastName, email, phone)
values ('Veolla','Leanore','platinum@god.com','1');
insert into homeTasks.Customers(firstName, lastName, email, phone)
values ('Jack','Ripper','dead@hell.com','666');

insert into homeTasks.Orders(customerId, quantity)
values((select id from homeTasks.Customers where firstName = 'Gabriel'),6);
insert into homeTasks.Orders(customerId, quantity)
values((select id from homeTasks.Customers where firstName = 'Mihail'),115);
insert into homeTasks.Orders(customerId, quantity)
values((select id from homeTasks.Customers where firstName = 'Chappy'),333);
insert into homeTasks.Orders(customerId, quantity)
values((select id from homeTasks.Customers where firstName = 'Veolla'),666);
insert into homeTasks.Orders(customerId, quantity)
values((select id from homeTasks.Customers where firstName = 'Jack'),321);

--Drop all
drop schema homeTasks cascade;

-- Написать свой кастомный запрос ( rus + sql)
-- Вывести всех пассажиров,у которых указан номер телефона в контактных данных, которые летали бизнес классом на самолете Суперджет из Казани в Киров

select people.passenger_name, people.contact_data::jsonb->>'phone' phone_number from tickets people
        inner join (select BP.ticket_no,BP.flight_id,BP.boarding_no,BP.seat_no from boarding_passes BP
                    inner join (select f.* from flights_v f
                                    where f.aircraft_code = (select aircraft_code from aircrafts_data
                                                             where model::jsonb->>'ru' like '%Суперджет%') and
                                            f.status = 'Arrived' and
                                            f.departure_city = 'Казань' and
                                            f.arrival_city = 'Киров'
                                    order by f.flight_id
                                )FV on FV.flight_id = BP.flight_id
                                        inner join (select s.* from seats s
                                                    where s.fare_conditions = 'Business' and
                                                            s.aircraft_code = (select aircraft_code from aircrafts_data
                                                                               where model::jsonb->>'ru' like '%Суперджет%')
                                                    order by s.seat_no) ST on ST.seat_no = BP.seat_no
                    order by BP.ticket_no) TN on TN.ticket_no = people.ticket_no
group by people.passenger_name, people.contact_data::jsonb->>'phone';
