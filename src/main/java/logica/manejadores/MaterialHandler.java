package logica.manejadores;

public class MaterialHandler {

    private static MaterialHandler instancia = null;

    private MaterialHandler(){}

    public static MaterialHandler getInstancia() {
        if (instancia == null)
            instancia = new MaterialHandler();
        return instancia;
    }
    
}
