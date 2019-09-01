insert into users (id, email, login, role, password) values (hibernate_sequence.nextval, '', 'admin', 'ADMIN', '$2a$10$qQGhKycCWv.450hob/UCN.z4QKOE3oUCM7daw0XWQxWHLHI/nzDDG');
insert into users (id, email, login, role, password) values (hibernate_sequence.nextval, '', 'agustinventura', 'USER', '$2a$10$RZwwsVu2ZZcUVn37sJZRCulJxecvHRoKL80yS3HCLDkqEN9GaLMkm');
insert into todo_lists (id, description) values (hibernate_sequence.nextval, 'Admin list');
insert into todo_lists (id, description) values (hibernate_sequence.nextval, 'Agustin list');
insert into users_todo_lists(user_id, list_id) values (select id from users where login = 'admin', select id from todo_lists where description = 'Admin list');
insert into users_todo_lists(user_id, list_id) values (select id from users where login = 'agustinventura', select id from todo_lists where description = 'Agustin list');
