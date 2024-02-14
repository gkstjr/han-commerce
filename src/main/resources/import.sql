insert into users (USER_ID, USERNAME, PASSWORD, CITY , STREET , ZIPCODE) values (3000, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', '경기도' , '시흥시' , '봉우재로');

insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

-- insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (3000, 'ROLE_ADMIN');

insert into CATEGORY (category_id,NAME) values (1,'도서');
insert into CATEGORY (category_id,NAME) values (2,'가구');
insert into CATEGORY (category_id,NAME) values (3,'의류');
insert into CATEGORY (category_id,NAME) values (4,'생필품');
insert into CATEGORY (category_id,NAME) values (5,'전자기기');
insert into CATEGORY (category_id,NAME) values (6,'기타');


