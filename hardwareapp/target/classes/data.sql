
delete from review;
delete from hardware;
delete from user_authority;
delete from user;
delete from authority;


insert into hardware(id, name, code, price, `type`, stock)
values (1, 'Asus TUF RTX 3080', '111111', 1599.0, 'GPU', 5),
       (2, 'EVGA XC3 RTX 3070', '222222', 1299.0, 'GPU', 0),
       (3, 'AMD Ryzen 5950X', '333333', 899.0, 'CPU', 7),
       (4, 'Samsung 980 PRO SSD 1TB', 'abc123', 299.0, 'STORAGE', 0),
       (5, 'Kingston FURY Beast DDR5 32GB', 'aBC445', 699.0, 'RAM', 6);


insert into review(id, title, `text`, rating, hardware_id)
values (1, 'testtitle1', 'testtext1', 1, 1),
       (2, 'testtitle2', 'testtext2', 2, 2),
       (3, 'testtitle3', 'testtext3', 3, 3),
       (4, 'testtitle4', 'testtext4', 4, 4),
       (5, 'testtitle5', 'testtext5', 5, 5),
       (6, 'testtitle6', 'testtext6', 1, 1),
       (7, 'testtitle7', 'testtext7', 2, 2),
       (8, 'testtitle8', 'testtext8', 3, 3),
       (9, 'testtitle9', 'testtext9', 4, 4),
       (10, 'testtitle10', 'testtext10', 5, 5);


insert into user(id, username, `password`)
values (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'),
       (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'),
       (3, 'deleter', '$2a$12$eH6yy7f35mHt0mQLS6G1Z.5hpsRp5HSBnKiwIJ/Fn/cY/h0dYGi5W');

insert into authority(id, authority_name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_DELETER');

insert into user_authority(user_id, authority_id)
values (1, 2),
       (2, 1),
       (3, 3);