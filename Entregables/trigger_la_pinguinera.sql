   --  Este trigger registra de forma automatica, la creacion de nuevos administradores en la tabla registros_creados
    CREATE DEFINER=`root`@`localhost` TRIGGER `nuevo_usuario` AFTER INSERT ON `administrador` FOR EACH ROW BEGIN
    INSERT INTO registros_creados (nombre, correo, fecha_registro) 
    VALUES (NEW.nombre, NEW.correo, NOW());
    END

