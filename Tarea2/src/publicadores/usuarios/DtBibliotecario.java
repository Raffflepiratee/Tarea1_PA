
package usuarios;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtBibliotecario complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtBibliotecario">
 *   <complexContent>
 *     <extension base="{http://publicadores/}dtUsuario">
 *       <sequence>
 *         <element name="IdEmp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtBibliotecario", propOrder = {
    "idEmp"
})
public class DtBibliotecario
    extends DtUsuario
{

    @XmlElement(name = "IdEmp")
    protected int idEmp;

    /**
     * Gets the value of the idEmp property.
     * 
     */
    public int getIdEmp() {
        return idEmp;
    }

    /**
     * Sets the value of the idEmp property.
     * 
     */
    public void setIdEmp(int value) {
        this.idEmp = value;
    }

}
