USE pinguinera;

DELIMITER //
CREATE TRIGGER after_insert_Usuario
AFTER INSERT ON Usuario
FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    
    -- Generar un nuevo ID para el registro
    SET new_id = (SELECT IFNULL(MAX(id) + 1, 1) FROM registros_creados);
    
    -- Insertar el nuevo registro en registros_creados
    INSERT INTO registros_creados (fecha, registro_id, idUsuario)
    VALUES (NOW(), new_id, NEW.idUsuario);
END;
//
DELIMITER ;
