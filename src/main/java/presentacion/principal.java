package presentacion;

import logica.clases.Lector;
import logica.clases.Bibliotecario;
import logica.clases.Libro;
import logica.clases.ArticuloEspecial;
import logica.clases.Prestamo;
import datatypes.EstadosP;
import datatypes.EstadosU;
import datatypes.Zonas;

import logica.manejadores.UsuarioHandler;
import logica.manejadores.MaterialHandler;
import logica.manejadores.PrestamoHandler;

import java.util.Date;

public class principal {
    public static void main(String[] args) {
        /* Crear un bibliotecario por defecto */
        // String nombre = "lecotr01";
        // String correo = "lector01@lector.com";

        // Lector lec = new Lector(nombre, correo, new Date(),
        // EstadosU.ACTIVO,Zonas.ARCHIVO_GENERAL,"Calle Falsa 123");

        // Bibliotecario bib = new Bibliotecario("biblio01", "biblio01@biblio.com");

        // Guardar el usuario usando UsuarioHandler
        // UsuarioHandler handler = UsuarioHandler.getInstancia();
        // handler.agregarUsuarioH(lec);
        // handler.agregarUsuarioH(bib);

        // System.out.println("Usuarios agregados: " + lec.getNombre() + ", " +
        // bib.getNombre());

        // Crear y guardar materiales, en este caso un libro
        /*
         * Libro libro = new Libro(new Date(), "martin gay", 5);
         * MaterialHandler mh = MaterialHandler.getInstancia();
         * mh.agregarMaterialH(libro);
         * 
         * System.out.println("Libro agregado: " + libro.getIdMaterial());
         */

        // Crear y guardar un Prestamo

        // Prestamo p = new Prestamo(new Date(), EstadosP.PENDIENTE, null, lec, bib,
        // articuloEspecial);

        // p.setLector(lec);
        // p.setBibliotecario(bib);
        // p.setMaterial(articuloEspecial);

        // PrestamoHandler ph = PrestamoHandler.getInstancia();
        // ph.agregarPrestamoH(p);
        // System.out.println("Prestamo agregado con ID: " + p.getIdPrestamo());
    }
}