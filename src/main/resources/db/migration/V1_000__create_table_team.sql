create table TB_TEAM(
    id bigint primary key auto_increment,
    members integer,
    foundationDate date,
    name varchar(200),
    created_date timestamp not null,
    last_updated_date timestamp
)