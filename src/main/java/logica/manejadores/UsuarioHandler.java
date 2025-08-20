package logica.manejadores;

public class UsuarioHandler {
    private static UsuarioHandler instancia = null;

    private UsuarioHandler(){}

    public static UsuarioHandler getInstancia() {
        if (instancia == null)
            instancia = new UsuarioHandler();
        return instancia;
    }
}
