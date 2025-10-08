
package materiales;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtArticuloEspecial complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtArticuloEspecial">
 *   <complexContent>
 *     <extension base="{http://publicadores/}dtMaterial">
 *       <sequence>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="peso" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="dimFisica" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtArticuloEspecial", propOrder = {
    "descripcion",
    "peso",
    "dimFisica"
})
public class DtArticuloEspecial
    extends DtMaterial
{

    protected String descripcion;
    protected float peso;
    protected float dimFisica;

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the peso property.
     * 
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Sets the value of the peso property.
     * 
     */
    public void setPeso(float value) {
        this.peso = value;
    }

    /**
     * Gets the value of the dimFisica property.
     * 
     */
    public float getDimFisica() {
        return dimFisica;
    }

    /**
     * Sets the value of the dimFisica property.
     * 
     */
    public void setDimFisica(float value) {
        this.dimFisica = value;
    }

}
