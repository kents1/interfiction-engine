CREATE TABLE credential (
    email_address VARCHAR(255) NOT NULL,
    password CHAR(60) NOT NULL,
    PRIMARY KEY (email_address)
);

CREATE TABLE account (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    email_address VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    terms_conditions_accept TINYINT(1) DEFAULT 0,
    country VARCHAR(6) NULL,
    enabled TINYINT(1) DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (email_address) REFERENCES credential (email_address)
);

CREATE TABLE role (
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE account_role_map (
    account_id BIGINT UNSIGNED NOT NULL,
    role_name VARCHAR(15) NOT NULL,
    PRIMARY KEY (account_id, role_name),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (role_name) REFERENCES role (name)
);

-- Security
INSERT INTO role VALUES ('USER');
INSERT INTO role VALUES ('ADMINISTRATOR');
