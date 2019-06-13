insert into usr(id, username, password, active, avatar, email, activation_code)
    values (1, 'admin', 'a', true, null, 'abc@abv', null);

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN'), (1, 'MODERATOR');


insert into usr(id, username, password, active, avatar, email, activation_code)
values (2, 'moder', 'a', true, null, 'abc@abv', null);

insert into user_role (user_id, roles)
values (2, 'USER'), (2, 'MODERATOR');

insert into usr(id, username, password, active, avatar, email, activation_code)
values (3, 'user', 'a', true, null, 'abc@abv', null);

insert into user_role (user_id, roles)
values (3, 'USER');