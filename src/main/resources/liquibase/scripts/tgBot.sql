-- liquibase formatted sql

--changeset anton:1
-- Создание таблицы "users" для хранения информации о пользователях
CREATE TABLE users(
    id  BIGINT generated by default as identity primary key, -- Уникальный идентификатор пользователя
    telegram_id   BIGINT   NOT NULL, -- Идентификатор пользователя в Telegram
    name    TEXT, -- Имя пользователя
    phone_number  TEXT, -- Номер телефона пользователя
    car_number    TEXT, -- Номер автомобиля пользователя
    city    TEXT -- Город проживания пользователя
);

-- Создание таблицы "animals" для хранения информации о животных
CREATE TABLE animals
(
    id        BIGINT generated by default as identity primary key, -- Уникальный идентификатор животного
    name TEXT     NOT NULL, -- Имя животного
    pet_type  SMALLINT NOT NULL, -- Тип животного (например, кошка, собака и т. д.)
    color     SMALLINT NOT NULL, -- Цвет животного
    sex       SMALLINT NOT NULL -- Пол животного (например, самец, самка)
);

-- Создание таблицы "adopters" для хранения информации о приемных родителях животных
CREATE TABLE adopters
(
    id         BIGINT generated by default as identity primary key, -- Уникальный идентификатор приемного родителя
    user_id    BIGINT, -- Идентификатор пользователя
    animal_id  BIGINT NOT NULL, -- Идентификатор животного
    shelter_id BIGINT NOT NULL -- Идентификатор приюта
);

-- Создание таблицы "shelters" для хранения информации о приютах животных
CREATE TABLE shelters
(
    id                 BIGINT generated by default as identity primary key, -- Уникальный идентификатор приюта
    address_shelter    TEXT     NOT NULL, -- Адрес приюта
    time_work          TEXT     NOT NULL, -- Время работы приюта
    driving_directions TEXT     NOT NULL, -- Маршрут проезда
    phone_shelter      TEXT     NOT NULL, -- Телефон приюта
    phone_security     TEXT     NOT NULL, -- Телефон охраны приюта
    shelter_type       SMALLINT NOT NULL -- Тип приюта (кошек, собак и т. д.)
);

-- Создание таблицы "reports" для хранения информации об отчётах от пользователей
CREATE TABLE reports
(
    id                    BIGINT generated by default as identity primary key, -- Уникальный идентификатор номера отчёта
    user_id               BIGINT   NOT NULL, -- Идентификатор пользователя
    date_report           DATE     NOT NULL, -- Дата получения отчёта
    status_report         SMALLINT NOT NULL, -- Статус отчёта
    date_end_of_probation DATE, -- Дата окончания испытательного срока
    link_picture          BYTEA, -- фото животного
    report_text           TEXT -- Текст отчёта от пользователя
);