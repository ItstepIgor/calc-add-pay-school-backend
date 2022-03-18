INSERT INTO people (id, address, first_name, patronymic, personnel_number, phone_number, sur_name)
VALUES (1, 'пр-т Московский', 'Иван', 'Иванович', '111', '+375291111111', 'Иванов')
ON CONFLICT DO NOTHING;
INSERT INTO users (id, password, role, people_id)
VALUES (1, '111', 'ADMIN', 1)
ON CONFLICT DO NOTHING;
INSERT INTO add_pay_type (id, add_pay_type_name)
VALUES (1, 'Премия'),
       (2, 'За характер труда'),
       (3, 'Стимулирующая надбавка')
ON CONFLICT DO NOTHING;