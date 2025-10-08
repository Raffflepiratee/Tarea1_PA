
package materiales;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtMaterial complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtMaterial">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idMaterial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtMaterial", propOrder = {
    "idMaterial",
    "fechaRegistro"
})
@XmlSeeAlso({
    DtLibro.class,
    DtArticuloEspecial.class
})
public class DtMaterial {

    protected int idMaterial;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;

    /**
     * Gets the value of the idMaterial property.
     * 
     */
    public int getIdMaterial() {
        return idMaterial;
    }

    /**
     * Sets the value of the idMaterial property.
     * 
     */
    public void setIdMaterial(int value) {
        this.idMaterial = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

}
