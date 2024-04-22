INSERT INTO `empleado` VALUES 
(0001,'John Doe','contraseñasegura123456','administrador@pingu.com.co','admin'),
(7777,'julio','julio123','julio@gmail.com','super'),
(1111,'jorge','jorge123','jorge@gmail.com','admin'),
(2222,'pacho','pacho123','pacho@gmail.com','asistente'),
(3333,'juan','juan123','juan@gmail.com','asistente');

INSERT INTO `publicacion` (`Titulo`, `tipo`, `autor`, `num_paginas`, `cant_ejemplares`, `cant_prestados`) VALUES 
('la vida es linda','Novela','julio elias',500,300,45),
('cien anos de soledad','libro','gabo',323,100,22),
('nos vemos en agosto','libro','gabo',232,200,10);

INSERT INTO `usuario` VALUES 
('julio@gmail.com','julio','julio123'),
('ali@gmail.com','ali','ali123'),
('jose@gmail.com', 'jose', 'jose123'),
('johan@gmail.com','johan','johan123');

INSERT INTO `prestamo` VALUES 
('1988853759','2024-04-16','2024-05-01','FINALIZADA','ali@gmail.com','nos vemos en agosto'),
('1988853750','2024-04-16','2024-05-01','REALIZADA','johan@gmail.com','la vida es linda'),
('5485538793','2024-04-16','2024-05-01','SOLICITADO','ali@gmail.com','cien anos de soledad');

INSERT INTO `grabacion` (`Titulo`, `tipo`, `autor`, `duracion`, `cant_ejemplares`, `cant_prestados`) VALUES 
('Sin medir distancias','Cancion','Diomedes Diaz','5 min',3000,45),
('Como serializar java','Videograbacion','Profe Juan','15 min',10,2),
('mañana sera bonito','Cancion','Karol G','3 min',2000,10);

INSERT INTO `prestamo_g` VALUES 
('1988853779','2024-04-16','2024-05-01','FINALIZADA','julio@gmail.com','Sin medir distancias'),
('1988853760','2024-04-16','2024-05-01','REALIZADA','johan@gmail.com','Como serializar java'),
('5485538798','2024-04-16','2024-05-01','SOLICITADO','ali@gmail.com','mañana sera bonito');