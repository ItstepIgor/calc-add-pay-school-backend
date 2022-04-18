--liquibase formatted sql

--changeset igor:1

INSERT INTO people (id, address, first_name, patronymic, personnel_number, phone_number, sur_name)
VALUES (1, 'пр-т Московский', 'Иван', 'Иванович', '111', '+375291111111', 'Иванов');
--changeset igor:2
INSERT INTO users (id, password, role, people_id, login)
VALUES (1, '$2y$10$fFKZN3cMmjkZ2xCNdT745uKzAAvqRAfyW0mlD7W0Qp.6gDX868nX6', 'ADMIN', 1, 'director');
--changeset igor:3
INSERT INTO add_pay_type (id, add_pay_type_name)
VALUES (1, 'Премия'),
       (2, 'За характер труда'),
       (3, 'Стимулирующая надбавка');