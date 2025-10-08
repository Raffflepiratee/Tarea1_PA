
package usuarios;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for estadosU</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="estadosU">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ACTIVO"/>
 *     <enumeration value="SUSPENDIDO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadosU")
@XmlEnum
public enum EstadosU {

    ACTIVO,
    SUSPENDIDO;

    public String value() {
        return name();
    }

    public static EstadosU fromValue(String v) {
        return valueOf(v);
    }

}
