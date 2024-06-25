### Proyecto Final Wallet Personal

```` SQL
CREATE DATABASE IF NOT EXISTS wallet_personal;
USE wallet_personal;

-- Crear la tabla 'user'
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(25) NOT NULL,
    lastname VARCHAR(35) NOT NULL,
    email VARCHAR(59) NOT NULL UNIQUE,
    password VARCHAR(25),
    balance DOUBLE NOT NULL DEFAULT 0.00
);

-- Crear la tabla 'transactions'
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,     
    amount DOUBLE NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);
ALTER TABLE users MODIFY COLUMN account_non_expired BOOLEAN DEFAULT TRUE;
ALTER TABLE users MODIFY COLUMN account_non_locked BOOLEAN DEFAULT TRUE;
ALTER TABLE users MODIFY COLUMN credentials_non_expired BOOLEAN DEFAULT TRUE;
ALTER TABLE users MODIFY COLUMN enabled BOOLEAN DEFAULT TRUE;

```
