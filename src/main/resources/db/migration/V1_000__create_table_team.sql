create table TB_TEAM(
    id bigint primary key auto_increment,
    members integer,
    foundation_date date,
    name varchar(200),
    created_date timestamp not null,
    last_modified_date timestamp
)