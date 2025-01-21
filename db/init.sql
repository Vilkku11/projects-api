CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO projects (name, description) VALUES
('Test Project 1', 'This is a test project'),
('Test Project 2', 'This is a second test project');