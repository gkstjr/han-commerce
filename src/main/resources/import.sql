insert into users (USER_ID, USERNAME, PASSWORD, CITY , STREET , ZIPCODE) values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', '경기도' , '시흥시' , '봉우재로');

insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

-- insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');

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

insert into delivery(delivery_id,status , city , street , zipcode) values(2,'READY','경기도','시흥','배곧2');
insert into delivery(delivery_id,status , city , street , zipcode) values(3,'READY','경기도','시흥','배곧3');
insert into delivery(delivery_id,status , city , street , zipcode) values(4,'READY','경기도','시흥','배곧4');
insert into delivery(delivery_id,status , city , street , zipcode) values(5,'READY','경기도','시흥','배곧5');
insert into delivery(delivery_id,status , city , street , zipcode) values(6,'READY','경기도','시흥','배곧6');
insert into delivery(delivery_id,status , city , street , zipcode) values(7,'READY','경기도','시흥','배곧7');
insert into delivery(delivery_id,status , city , street , zipcode) values(8,'READY','경기도','시흥','배곧8');
insert into delivery(delivery_id,status , city , street , zipcode) values(9,'READY','경기도','시흥','배곧9');
insert into delivery(delivery_id,status , city , street , zipcode) values(10,'READY','경기도','시흥','배곧1');

-- //2024-02-20 20:29:10.779621(H2)
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(2,2,3000,2000,'2024-02-20 20:29:10','ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(3,3,3000,3000,'2024-02-20 20:29:10','ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(4,4,3000,4000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(5,5,3000,5000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(6,6,3000,6000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(7,7,3000,6000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(8,8,3000,7000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(9,9,3000,7000,FORMATDATETIME(current_timestamp,'yyyy-MM-dd HH:mm:ss'),'ORDER');
-- insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(10,10,3000,1000,'2024-02-20 20:29:10','ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(2,2,1,2000,'2024-02-20 20:29:10','ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(3,3,1,3000,'2024-02-20 20:29:10','ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(4,4,1,4000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(5,5,1,5000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(6,6,1,6000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(7,7,1,6000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(8,8,1,7000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(9,9,1,7000,now(),'ORDER');
insert into ORDERS(order_id,delivery_id,user_id,total_price,create_date,status) values(10,10,1,1000,'2024-02-20 20:29:10','ORDER');