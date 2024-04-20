USE bibliotecapingu;

CREATE TABLE registros_creados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    creado_por VARCHAR(255) DEFAULT 'SUPERSUARIO',
    correo_nuevo_usuario VARCHAR(255),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    accion VARCHAR(50) DEFAULT 'Usuario creado'
);

DELIMITER //

CREATE TRIGGER registros_creados
AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    DECLARE creado_por_value VARCHAR(255);

    -- Verificar el rol del nuevo usuario
    IF NEW.rol <> 'LECTOR' THEN
        SET creado_por_value = 'SUPERSUARIO';
        
        -- Insertar en registros_creados con el rol "SUPERSUARIO"
        INSERT INTO registros_creados (creado_por, correo_nuevo_usuario, accion)
        VALUES (creado_por_value, NEW.correo, 'Usuario creado');
    END IF;
END;
//

DELIMITER ;












