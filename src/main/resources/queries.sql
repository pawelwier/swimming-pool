/*ROLES*/
insert into swimming_pool.role (role_id, role) values (1, 'ADMIN');
insert into swimming_pool.role (role_id, role) values (2, 'USER');

/*USERS*/
insert into swimming_pool.user (id, active, name, password)
values (1, 1, 'admin', '$2a$10$dRfn6Wqf7xpp.AyW/Yd1geBMJpQ0WwJ0CCqoYsF8phQpe0tGrheK2');

insert into swimming_pool.user (id, active, name, password)
values (2, 1, 'user', '$2a$10$lsOQ3okaEUQDquNYSA4MSOBd3g7XDKXts7SSAygeJ2urI76XDk/pi');

/*Add roles to user*/
insert into swimming_pool.user_role (user_id, role_id) values (1,1);
insert into swimming_pool.user_role (user_id, role_id) values (1,2);
insert into swimming_pool.user_role (user_id, role_id) values (2,2);