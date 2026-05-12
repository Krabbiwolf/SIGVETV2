package Modelos;

/**
 * Guarda temporalmente el usuario que inició sesión.
 * Sirve para que los módulos no pidan manualmente el ID del usuario.
 */
public class SesionUsuario {

    private static Usuario usuarioActual;

    private SesionUsuario() {
        // Evita crear objetos de esta clase.
    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static int getIdUsuarioActual() {
        return usuarioActual != null ? usuarioActual.getIdUsuario() : 0;
    }

    public static String getNombreUsuarioActual() {
        if (usuarioActual == null) {
            return "Usuario no identificado";
        }

        String nombre = usuarioActual.getNombre() == null ? "" : usuarioActual.getNombre().trim();
        String apellido = usuarioActual.getApellido() == null ? "" : usuarioActual.getApellido().trim();
        String nombreCompleto = (nombre + " " + apellido).trim();

        if (!nombreCompleto.isEmpty()) {
            return nombreCompleto;
        }

        return usuarioActual.getUsuario() != null ? usuarioActual.getUsuario() : "Usuario no identificado";
    }

    public static String getRolUsuarioActual() {
        if (usuarioActual == null || usuarioActual.getRolCargo() == null) {
            return "";
        }
        return usuarioActual.getRolCargo();
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
