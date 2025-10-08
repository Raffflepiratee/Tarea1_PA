
package usuarios;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the usuarios package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: usuarios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtLector }
     * 
     * @return
     *     the new instance of {@link DtLector }
     */
    public DtLector createDtLector() {
        return new DtLector();
    }

    /**
     * Create an instance of {@link DtBibliotecario }
     * 
     * @return
     *     the new instance of {@link DtBibliotecario }
     */
    public DtBibliotecario createDtBibliotecario() {
        return new DtBibliotecario();
    }

    /**
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

}
