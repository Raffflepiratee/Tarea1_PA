
package usuarios;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for zonas</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="zonas">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="BIBLIOTECA_CENTRAL"/>
 *     <enumeration value="SUCURSAL_ESTE"/>
 *     <enumeration value="SUCURSAL_OESTE"/>
 *     <enumeration value="BIBLIOTECA_INFANTIL"/>
 *     <enumeration value="ARCHIVO_GENERAL"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "zonas")
@XmlEnum
public enum Zonas {

    BIBLIOTECA_CENTRAL,
    SUCURSAL_ESTE,
    SUCURSAL_OESTE,
    BIBLIOTECA_INFANTIL,
    ARCHIVO_GENERAL;

    public String value() {
        return name();
    }

    public static Zonas fromValue(String v) {
        return valueOf(v);
    }

}
