-- liquibase formatted sql

-- changeset sergei:1
CREATE TABLE app_user_table(
       id SERIAL primary key,
       telegram_user_id BIGINT
);