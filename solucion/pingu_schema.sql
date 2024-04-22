DROP DATABASE IF EXISTS pingu;
CREATE DATABASE IF NOT EXISTS pingu;
USE pingu;

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'READER',
    is_deleted BOOLEAN DEFAULT 0
) ENGINE=INNODB;

-- default admin user
INSERT INTO Users VALUES (1, "John Doe", "administrador@pingu.com.co", "contraseniasegura123456", "ADMINISTRATOR", 0);

CREATE TABLE IF NOT EXISTS Books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    copies INT NOT NULL,
    copies_borrowed INT NOT NULL,
    field VARCHAR(100) NOT NULL,
    pages INT NOT NULL,
    is_deleted BOOLEAN DEFAULT 0
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Novels (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    copies INT NOT NULL,
    copies_borrowed INT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    recommended_age INT NOT NULL,
    is_deleted BOOLEAN DEFAULT 0
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Borrowings (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    requested_date DATE NOT NULL,
    returned_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED',
    is_deleted BOOLEAN DEFAULT 0,
	FOREIGN KEY (user_id)
        REFERENCES Users (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS borrowings_books (
    borrowing_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY (borrowing_id , book_id),
    FOREIGN KEY (borrowing_id)
        REFERENCES Borrowings (id),
    FOREIGN KEY (book_id)
        REFERENCES Books (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS borrowings_novels (
    borrowing_id INT NOT NULL,
    novel_id INT NOT NULL,
    PRIMARY KEY (borrowing_id , novel_id),
    FOREIGN KEY (borrowing_id)
        REFERENCES Borrowings (id),
    FOREIGN KEY (novel_id)
        REFERENCES Novels (id)
) ENGINE=INNODB;

-- =========================== EXTENSION ===========================

CREATE TABLE IF NOT EXISTS videorecordings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    duration TIME NOT NULL,
    copies INT NOT NULL,
    copies_borrowed INT NOT NULL,
    format VARCHAR(45) NOT NULL,
    is_deleted BOOLEAN DEFAULT 0
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS songs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    recordLabel VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    duration TIME NOT NULL,
    copies INT NOT NULL,
    copies_borrowed INT NOT NULL,
    is_deleted  BOOLEAN DEFAULT 0
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS essays (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    supervisor VARCHAR(50) NOT NULL,
    copies INT NOT NULL,
    copies_borrowed INT NOT NULL,
    topic VARCHAR(50) NOT NULL,
    is_deleted  BOOLEAN DEFAULT 0
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS borrowings_videos (
    borrowing_id INT NOT NULL,
    video_recording_id INT NOT NULL,
    PRIMARY KEY (borrowing_id, video_recording_id),
    FOREIGN KEY (borrowing_id)
        REFERENCES Borrowings (id),
    FOREIGN KEY (video_recording_id)
        REFERENCES videorecordings (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS borrowings_songs (
    borrowing_id INT NOT NULL,
    song_id INT NOT NULL,
    PRIMARY KEY (borrowing_id, song_id),
    FOREIGN KEY (borrowing_id)
        REFERENCES Borrowings (id),
    FOREIGN KEY (song_id)
        REFERENCES songs (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS borrowings_essays (
    borrowing_id INT NOT NULL,
    essay_id INT NOT NULL,
    PRIMARY KEY (borrowing_id, essay_id),
    FOREIGN KEY (borrowing_id)
        REFERENCES Borrowings (id),
    FOREIGN KEY (essay_id)
        REFERENCES essays (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user_info (
    user_id INT NOT NULL,
    biography VARCHAR(250) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id)
        REFERENCES Users (id)
) ENGINE = InnoDB;

CREATE TABLE control_de_cambios_libreria(
    usuario VARCHAR(100),
    accion VARCHAR(100),
    fecha DATETIME
);


DELIMITER //

CREATE TRIGGER controlInsercionUsuario 
AFTER INSERT ON Users 
FOR EACH ROW
BEGIN
    INSERT INTO control_de_cambios_libreria (usuario, accion, fecha)
    VALUES (USER(), 'Insert User', NOW());
END;
//

DELIMITER ;


DELIMITER ;