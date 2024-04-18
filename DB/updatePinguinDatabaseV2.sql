USE `Pinguin_Library`;

ALTER TABLE user ADD birth_date DATE; 
ALTER TABLE user ADD phone VARCHAR(50);

DROP TABLE IF EXISTS Resources;
CREATE TABLE IF NOT EXISTS Resources (
    resource_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    type VARCHAR(30) NOT NULL,
    title VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    quantity_loaned INT NOT NULL,
    author_id VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN DEFAULT 0,
    FOREIGN KEY (author_id)
        REFERENCES author (id)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS Songs;
CREATE TABLE IF NOT EXISTS Songs (
    resource_id INT NOT NULL,
    duration FLOAT NOT NULL,
    FOREIGN KEY (resource_id)
        REFERENCES Resources (resource_id)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS Video_recordings;
CREATE TABLE IF NOT EXISTS Video_recordings (
    resource_id INT NOT NULL,
    resolution varchar(100) NOT NULL,
    FOREIGN KEY (resource_id)
        REFERENCES Resources (resource_id)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS Essays;
CREATE TABLE IF NOT EXISTS Essays (
    resource_id INT NOT NULL,
    academyc_level varchar(50) NOT NULL,
    FOREIGN KEY (resource_id)
        REFERENCES Resources (resource_id)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS Loans;
CREATE TABLE IF NOT EXISTS Loans (
    loan_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    request_date DATE NOT NULL,
    return_date DATE NOT NULL,
    status VARCHAR(45) NOT NULL DEFAULT 'REQUESTED',
    user_id VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN DEFAULT 0,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
)  ENGINE=INNODB;

DROP TABLE IF EXISTS loan_resources;
CREATE TABLE IF NOT EXISTS loan_resources (
    loan_id INT NOT NULL,
    resource_id INT NOT NULL,
    type VARCHAR(30) NOT NULL,
    FOREIGN KEY (loan_id)
        REFERENCES Loans (loan_id),
    FOREIGN KEY (resource_id)
        REFERENCES Resources (resource_id)
)  ENGINE=INNODB;
