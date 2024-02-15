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

insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(1,5000,1,'임의 상품 설명','자기관리론',1);
insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(2,2000,8,'임의 상품 설명','책상',2);
insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(3,3000,2,'임의 상품 설명','COOR',3);
insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(4,7000,1,'임의 상품 설명','칫솔',4);
insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(5,9000,1,'임의 상품 설명','에어팟',5);
insert into ITEM(item_id , price , stock_quantity , content , name , category_id) values(6,10000,3,'임의 상품 설명','개발은 취미로',1);
