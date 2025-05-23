CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE,
    email      VARCHAR(255) UNIQUE,
    password   VARCHAR(255),
    role       VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS modules
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    user_id     BIGINT,
    CONSTRAINT fk_user_module FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS module_items
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    code        TEXT,
    module_id   BIGINT,
    FOREIGN KEY (module_id) REFERENCES modules (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS folders
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    user_id     BIGINT,
    CONSTRAINT fk_user_folder FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS module_folder
(
    module_id BIGINT,
    folder_id BIGINT,
    PRIMARY KEY (module_id, folder_id),
    CONSTRAINT fk_module_module_folder FOREIGN KEY (module_id) REFERENCES modules (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_folder_module_folder FOREIGN KEY (folder_id) REFERENCES folders (id) ON DELETE CASCADE ON UPDATE CASCADE
);