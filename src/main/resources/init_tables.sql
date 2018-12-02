CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100),
  role VARCHAR(100),
  is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE tasks (
  id BIGSERIAL PRIMARY KEY,
  status BOOLEAN DEFAULT TRUE,
  expired_date DATE,
  user_id INT REFERENCES users(id)
);

INSERT INTO users(name, role) VALUES ('Admin', 'ADMIN');