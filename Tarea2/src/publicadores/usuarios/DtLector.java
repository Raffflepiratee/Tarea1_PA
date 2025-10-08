
package usuarios;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtLector complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtLector">
 *   <complexContent>
 *     <extension base="{http://publicadores/}dtUsuario">
 *       <sequence>
 *         <element name="fechaIngreso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="estadoUsuario" type="{http://publicadores/}estadosU" minOccurs="0"/>
 *         <element name="zona" type="{http://publicadores/}zonas" minOccurs="0"/>
 *         <element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtLector", propOrder = {
    "fechaIngreso",
    "estadoUsuario",
    "zona",
    "direccion"
})
public class DtLector
    extends DtUsuario
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaIngreso;
    @XmlSchemaType(name = "string")
    protected EstadosU estadoUsuario;
    @XmlSchemaType(name = "string")
    protected Zonas zona;
    protected String direccion;

    /**
     * Gets the value of the fechaIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Sets the value of the fechaIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaIngreso(XMLGregorianCalendar value) {
        this.fechaIngreso = value;
    }

    /**
     * Gets the value of the estadoUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link EstadosU }
     *     
     */
    public EstadosU getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * Sets the value of the estadoUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadosU }
     *     
     */
    public void setEstadoUsuario(EstadosU value) {
        this.estadoUsuario = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link Zonas }
     *     
     */
    public Zonas getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zonas }
     *     
     */
    public void setZona(Zonas value) {
        this.zona = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

}
