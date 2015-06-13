package engine;

import engine.bets.Bet;

/**
 *
 * @author yuvalsegall
 */
public class Event {

    private String playerName;
    private int eventID;
    private EventType eventType;
    private Game game;
    private Bet.BetType playerBet;
    private Player.PlayerAction playerAction;
    private int timeoutCount;
    private int winningNumber;
    private int[] numbers;
    private int amount;

    public Event(String playerName, EventType eventType, Game game, Bet.BetType playerBet, int timeoutCount, int[] numbers, int amount) {
        this.playerName = playerName;
        this.eventType = eventType;
        this.game = game;
        this.playerBet = playerBet;
        this.eventID = game.getEventCounter();
        this.playerAction = generatePlayerActionFromEventType(eventType);
        this.timeoutCount = timeoutCount;
        this.numbers = numbers;
        this.amount = amount;
    }

    public Event(String playerName, EventType eventType, Game game) {
        this(playerName, eventType, game, null, 0, null, 0);
    }

    public Event(EventType eventType, Game game) {
        this(null, eventType, game, null, 0, null, 0);
    }

    public static enum EventType {

        GAME_START, GAME_OVER, GAME_WINNER, NUMBER_RESULT, PLAYER_RESIGNED, PLAYER_BET, PLAYER_FINISHED_BETTING, RESULT_SCORE;
    }

    private Player.PlayerAction generatePlayerActionFromEventType(EventType eventType) {
        switch (eventType) {
            case PLAYER_RESIGNED:
                return Player.PlayerAction.RESIGNED;
            case PLAYER_BET:
                return Player.PlayerAction.BET;
            case PLAYER_FINISHED_BETTING:
                return Player.PlayerAction.FINISHED_BETTING;
            default:
                return null;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getEventID() {
        return eventID;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getTimeoutCount() {
        return timeoutCount;
    }

    public Player.PlayerAction getPlayerAction() {
        return playerAction;
    }

    public Bet.BetType getBetType() {
        return  playerBet;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getAmount() {
        return amount;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(int winningNumber) {
        this.winningNumber = winningNumber;
    }
}
