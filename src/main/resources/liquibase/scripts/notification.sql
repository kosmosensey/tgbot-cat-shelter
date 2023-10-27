-- liquibase formatted sql

-- changeset ACTION:1
CREATE TABLE shelter_data(
id bigserial primary key,
chat_id bigint,
text varchar,
exec_date timestamp
)