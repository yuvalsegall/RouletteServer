
package ws.roulette;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for betType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="betType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRAIGHT"/>
 *     &lt;enumeration value="SPLIT"/>
 *     &lt;enumeration value="STREET"/>
 *     &lt;enumeration value="SIX_LINE"/>
 *     &lt;enumeration value="TRIO"/>
 *     &lt;enumeration value="TOP_LINE"/>
 *     &lt;enumeration value="MANQUE"/>
 *     &lt;enumeration value="PASSE"/>
 *     &lt;enumeration value="ROUGE"/>
 *     &lt;enumeration value="NOIR"/>
 *     &lt;enumeration value="PAIR"/>
 *     &lt;enumeration value="IMPAIR"/>
 *     &lt;enumeration value="PREMIERE_DOUZAINE"/>
 *     &lt;enumeration value="MOYENNE_DOUZAINE"/>
 *     &lt;enumeration value="DERNIERE_DOUZAINE"/>
 *     &lt;enumeration value="CORNER"/>
 *     &lt;enumeration value="BASKET"/>
 *     &lt;enumeration value="COLUMN1"/>
 *     &lt;enumeration value="COLUMN2"/>
 *     &lt;enumeration value="COLUMN3"/>
 *     &lt;enumeration value="SNAKE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "betType")
@XmlEnum
public enum BetType {

    STRAIGHT("STRAIGHT"),
    SPLIT("SPLIT"),
    STREET("STREET"),
    SIX_LINE("SIX_LINE"),
    TRIO("TRIO"),
    TOP_LINE("TOP_LINE"),
    MANQUE("MANQUE"),
    PASSE("PASSE"),
    ROUGE("ROUGE"),
    NOIR("NOIR"),
    PAIR("PAIR"),
    IMPAIR("IMPAIR"),
    PREMIERE_DOUZAINE("PREMIERE_DOUZAINE"),
    MOYENNE_DOUZAINE("MOYENNE_DOUZAINE"),
    DERNIERE_DOUZAINE("DERNIERE_DOUZAINE"),
    CORNER("CORNER"),
    BASKET("BASKET"),
    @XmlEnumValue("COLUMN1")
    COLUMN_1("COLUMN1"),
    @XmlEnumValue("COLUMN2")
    COLUMN_2("COLUMN2"),
    @XmlEnumValue("COLUMN3")
    COLUMN_3("COLUMN3"),
    SNAKE("SNAKE");
    private final String value;

    BetType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BetType fromValue(String v) {
        for (BetType c: BetType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
