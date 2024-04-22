-- DROP TABLE IF EXISTS admins_log;
CREATE TABLE IF NOT EXISTS admins_log (
    action VARCHAR(100) NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    user_name VARCHAR(45),
    date DATE NOT NULL
)  ENGINE=INNODB;

-- DROP TRIGGER log_new_admin_user;
DELIMITER $$
CREATE TRIGGER log_new_admin_user AFTER INSERT ON user
FOR EACH ROW
BEGIN
    IF NEW.role = 'ADMINISTRATOR' THEN
        INSERT INTO admins_log (action, user_id, user_name, date) 
        VALUES ('A superuser created a new admin user', NEW.id, NEW.name, NOW());
    END IF;
END$$

DELIMITER ;


-- insert into user values ('12356','pablito', 'pab@lo', 'lo', 'ADMINISTRATOR', '310', '2024-11-11');
select * from admins_log;
select * from user;