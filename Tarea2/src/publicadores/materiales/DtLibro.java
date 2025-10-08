
package materiales;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtLibro complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtLibro">
 *   <complexContent>
 *     <extension base="{http://publicadores/}dtMaterial">
 *       <sequence>
 *         <element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantPag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtLibro", propOrder = {
    "titulo",
    "cantPag"
})
public class DtLibro
    extends DtMaterial
{

    protected String titulo;
    protected int cantPag;

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Gets the value of the cantPag property.
     * 
     */
    public int getCantPag() {
        return cantPag;
    }

    /**
     * Sets the value of the cantPag property.
     * 
     */
    public void setCantPag(int value) {
        this.cantPag = value;
    }

}
