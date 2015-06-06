
package ws.roulette;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GameStart"/>
 *     &lt;enumeration value="GameOver"/>
 *     &lt;enumeration value="WinningNumber"/>
 *     &lt;enumeration value="ResultsScores"/>
 *     &lt;enumeration value="PlayerResigned"/>
 *     &lt;enumeration value="PlayerBet"/>
 *     &lt;enumeration value="PlayerFinishedBetting"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventType")
@XmlEnum
public enum EventType {

    @XmlEnumValue("GameStart")
    GAME_START("GameStart"),
    @XmlEnumValue("GameOver")
    GAME_OVER("GameOver"),
    @XmlEnumValue("WinningNumber")
    WINNING_NUMBER("WinningNumber"),
    @XmlEnumValue("ResultsScores")
    RESULTS_SCORES("ResultsScores"),
    @XmlEnumValue("PlayerResigned")
    PLAYER_RESIGNED("PlayerResigned"),
    @XmlEnumValue("PlayerBet")
    PLAYER_BET("PlayerBet"),
    @XmlEnumValue("PlayerFinishedBetting")
    PLAYER_FINISHED_BETTING("PlayerFinishedBetting");
    private final String value;

    EventType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventType fromValue(String v) {
        for (EventType c: EventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
