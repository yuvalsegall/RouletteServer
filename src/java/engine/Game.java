package engine;

import controller.exceptions.PlayerIdNotFoundException;
import engine.Player.PlayerDetails;
import engine.Table.TableType;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author yuvalsegall
 */
public class Game {
    
    private GameDetails gameDetails = null;
    private Table table = null;
    private RulesChecker rulesChecker = null;
    private GameTimer gameTimer = null;
    
    public Game() {
        this.gameDetails = new GameDetails();
        this.table = new Table(gameDetails.getTableType());
        this.rulesChecker = new RulesChecker();
    }
    
    public Game(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
        this.table = new Table(gameDetails.getTableType());
        this.rulesChecker = new RulesChecker();
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }
    
    public GameDetails getGameDetails() {
        return gameDetails;
    }
    
    public Table getTable() {
        return table;
    }
    
    public RulesChecker getRulesChecker() {
        return rulesChecker;
    }
    
    public void setGameDetails(GameDetails gameDetails) {
        this.gameDetails = gameDetails;
    }
    
    public void setTable(Table table) {
        this.table = table;
    }
    
    public void setRulesChecker(RulesChecker rulesChecker) {
        this.rulesChecker = rulesChecker;
    }
    
    public void copyGame(Game dest) {
        dest.setGameDetails(this.getGameDetails());
        dest.setRulesChecker(this.getRulesChecker());
        dest.setTable(this.getTable());
    }
    
    public void startTimer(TimerTask task,long interval) {
        if(gameTimer == null)
            gameTimer = new GameTimer();
        gameTimer.startTimer(task, interval);
    }
    
    public void stopTime(){
        if (gameTimer == null)
            return;
        gameTimer.stopTimer();
        gameTimer = null;
    }
    
    public static class GameDetails {
        
        private String gameName;
        private int minWages;
        private int maxWages;
        private TableType tableType;
        private int initialSumOfMoney;
        private List<Player> players;
        private int computerPlayers;
        private int humanPlayers;
        private GameStatus gameStatus;
        
        public GameDetails(String gameName, int computerPlayers, int humanPlayers, int minWages, int maxWages, TableType tableType, int initialSumOfMoney, GameStatus gameStatus) {
            this.gameName = gameName;
            this.minWages = minWages;
            this.maxWages = maxWages;
            this.tableType = tableType;
            this.initialSumOfMoney = initialSumOfMoney;
            this.computerPlayers = computerPlayers;
            this.humanPlayers = humanPlayers;
            this.players = initiateComputerPlayers(computerPlayers);
            this.gameStatus = gameStatus;
            
        }
        
        public GameDetails(String gameName, int minWages, int maxWages, TableType tableType, int initialSumOfMoney, GameStatus gameStatus) {
            this.gameName = gameName;
            this.minWages = minWages;
            this.maxWages = maxWages;
            this.tableType = tableType;
            this.initialSumOfMoney = initialSumOfMoney;
            this.gameStatus = gameStatus;
        }
        
        public GameDetails(String gameName, int minWages, int maxWages, TableType tableType, int initialSumOfMoney, List<Player> players, GameStatus gameStatus) {
            this.gameName = gameName;
            this.minWages = minWages;
            this.maxWages = maxWages;
            this.tableType = tableType;
            this.initialSumOfMoney = initialSumOfMoney;
            this.players = players;
            this.computerPlayers = 0;
            this.humanPlayers = 0;
            this.gameStatus = gameStatus;
            players.stream().forEach((player) -> {
                if (player.getPlayerDetails().getIsHuman()) {
                    humanPlayers++;
                } else {
                    computerPlayers++;
                }
            });
        }
        
        public GameDetails() {
            this.players = new ArrayList<>();
        }

        public GameStatus getGameStatus() {
            return gameStatus;
        }

        public void setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
        }
        
        public int getComputerPlayers() {
            return computerPlayers;
        }
        
        public int getHumanPlayers() {
            return humanPlayers;
        }
        
        public int getInitialSumOfMoney() {
            return initialSumOfMoney;
        }
        
        public TableType getTableType() {
            return tableType;
        }
        
        public int getMinWages() {
            return minWages;
        }
        
        public int getMaxWages() {
            return maxWages;
        }
        
        public List<Player> getPlayers() {
            return players;
        }
        
        public String getGameName() {
            return gameName;
        }
              
        public PlayerDetails[] getPlayersDetails() {
            PlayerDetails[] details = new PlayerDetails[computerPlayers + humanPlayers];
            for (int i = 0; i < details.length; i++) {
                details[i] = players.get(i).getPlayerDetails();
            }
            
            return details;
        }
        
        PlayerDetails getPlayerDetails(int playerID) throws PlayerIdNotFoundException {
            return findPlayerById(playerID).getPlayerDetails();
        }
        
        public void setGameName(String gameName) {
            this.gameName = gameName;
        }
        
        public void setMinWages(int minWages) {
            this.minWages = minWages;
        }
        
        public void setMaxWages(int maxWages) {
            this.maxWages = maxWages;
        }
        
        public void setTableType(TableType tableType) {
            this.tableType = tableType;
        }
        
        public void setInitialSumOfMoney(int initialSumOfMoney) {
            this.initialSumOfMoney = initialSumOfMoney;
        }
        
        public void setComputerPlayers(int computerPlayers) {
            this.computerPlayers = computerPlayers;
        }
        
        public void setHumanPlayers(int humanPlayers) {
            this.humanPlayers = humanPlayers;
        }
        
        public Player findPlayerById(int playerID) throws PlayerIdNotFoundException {
            for (Player player : players) {
                if (player.getPlayerDetails().getPlayerID() == playerID) {
                    return player;
                }
            }
            throw new PlayerIdNotFoundException();
        }
        
        private ArrayList<Player> initiateComputerPlayers(int computerPlayers) {
            ArrayList<Player> allPlayers = new ArrayList<>();
            for (int i = 0; i < computerPlayers; i++) {
                Player.PlayerDetails details = new Player.PlayerDetails("Computer" + i, false, BigInteger.valueOf(initialSumOfMoney));
                allPlayers.add(new Player(details));
            }
            return allPlayers;
        }
        
        public void addPlayer(Player newPlayer) {
            players.add(newPlayer);
        }
        
    }
    
    public enum GameStatus{
        WAITING, ACTIVE, FINISHED;
    }
    
    public static class ConstValuesForBets {
        
        public static final int MIN_MANQUE = 1;
        public static final int MAX_MANQUE = 18;
        public static final int MIN_PASSE = 19;
        public static final int MAX_PASSE = 36;
        public static final int MIN_PREMIERE_DOUZAINE = 1;
        public static final int MAX_PREMIERE_DOUZAINE = 12;
        public static final int MIN_DERNIERE_DOUZAINE = 25;
        public static final int MAX_DERNIERE_DOUZAINE = 36;
        public static final int MIN_MOYENNE_DOUZAINE = 13;
        public static final int MAX_MOYENNE_DOUZAINE = 24;
        public static final int[] SNAKE_VALUES = {1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, 34};
        public static final int FIRST_COLUMN_HEAD = 1;
        public static final int SECOND_COLUMN_HEAD = 2;
        public static final int THIRD_COLUMN_HEAD = 3;
        public static final int[] TRIO_V1 = {0, 1, 2};
        public static final int[] TRIO_V2 = {0, 2, 3};
        public static final int ODD_DEVIDER = 1;
        public static final int EVEN_DEVIDER = 0;
        public static final int NUM_OF_COLS = 3;
        public static final int[] BASKET_FR = {0, 1, 2, 3};
        public static final int[] BASKET_AM_V1 = {0, 1, 2};
        public static final int[] BASKET_AM_V2 = {0, 2, 37};
        public static final int[] BASKET_AM_V3 = {2, 3, 37};
        public static final int[] TOP_LINE = {0, 1, 2, 3, 37};
    }
}
