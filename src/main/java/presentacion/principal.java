package presentacion;

import logica.clases.Bibliotecario;
import logica.manejadores.UsuarioHandler;

public class principal {
    public static void main(String[] args) {
        // Crear un bibliotecario por defecto
        String nombre = "Admin";
        String correo = "admin@biblioteca.com";
        int idEmp = 1;

        Bibliotecario admin = new Bibliotecario(nombre, correo, idEmp);

        // Guardar el usuario usando UsuarioHandler
        UsuarioHandler handler = UsuarioHandler.getInstancia();
        handler.agregarUsuarioH(admin);

        System.out.println("Usuario por defecto creado: " + nombre + " (" + correo + ")");
    }
}