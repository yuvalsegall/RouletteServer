
package ws.roulette;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rouletteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rouletteType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FRENCH"/>
 *     &lt;enumeration value="AMERICAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rouletteType")
@XmlEnum
public enum RouletteType {

    FRENCH,
    AMERICAN;

    public String value() {
        return name();
    }

    public static RouletteType fromValue(String v) {
        return valueOf(v);
    }

}
