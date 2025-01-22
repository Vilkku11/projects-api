CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users {
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    fk_role_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
};

CREATE TABLE roles {
    id SERIAL PRIMARY KEY,
    rolename VARCHAR(255)
};



INSERT INTO projects (name, description) VALUES
('Test Project 1', 'This is a test project'),
('Test Project 2', 'This is a second test project');