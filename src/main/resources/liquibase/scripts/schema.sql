-- liquibase formatted sql

-- changeset alex turaev:1
drop table if exists items;

create table if not exists items(
    id bigserial primary key
    );