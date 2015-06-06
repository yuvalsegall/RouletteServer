
package ws.roulette;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for makeBet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="makeBet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="betMoney" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="betType" type="{http://roulette.ws/}betType" minOccurs="0"/>
 *         &lt;element name="numbers" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "makeBet", propOrder = {
    "betMoney",
    "betType",
    "numbers",
    "playerId"
})
public class MakeBet {

    protected int betMoney;
    protected BetType betType;
    @XmlElement(nillable = true)
    protected List<Integer> numbers;
    protected int playerId;

    /**
     * Gets the value of the betMoney property.
     * 
     */
    public int getBetMoney() {
        return betMoney;
    }

    /**
     * Sets the value of the betMoney property.
     * 
     */
    public void setBetMoney(int value) {
        this.betMoney = value;
    }

    /**
     * Gets the value of the betType property.
     * 
     * @return
     *     possible object is
     *     {@link BetType }
     *     
     */
    public BetType getBetType() {
        return betType;
    }

    /**
     * Sets the value of the betType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BetType }
     *     
     */
    public void setBetType(BetType value) {
        this.betType = value;
    }

    /**
     * Gets the value of the numbers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numbers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumbers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getNumbers() {
        if (numbers == null) {
            numbers = new ArrayList<Integer>();
        }
        return this.numbers;
    }

    /**
     * Gets the value of the playerId property.
     * 
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the value of the playerId property.
     * 
     */
    public void setPlayerId(int value) {
        this.playerId = value;
    }

}
