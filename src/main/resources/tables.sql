-- postgres
create table post(
    id   bigserial primary key,
    name varchar(255) not null,
    content varchar(1024) not null
);

create table user_account(
    id   bigserial primary key,
    login varchar(64) not null
);

create table account_api_token(
    id   bigserial primary key,
    token varchar(64) not null,
    user_account_id bigint not null,

    constraint user_account_id_fkey foreign key (user_account_id)
    references user_account (id)
);
