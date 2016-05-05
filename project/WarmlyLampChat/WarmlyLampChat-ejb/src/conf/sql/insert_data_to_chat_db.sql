DROP VIEW IF EXISTS `warmly_lamp_chat_chat_db`.`users_in_roles_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `warmly_lamp_chat_chat_db`.`users_in_roles_view` AS select `u`.`NICKNAME` AS `LOGIN`,`u`.`PASSWORD` AS `PASSWORD`,`r`.`NAME` AS `ROLE_NAME` from (`warmly_lamp_chat_chat_db`.`users` `u` join `warmly_lamp_chat_chat_db`.`roles` `r` on((`u`.`ROLE_ID` = `r`.`ID`)));

INSERT INTO roles(NAME) VALUES ('Модератор'),('Рекламодатель'),('Пользователь');
INSERT INTO users(FIO,NICKNAME,PASSWORD,ROLE_ID) VALUES ('Макаров Сергей Андреевич','makar',SHA2('makar',256),3),('Модератор','moderator',SHA2('moderator',256),1),('Рекламодатель','advertiser',SHA2('advertiser',256),2);