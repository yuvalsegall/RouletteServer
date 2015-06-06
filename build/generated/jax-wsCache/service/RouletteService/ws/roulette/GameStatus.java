
package ws.roulette;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gameStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="gameStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WAITING"/>
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="FINISHED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gameStatus")
@XmlEnum
public enum GameStatus {

    WAITING,
    ACTIVE,
    FINISHED;

    public String value() {
        return name();
    }

    public static GameStatus fromValue(String v) {
        return valueOf(v);
    }

}
