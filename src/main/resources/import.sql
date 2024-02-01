insert into users (USER_ID, USERNAME, PASSWORD, CITY , STREET , ZIPCODE) values (3000, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', '경기도' , '시흥시' , '봉우재로');

insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

-- insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (3000, 'ROLE_ADMIN');