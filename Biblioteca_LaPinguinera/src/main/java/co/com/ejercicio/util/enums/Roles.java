package co.com.ejercicio.util.enums;

public enum Roles {
    TIPO_UNO("ADMINISTRADOR"),TIPO_DOS("ASISTENTE"),TIPO_TRES("SUPER_USUARIO");

    private String value;

    private Roles (String roles){
        this.value = roles;
    }

    public String getvalue() {
        return value;
    }
}
