package engine;

import engine.bets.Bet;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author yuvalsegall
 */
public class Player implements Comparable<Player> {

    private final PlayerDetails playerDetails;
    private static int idCounter = 1000;

    public Player(PlayerDetails playerDetials) {
        this.playerDetails = playerDetials;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }
    
    public enum PlayerAction {
        BET, RESIGNED, FINISHED_BETTING;
    }

    @Override
    public int compareTo(Player o) {
        return this.getPlayerDetails().getMoney().compareTo(o.getPlayerDetails().getMoney());
    }

    public static class PlayerDetails {

        private final int playerID;
        private String name;
        private Boolean isHuman;
        private BigInteger money;
        private List<Bet> bets;
        private Boolean isActive;
        private IntegerProperty amount;
        private PlayerAction playerAction = null;

        public PlayerDetails(String name, Boolean isHuman, BigInteger money) {
            this.playerID = idCounter++;
            this.name = name;
            this.isHuman = isHuman;
            this.amount = new SimpleIntegerProperty();
            this.isActive = true;
            setMoney(money);
            bets = new ArrayList<>();
        }

        public PlayerDetails() {
            this.playerID = idCounter++;
            this.name = new String();
            this.isHuman = true;
            this.amount = new SimpleIntegerProperty();
            this.isActive = true;
            setMoney(money);
            bets = new ArrayList<>();
        }

        public PlayerAction getPlayerAction() {
            return playerAction;
        }

        public void setPlayerAction(PlayerAction playerAction) {
            this.playerAction = playerAction;
        }
        
        public void setName(String name) {
            this.name = name;
        }

        public void setIsHuman(Boolean isHuman) {
            this.isHuman = isHuman;
        }

        public int getPlayerID() {
            return playerID;
        }

        public String getName() {
            return name;
        }

        public Boolean getIsHuman() {
            return isHuman;
        }

        public BigInteger getMoney() {
            return money;
        }

        public void setMoney(BigInteger money) {
            this.money = money;
            this.amount.setValue(money);
        }

        public List<Bet> getBets() {
            return bets;
        }

        public void setBets(List<Bet> bets) {
            this.bets = bets;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public IntegerProperty getAmount() {
            return amount;
        }

    }
}
