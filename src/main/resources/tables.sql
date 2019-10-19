-- postgres
create table post(
    id   bigserial primary key,
    name varchar(255) not null,
    content varchar(1024) not null
)
