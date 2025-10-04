
package prestamos;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for estadosP</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="estadosP">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="PENDIENTE"/>
 *     <enumeration value="EN_CURSO"/>
 *     <enumeration value="DEVUELTO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadosP")
@XmlEnum
public enum EstadosP {

    PENDIENTE,
    EN_CURSO,
    DEVUELTO;

    public String value() {
        return name();
    }

    public static EstadosP fromValue(String v) {
        return valueOf(v);
    }

}
