DELETE
FROM users;

DELETE
FROM folders;

DELETE
FROM modules;

DELETE
FROM module_folder;

DELETE
FROM module_items;

ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE folders_id_seq RESTART WITH 1;
ALTER SEQUENCE modules_id_seq RESTART WITH 1;
ALTER SEQUENCE module_items_id_seq RESTART WITH 1;

INSERT INTO users (username, email, password, role)
VALUES ('test', 'test@mail.ru', '$2a$12$gk3POTMYLu/3wZ7EOZwUoO8qAKCZ4sYOm6UXzbnvssCK6inEpgyLa', 'USER');

INSERT INTO folders(title, description, user_id)
VALUES ('folder 1', 'description 1', 1),
       ('folder 2', 'description 2', 1),
       ('folder 3', 'description 3', 1),
       ('folder 4', 'description 4', 1),
       ('folder 5', 'description 5', 1),
       ('folder 6', 'description 6', 1),
       ('folder 7', 'description 7', 1),
       ('folder 8', 'description 8', 1),
       ('folder 9', 'description 9', 1),
       ('folder 10', 'description 10', 1);

INSERT INTO modules(title, description, user_id)
VALUES ('module 1', 'description 1', 1),
       ('module 2', 'description 2', 1),
       ('module 3', 'description 3', 1),
       ('module 4', 'description 4', 1),
       ('module 5', 'description 5', 1),
       ('module 6', 'description 6', 1),
       ('module 7', 'description 7', 1),
       ('module 8', 'description 8', 1),
       ('module 9', 'description 9', 1),
       ('module 10', 'description 10', 1);

INSERT INTO module_items(title, description, code, module_id)
VALUES ('module_item 1', 'description 1', 'code 1', 1),
       ('module_item 2', 'description 2', 'code 2', 1),
       ('module_item 3', 'description 3', 'code 3', 1);

INSERT INTO module_folder(module_id, folder_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);