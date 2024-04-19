package co.com.ejercicio.conexionBd.constantesCRUD;

public class QueryConstante {

    //Query a Tabla Publicacion en la BD
    public static final String SELECT_ALL_FROM_PUBLICACIONES = "SELECT * FROM publicacion";

    public static final String SELECT_ALL_AUTOR_FROM_PUBLICACIONES = "SELECT * FROM publicacion WHERE nombre_autor = ? " ;
    public static final String INSERT_PUBLICACIONES = "INSERT INTO publicacion (Titulo, Tipo_publicacion, numero_pagina, cantidad_ejemplares, cantidad_prestado, cantidad_disponible, nombre_autor) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PUBLICACIONES = "UPDATE publicacion SET Tipo_publicacion = ?, Numero_pagina = ?, cantidad_ejemplares = ?, cantidad_prestado = ?, cantidad_disponible = ?, nombre_autor = ? WHERE titulo = ?";
    public static final String DELETE_FROM_PUBLICACIONES = "DELETE FROM publicacion WHERE titulo = ?";

    //Query a Tabla video_grabacion en la BD
    public static final String SELECT_ALL_FROM_VIDEOGRABACION= "SELECT * FROM video_grabacion";

    public static final String SELECT_ALL_DIRECTOR_FROM_VIDEOGRABACION = "SELECT * FROM video_grabacion WHERE director = ? " ;
    public static final String INSERT_VIDEOGRABACION = "INSERT INTO video_grabacion (titulo, director, duracion, cantidad_ejemplares, cantidad_prestado, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_VIDEOGRABACION  = "UPDATE video_grabacion SET titulo = ?, director = ?, duracion = ?, cantidad_ejemplares = ?, cantidad_prestado = ?, cantidad_disponible = ? WHERE titulo = ?";
    public static final String DELETE_FROM_VIDEOGRABACION = "DELETE FROM video_grabacion WHERE titulo = ?";

    //Query a Tabla cancion en la BD
    public static final String SELECT_ALL_FROM_CANCION= "SELECT * FROM cancion";

    public static final String SELECT_ALL_ARTISTA_FROM_CANCION = "SELECT * FROM cancion WHERE artista = ? " ;
    public static final String INSERT_CANCION= "INSERT INTO cancion (titulo, artista, album, duracion, cantidad_ejemplares, cantidad_prestado, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CANCION  = "UPDATE cancion SET titulo = ?, artista = ?, album = ?, duracion = ?, cantidad_ejemplares = ?, cantidad_prestado = ?, cantidad_disponible = ? WHERE titulo = ?";
    public static final String DELETE_FROM_CANCION = "DELETE FROM cancion WHERE titulo = ?";

    //Query a Tabla ensayo_tesis en la BD
    public static final String SELECT_ALL_FROM_ENSAYO_TESIS = "SELECT * FROM ensayo_tesis";

    public static final String SELECT_ALL_AUTOR_FROM_ENSAYO_TESIS  = "SELECT * FROM ensayo_tesis WHERE autor = ? " ;
    public static final String INSERT_ENSAYO_TESIS = "INSERT INTO ensayo_tesis (titulo, autor, numero_paginas, cantidad_ejemplares, cantidad_prestado, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ENSAYO_TESIS = "UPDATE ensayo_tesis SET titulo = ?, autor = ?, numero_paginas = ?, cantidad_ejemplares = ?, cantidad_prestado = ?, cantidad_disponible = ? WHERE titulo = ?";
    public static final String DELETE_FROM_ENSAYO_TESIS = "DELETE FROM ensayo_tesis WHERE titulo = ?";


    //Query a Tabla Prestamo en la BD

    public static final String INSERT_PRESTAMO = "INSERT INTO prestamo (idPrestamo, fecha_prestamo, fecha_devolucion, estado, usuario_correo, Publicacion_titulo) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String DELETE_FROM_PRESTAMO = "DELETE FROM prestamo WHERE idPrestamo = ?";

    public static final String SELECT_ALL_FROM_PRESTAMO = "SELECT * FROM prestamo";
    public static final String UPDATE_PRESTAMO = "UPDATE prestamo SET fecha_prestamo = ?, fecha_devolucion = ?, estado = ?, usuario_correo = ?, publicacion_titulo = ? WHERE idPrestamo = ?";

    //Query a Tabla Usuario en la BD

    public static final String SELECT_ALL_FROM_USUARIO = "SELECT * FROM usuario";
    public static final String INSERT_USUARIO = "INSERT INTO usuario (correo, nombre, contrasenia) VALUES (?, ?, ?)";

    public static final String UPDATE_USUARIO = "UPDATE usuario SET nombre = ?, correo = ? WHERE contrasenia = ?";

    public static final String UPDATE_PASSWORD_USUARIO = "UPDATE usuario set contrasenia = ?  WHERE correo = ? AND contrasenia = ?";

    public static final String UPDATE_PASSWORD_EMPLEADO = "UPDATE empleado set contrasenia = ? WHERE idEmpleado = ?";

    public static final String UPDATE_PASSWORD_ADMINISTRADOR = "UPDATE administrador set contrasenia = ? WHERE id = ?";

    public static final String DELETE_FROM_USUARIO = "DELETE FROM usuario WHERE correo = ?";

    public static final String SELECT_LOGUIN_USER = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";

    public static final String SELECT_REGISTRO_USUARIO = "SELECT * FROM usuario WHERE correo = ?";

    //Query a Tabla Empleado en la BD

    public static final String INSERT_EMPLEADO = "INSERT INTO empleado (idEmpleado, nombre, correo, contrasenia, rol) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_EMPLEADO = "UPDATE empleado SET nombre = ?, correo = ? WHERE idEmpleado = ?";

    public static final String INSERT_ADMINISTRADOR = "INSERT INTO administrador (id, nombre, correo, contrasenia, departamentoAdministrado) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_ADMINISTRADOR = "UPDATE administrador SET nombre = ?, correo = ?, departamentoAdministrado = ? WHERE id = ?";


    public static final String DELETE_FROM_EMPLEADO = "DELETE FROM empleado WHERE idEmpleado = ?";

    public static final String SELECT_LOGUIN_ASISTENTE = "SELECT * FROM empleado WHERE correo = ? AND contrasenia = ?";

    public static final String SELECT_LOGIN_ADMINISTRADOR = "SELECT * from administrador where correo = ? AND contrasenia = ?";
    public static final String SELECT_ALL_FROM_EMPLEADO = "SELECT * FROM empleado";

    public static final String SELECT_ALL_FROM_ADMINISTRADOR = "SELECT * from administrador";

}
