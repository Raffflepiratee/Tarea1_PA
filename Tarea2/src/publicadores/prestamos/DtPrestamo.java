
package prestamos;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPrestamo complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtPrestamo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idPrestamo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaSoli" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="estadoPres" type="{http://publicadores/}estadosP" minOccurs="0"/>
 *         <element name="fechaDev" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="lector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="bibliotecario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="material" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPrestamo", propOrder = {
    "idPrestamo",
    "fechaSoli",
    "estadoPres",
    "fechaDev",
    "lector",
    "bibliotecario",
    "material"
})
public class DtPrestamo {

    protected int idPrestamo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSoli;
    @XmlSchemaType(name = "string")
    protected EstadosP estadoPres;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDev;
    protected String lector;
    protected String bibliotecario;
    protected int material;

    /**
     * Gets the value of the idPrestamo property.
     * 
     */
    public int getIdPrestamo() {
        return idPrestamo;
    }

    /**
     * Sets the value of the idPrestamo property.
     * 
     */
    public void setIdPrestamo(int value) {
        this.idPrestamo = value;
    }

    /**
     * Gets the value of the fechaSoli property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSoli() {
        return fechaSoli;
    }

    /**
     * Sets the value of the fechaSoli property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSoli(XMLGregorianCalendar value) {
        this.fechaSoli = value;
    }

    /**
     * Gets the value of the estadoPres property.
     * 
     * @return
     *     possible object is
     *     {@link EstadosP }
     *     
     */
    public EstadosP getEstadoPres() {
        return estadoPres;
    }

    /**
     * Sets the value of the estadoPres property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadosP }
     *     
     */
    public void setEstadoPres(EstadosP value) {
        this.estadoPres = value;
    }

    /**
     * Gets the value of the fechaDev property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDev() {
        return fechaDev;
    }

    /**
     * Sets the value of the fechaDev property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDev(XMLGregorianCalendar value) {
        this.fechaDev = value;
    }

    /**
     * Gets the value of the lector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLector() {
        return lector;
    }

    /**
     * Sets the value of the lector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLector(String value) {
        this.lector = value;
    }

    /**
     * Gets the value of the bibliotecario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBibliotecario() {
        return bibliotecario;
    }

    /**
     * Sets the value of the bibliotecario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBibliotecario(String value) {
        this.bibliotecario = value;
    }

    /**
     * Gets the value of the material property.
     * 
     */
    public int getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     */
    public void setMaterial(int value) {
        this.material = value;
    }

}
