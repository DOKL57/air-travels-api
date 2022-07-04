create table "company"
(
    id   uuid not null primary key,
    name text not null
);

create table "trip"
(
    id         uuid not null primary key,
    company_id uuid not null references company (id),
    town_from  text not null,
    town_to    text not null,
    time_in    timestamp not null,
    time_out   timestamp not null
);

create table "passenger"
(
    id              uuid not null primary key,
    name            text not null,
    surname         text not null,
    passport_number text not null,
    date_of_birth   date not null,
    phone_number    text not null
);

create table "pass_in_trip"
(
    id           uuid    not null primary key,
    trip_id      uuid    not null references trip (id),
    passenger_id uuid    not null references passenger (id),
    seat_number  integer not null
);






