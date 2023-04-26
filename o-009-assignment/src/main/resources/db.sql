create table branches
(
    pk   bigserial primary key,
    name varchar(256) unique
);

create type enum_gender as enum ('MALE', 'FEMALE');

create table students
(
    pk         bigserial primary key,
    first_name varchar(256),
    last_name  varchar(256),
    gender     enum_gender,

    branch_pk  bigint default null,

    constraint cst_branch_pk_fk foreign key (branch_pk) references branches (pk) on delete set null
);