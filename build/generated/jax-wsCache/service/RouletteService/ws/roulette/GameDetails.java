
package ws.roulette;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gameDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gameDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="computerizedPlayers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="humanPlayers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="initalSumOfMoney" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intMaxWages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="joinedHumanPlayers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="loadedFromXML" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="minWages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rouletteType" type="{http://roulette.ws/}rouletteType" minOccurs="0"/>
 *         &lt;element name="status" type="{http://roulette.ws/}gameStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gameDetails", propOrder = {
    "computerizedPlayers",
    "humanPlayers",
    "initalSumOfMoney",
    "intMaxWages",
    "joinedHumanPlayers",
    "loadedFromXML",
    "minWages",
    "name",
    "rouletteType",
    "status"
})
public class GameDetails {

    protected int computerizedPlayers;
    protected int humanPlayers;
    protected int initalSumOfMoney;
    protected int intMaxWages;
    protected int joinedHumanPlayers;
    protected boolean loadedFromXML;
    protected int minWages;
    protected String name;
    protected RouletteType rouletteType;
    protected GameStatus status;

    /**
     * Gets the value of the computerizedPlayers property.
     * 
     */
    public int getComputerizedPlayers() {
        return computerizedPlayers;
    }

    /**
     * Sets the value of the computerizedPlayers property.
     * 
     */
    public void setComputerizedPlayers(int value) {
        this.computerizedPlayers = value;
    }

    /**
     * Gets the value of the humanPlayers property.
     * 
     */
    public int getHumanPlayers() {
        return humanPlayers;
    }

    /**
     * Sets the value of the humanPlayers property.
     * 
     */
    public void setHumanPlayers(int value) {
        this.humanPlayers = value;
    }

    /**
     * Gets the value of the initalSumOfMoney property.
     * 
     */
    public int getInitalSumOfMoney() {
        return initalSumOfMoney;
    }

    /**
     * Sets the value of the initalSumOfMoney property.
     * 
     */
    public void setInitalSumOfMoney(int value) {
        this.initalSumOfMoney = value;
    }

    /**
     * Gets the value of the intMaxWages property.
     * 
     */
    public int getIntMaxWages() {
        return intMaxWages;
    }

    /**
     * Sets the value of the intMaxWages property.
     * 
     */
    public void setIntMaxWages(int value) {
        this.intMaxWages = value;
    }

    /**
     * Gets the value of the joinedHumanPlayers property.
     * 
     */
    public int getJoinedHumanPlayers() {
        return joinedHumanPlayers;
    }

    /**
     * Sets the value of the joinedHumanPlayers property.
     * 
     */
    public void setJoinedHumanPlayers(int value) {
        this.joinedHumanPlayers = value;
    }

    /**
     * Gets the value of the loadedFromXML property.
     * 
     */
    public boolean isLoadedFromXML() {
        return loadedFromXML;
    }

    /**
     * Sets the value of the loadedFromXML property.
     * 
     */
    public void setLoadedFromXML(boolean value) {
        this.loadedFromXML = value;
    }

    /**
     * Gets the value of the minWages property.
     * 
     */
    public int getMinWages() {
        return minWages;
    }

    /**
     * Sets the value of the minWages property.
     * 
     */
    public void setMinWages(int value) {
        this.minWages = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the rouletteType property.
     * 
     * @return
     *     possible object is
     *     {@link RouletteType }
     *     
     */
    public RouletteType getRouletteType() {
        return rouletteType;
    }

    /**
     * Sets the value of the rouletteType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RouletteType }
     *     
     */
    public void setRouletteType(RouletteType value) {
        this.rouletteType = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link GameStatus }
     *     
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameStatus }
     *     
     */
    public void setStatus(GameStatus value) {
        this.status = value;
    }

}
