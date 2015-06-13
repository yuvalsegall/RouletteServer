/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controller.exceptions.NumOfHumanPlayersException;
import controller.exceptions.NumOfPlayersException;
import controller.exceptions.OutOfRangeException;
import engine.BadParamsException;
import engine.Game;
import engine.PlayerTimer;
import engine.Player;
import engine.Table;
import engine.XMLGame;
import engine.bets.Bet;
import engine.bets.ColorBet;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import ws.roulette.BetType;
import ws.roulette.Event;
import ws.roulette.EventType;
import ws.roulette.GameDetails;
import ws.roulette.GameStatus;
import ws.roulette.PlayerDetails;
import ws.roulette.PlayerStatus;
import ws.roulette.PlayerType;

/**
 *
 * @author yuvalsegall
 */
@WebService(serviceName = "RouletteWebServiceService", portName = "RouletteWebServicePort", endpointInterface = "ws.roulette.RouletteWebService", targetNamespace = "http://roulette.ws/", wsdlLocation = "WEB-INF/wsdl/RouletteService/RouletteWebServiceService.wsdl")
public class RouletteService {
    //TODO: server on Amazon?
    private final List<engine.Game> games = new ArrayList<>();
    private final Map<Integer, PlayerTimer> timers = new HashMap<Integer, engine.PlayerTimer>();
    private static final long MAX_SECONDS_FOR_ROUND = 600;
    public static final String GAME_NOT_FOUND = "Game not found";
    public static final String GAME_EXCISTES = "Game name already taken";
    public static final String PLAYER_EXCISTES = "Player name in game taken";
    public static final String GAME_NOT_WAITING = "Game status is not waiting, cannot join";
    public static final String PLAYER_DOESNT_EXCISTES = "Cannot find player";
    public static final String OUT_OF_RANGE = "event id out of range";
    public static final String XML_ERROER = "error reading XML";
    public static final String ILLEGAL_BET = "Illegal bet";
    public static final String INACTIVE_PLAYER = "cannot place bet, player inactive";
    private static final int MIN_NUM = 0;
    private static final int MAX_COMP_PLAYERS = 6;
    private static final int MAX_HUMAN_PLAYERS = 6;
    private static final int MAX_PLAYERS = 6;
    private static final int FROM_MIN_WAGES = 0;
    private static final int TO_MIN_WAGES = 1;
    private static final int FROM_MAX_WAGES = 1;
    private static final int TO_MAX_WAGES = 10;
    private static final int MIN_INITIAL_SUM_OF_MONEY = 10;
    private static final int MAX_INITIAL_SUM_OF_MONEY = 100;
    
    public java.util.List<ws.roulette.Event> getEvents(int eventId, int playerId) throws ws.roulette.InvalidParameters_Exception {
        List<ws.roulette.Event> results = new ArrayList<>();
        Player player = findPlayer(playerId);
        engine.Game game = findGameByPlayer(player);
        List<engine.Event> targetEvents;
//        System.out.println("getEvents started from player: " + playerId);
        if(game == null){
            System.out.println("exception in getEvents: playerId="+ playerId);
            throw new ws.roulette.InvalidParameters_Exception(PLAYER_DOESNT_EXCISTES, null);
        }
        if(eventId < 0 || eventId > game.getEvents().size()){
            System.out.println("exception in getEvents: eventId=" + eventId + " while size is:" + game.getEvents().size());
            throw new ws.roulette.InvalidParameters_Exception(OUT_OF_RANGE, null);
        }
        if(eventId == 0){
            targetEvents = game.getEvents().subList(0, game.getEvents().size());
//            System.out.println("sending events:"+0 + "to" + game.getEvents().size());
        }
        else{
            targetEvents = game.getEvents().subList(eventId, game.getEvents().size());
//            System.out.println("sending events:"+ (eventId) + "to" + (game.getEvents().size()));
        }
        for(engine.Event event : targetEvents)
            results.add(convertEventToWSEvent(event));

        return results;
    }

    public void createGame(int computerizedPlayers, int humanPlayers, int initalSumOfMoney, int intMaxWages, int minWages, java.lang.String name, ws.roulette.RouletteType rouletteType) throws ws.roulette.DuplicateGameName_Exception, ws.roulette.InvalidParameters_Exception {
        Table.TableType tableType;
        if(findGame(name) != null)
            throw new ws.roulette.DuplicateGameName_Exception(GAME_EXCISTES, null);
        try {
                paramsCheck(computerizedPlayers, humanPlayers, minWages, intMaxWages, initalSumOfMoney);
            
        } catch(NumOfPlayersException | OutOfRangeException | NumOfHumanPlayersException ex){
            throw new ws.roulette.InvalidParameters_Exception(ex.getMessage(), null);
        }
                
        tableType = rouletteType == ws.roulette.RouletteType.AMERICAN ? Table.TableType.AMERICAN : Table.TableType.FRENCH;
        Game.GameDetails gd = new Game.GameDetails(name, computerizedPlayers, humanPlayers, minWages, minWages, tableType, initalSumOfMoney, Game.GameStatus.WAITING);
        Game game = new Game(gd);
        createComputerPlayers(game);
        games.add(game);
        System.out.println("game created");
    }

    public ws.roulette.GameDetails getGameDetails(java.lang.String gameName) throws ws.roulette.GameDoesNotExists_Exception {
        ws.roulette.GameDetails result;
        Game.GameDetails gameDetails = getGameDetailsByName(gameName);
        
        if(gameDetails == null){
            throw new ws.roulette.GameDoesNotExists_Exception(GAME_NOT_FOUND, null);
        }
        result = convertGameDetailsToWsFormat(gameDetails);
        
        System.out.println("getGameDetails for game: " + gameName);
        return result;
    }

    public java.util.List<java.lang.String> getWaitingGames() {
        List<String> results = new ArrayList<>();
        
        games.stream().filter((game) -> (game.getGameDetails().getGameStatus() == Game.GameStatus.WAITING)).forEach((game) -> {
            results.add(game.getGameDetails().getGameName());
        });
        
        System.out.println("getWaitingGames" + results);
        return results;
    }

    public int joinGame(java.lang.String gameName, java.lang.String playerName) throws ws.roulette.InvalidParameters_Exception, ws.roulette.GameDoesNotExists_Exception {
        Game game = findGame(gameName);
        
        if(game == null)
            throw new ws.roulette.GameDoesNotExists_Exception(GAME_NOT_FOUND, null);
        if(game.getGameDetails().getGameStatus() != Game.GameStatus.WAITING)
            throw new ws.roulette.InvalidParameters_Exception(GAME_NOT_WAITING, null);
        for(Player player : game.getGameDetails().getPlayers()){
            if(player.getPlayerDetails().getName().equals(playerName))
                throw new ws.roulette.InvalidParameters_Exception(PLAYER_EXCISTES, null);
        }
        Player.PlayerDetails pd = new Player.PlayerDetails(playerName, Boolean.TRUE, BigInteger.valueOf(game.getGameDetails().getInitialSumOfMoney()));
        Player newPlayer = new Player(pd);
        if(game.getGameDetails().isIsGameFromXML()){
            Player playerInLoadedGame = GetPlayerFromGame(newPlayer.getPlayerDetails().getName(), game);
            if(playerInLoadedGame.getPlayerDetails().getIsHuman()){
                if(playerInLoadedGame.getPlayerDetails().getPlayerID() != 0){
                    throw new ws.roulette.InvalidParameters_Exception(PLAYER_EXCISTES, null);
                }
                else{
                    game.getGameDetails().getPlayers().remove(playerInLoadedGame);
                    game.getGameDetails().getPlayers().add(newPlayer);
                }
            }
            else{
                throw new ws.roulette.InvalidParameters_Exception(PLAYER_EXCISTES, null);
            }
        }
        else{
            game.getGameDetails().addPlayer(newPlayer);
        }
        timers.put(pd.getPlayerID(),new PlayerTimer());
        if(gameReadyToStart(game)){
            game.getGameDetails().setGameStatus(Game.GameStatus.ACTIVE);
            game.getEvents().add(new engine.Event(engine.Event.EventType.GAME_START, game));
            startPlayersTimers(game);
            playComputerMoves(game);
        }
        
        System.out.println("joinGame player: " + newPlayer.getPlayerDetails().getPlayerID());
        return newPlayer.getPlayerDetails().getPlayerID();
    }

    public ws.roulette.PlayerDetails getPlayerDetails(int playerId) throws ws.roulette.InvalidParameters_Exception, ws.roulette.GameDoesNotExists_Exception {
        engine.Player result;
        
        result = findPlayer(playerId);
        if(result == null)
            throw new ws.roulette.InvalidParameters_Exception(PLAYER_DOESNT_EXCISTES, null);
        engine.Game game = findGameByPlayer(result);
        if(game.getGameDetails().getGameStatus() == engine.Game.GameStatus.FINISHED)
            throw new ws.roulette.GameDoesNotExists_Exception(GAME_NOT_FOUND, null);
        
        System.out.println("getPlayerDetails");
        return convertPlayerDetailsToWsFormat(result.getPlayerDetails());
    }

    public void makeBet(int betMoney, ws.roulette.BetType betType, java.util.List<java.lang.Integer> numbers, int playerId) throws ws.roulette.InvalidParameters_Exception {
        engine.Player player = findPlayer(playerId);
        engine.Game game = findGameByPlayer(player);
        int[] nums;
        nums = convertListToArr(numbers);
        PlayerTimer timer = timers.get(player.getPlayerDetails().getPlayerID());
        
        if(game == null)
            throw new ws.roulette.InvalidParameters_Exception(PLAYER_DOESNT_EXCISTES, null);
        if(!player.getPlayerDetails().getIsActive())
                throw new ws.roulette.InvalidParameters_Exception(INACTIVE_PLAYER, null);
        if(!isAffordableBet(player, betMoney))
            throw new ws.roulette.InvalidParameters_Exception(ILLEGAL_BET, null);
        try {
            player.getPlayerDetails().getBets().add(Bet.makeBet(convertWsBetType(betType), BigInteger.valueOf(betMoney), nums, game.getTable().getTableType()));
            player.getPlayerDetails().setPlayerAction(Player.PlayerAction.BET);
        } catch (BadParamsException ex) {
            throw new ws.roulette.InvalidParameters_Exception(ILLEGAL_BET, null);
        }
        player.getPlayerDetails().setMoney(player.getPlayerDetails().getMoney().add(BigInteger.valueOf((int) betMoney * -1)));
        engine.Event event = new engine.Event(player.getPlayerDetails().getName(), engine.Event.EventType.PLAYER_BET, game, convertWsBetType(betType),0, nums, betMoney);
        game.getEvents().add(event);
        timer.stopTimer();
        timer.startTimer(new RemovePlayer(game, player.getPlayerDetails().getPlayerID()), TimeUnit.SECONDS.toMillis(MAX_SECONDS_FOR_ROUND));
        System.out.println("makeBet");
    }

    public void finishBetting(int playerId) throws ws.roulette.InvalidParameters_Exception {
        engine.Player player = findPlayer(playerId);
        if(player == null)
            throw new ws.roulette.InvalidParameters_Exception(PLAYER_DOESNT_EXCISTES, null);
        engine.Game game = findGameByPlayer(player);
        player.getPlayerDetails().setPlayerAction(Player.PlayerAction.FINISHED_BETTING);
        game.getEvents().add(new engine.Event(player.getPlayerDetails().getName(), engine.Event.EventType.PLAYER_FINISHED_BETTING, game));
        if(isGameReadyForEndRound(game))
            endRound(game);
        System.out.println("finish betting");
    }

    public void resign(int playerId) throws ws.roulette.InvalidParameters_Exception {
        engine.Player player = findPlayer(playerId);
        engine.Game game = findGameByPlayer(player);
        if(player == null)
            throw new ws.roulette.InvalidParameters_Exception(PLAYER_DOESNT_EXCISTES, null);
        player.getPlayerDetails().setIsActive(Boolean.FALSE);
        game.getEvents().add(new engine.Event(player.getPlayerDetails().getName(), engine.Event.EventType.PLAYER_RESIGNED, findGameByPlayer(player)));
        player.getPlayerDetails().setPlayerAction(Player.PlayerAction.RESIGNED);
        System.out.println("resign");
    }

    public java.lang.String createGameFromXML(java.lang.String xmlData) throws ws.roulette.DuplicateGameName_Exception, ws.roulette.InvalidXML_Exception, ws.roulette.InvalidParameters_Exception {
        engine.Game game = null;
        try {
            game = XMLGame.getXMLGame(new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8)));
        } catch (JAXBException ex) {
            throw new ws.roulette.InvalidXML_Exception(XML_ERROER, null);
        } catch (BadParamsException ex) {
            throw new ws.roulette.InvalidParameters_Exception(ex.getMessage(), null);
        }
        
        if(findGame(game.getGameDetails().getGameName()) != null)
            throw new ws.roulette.DuplicateGameName_Exception(GAME_EXCISTES ,null);
        game.getGameDetails().setIsGameFromXML(true);
        game.getGameDetails().setGameStatus(Game.GameStatus.WAITING);
        games.add(game);
        
        System.out.println("createGameFromXML");
        return game.getGameDetails().getGameName();
    }

    public java.util.List<ws.roulette.PlayerDetails> getPlayersDetails(java.lang.String gameName) throws ws.roulette.GameDoesNotExists_Exception {
        Game game = findGame(gameName);
        List<ws.roulette.PlayerDetails> results = new ArrayList<>();
        
        if(game == null)
            throw new ws.roulette.GameDoesNotExists_Exception(GAME_NOT_FOUND, null);
        game.getGameDetails().getPlayers().stream().forEach((player) -> {
            results.add(convertPlayerDetailsToWsFormat(player.getPlayerDetails()));
        });
        
        System.out.println("getPlayersDetails, gameName: " + gameName);
        return results;
    }

    private Game.GameDetails getGameDetailsByName(String gameName) {        
        for(Game game : games){
            if(game.getGameDetails().getGameName().equals(gameName))
                return game.getGameDetails();
        }
        
        return null;
    }

    private GameStatus getWsGameStatus(Game.GameStatus gameStatus) {
        switch(gameStatus){
            case WAITING:
                return GameStatus.WAITING;
            case ACTIVE:
                return GameStatus.ACTIVE;
            case FINISHED:
                return GameStatus.FINISHED;
        }
        return null;
    }

    private engine.Game findGame(String gameName) {
        for(Game game : games){
            if(game.getGameDetails().getGameName().equals(gameName))
                return game;
        }
        
        return null;
    }
    
        private void paramsCheck(int computerPlayers, int humanPlayers, int minWages, int maxWages, int initalSumOfMoney) throws NumOfPlayersException, OutOfRangeException, NumOfHumanPlayersException {
        playersCountCheck(computerPlayers, humanPlayers);
        propertiesCheck(minWages, maxWages, initalSumOfMoney);
    }

    private void propertiesCheck(int minWages, int maxWages, int initalSumOfMoney) throws OutOfRangeException {
        if (minWages < FROM_MIN_WAGES || minWages > TO_MIN_WAGES || maxWages < FROM_MAX_WAGES || maxWages > TO_MAX_WAGES || initalSumOfMoney < MIN_INITIAL_SUM_OF_MONEY || initalSumOfMoney > MAX_INITIAL_SUM_OF_MONEY) {
            throw new OutOfRangeException();
        }
    }

    private void playersCountCheck(int computerPlayers, int humanPlayers) throws NumOfHumanPlayersException, NumOfPlayersException {
        if (computerPlayers < MIN_NUM || computerPlayers > MAX_COMP_PLAYERS || humanPlayers < MIN_NUM || humanPlayers > MAX_HUMAN_PLAYERS || computerPlayers + humanPlayers > MAX_PLAYERS || computerPlayers + humanPlayers < MIN_NUM + 1) {
            throw new NumOfPlayersException(MIN_NUM + 1, MAX_PLAYERS);
        }
        if (humanPlayers == MIN_NUM) {
            throw new NumOfHumanPlayersException(MIN_NUM + 1, MAX_PLAYERS);
        }
    }

    private PlayerDetails convertPlayerDetailsToWsFormat(engine.Player.PlayerDetails playerDetails) {
        PlayerDetails result = new PlayerDetails();

        result.setMoney(playerDetails.getAmount().intValue());
        result.setName(playerDetails.getName());
        engine.Player player = findPlayer(playerDetails.getPlayerID());
        engine.Game game = findGameByPlayer(player);
        if(game.getGameDetails().getGameStatus() == engine.Game.GameStatus.WAITING)
            result.setStatus(PlayerStatus.JOINED);
        else{
            if(playerDetails.getIsActive())
                result.setStatus(PlayerStatus.ACTIVE);
            else
                result.setStatus(PlayerStatus.RETIRED);
        }
        if(playerDetails.getIsHuman())
            result.setType(PlayerType.HUMAN);
        else
            result.setType(PlayerType.COMPUTER);
        
        return result;
    }
    
    private engine.Player findPlayer(int playerId){
        for(Game game : games){
            for(Player player : game.getGameDetails().getPlayers()){
                if(player.getPlayerDetails().getPlayerID() == playerId)
                    return player;
            }
        }
        return null;
    }

    private GameDetails convertGameDetailsToWsFormat(Game.GameDetails gameDetails) {
        GameDetails result = new GameDetails();
        
        result.setComputerizedPlayers(gameDetails.getComputerPlayers());
        result.setHumanPlayers(gameDetails.getHumanPlayers());
        result.setInitalSumOfMoney(gameDetails.getInitialSumOfMoney());
        result.setIntMaxWages(gameDetails.getMaxWages());
        result.setJoinedHumanPlayers(getCountOfHumans(gameDetails));
        result.setLoadedFromXML(gameDetails.isIsGameFromXML());
        result.setMinWages(gameDetails.getMinWages());
        result.setName(gameDetails.getGameName());
        result.setRouletteType(gameDetails.getTableType() == Table.TableType.FRENCH ? ws.roulette.RouletteType.FRENCH : ws.roulette.RouletteType.AMERICAN);
        result.setStatus(getWsGameStatus(gameDetails.getGameStatus()));
        
        return result;
    }

    private Event convertEventToWSEvent(engine.Event event) {
        Event wsEvent = new Event();
        System.out.println("print event:{");
        wsEvent.setAmount(event.getAmount());
        System.out.println("amount: " + wsEvent.getAmount());
        if(event.getBetType() != null){
            wsEvent.setBetType(convertBetTypeToWs(event.getBetType()));
            System.out.println("betType: " + wsEvent.getBetType());
        }
        wsEvent.setId(event.getEventID());
        System.out.println("eventId: " + wsEvent.getId());
        if(event.getPlayerName() != null){
            wsEvent.setPlayerName(event.getPlayerName());
            System.out.println("playername: " + wsEvent.getPlayerName());
        }
        wsEvent.setTimeout(event.getTimeoutCount());
        System.out.println("timeout:" + wsEvent.getTimeout());
        wsEvent.setType(convertEventTypeToWs(event.getEventType()));
        System.out.println("eventType:" + wsEvent.getType());
        wsEvent.setWinningNumber(event.getWinningNumber());
        System.out.println("winning number: " + wsEvent.getWinningNumber());
        System.out.println("}");
        return wsEvent;
        
    }

    private EventType convertEventTypeToWs(engine.Event.EventType eventType) {
        //TODO: fix if Liron adds "Game winner"
        switch(eventType){
            case GAME_START:
                return EventType.GAME_START;
            case GAME_OVER:
                return EventType.GAME_OVER;
            case GAME_WINNER:
                return null;
            case NUMBER_RESULT:
                return EventType.WINNING_NUMBER;
            case PLAYER_RESIGNED:
                return EventType.PLAYER_RESIGNED;
            case PLAYER_BET:
                return EventType.PLAYER_BET;
            case PLAYER_FINISHED_BETTING:
                return EventType.PLAYER_FINISHED_BETTING;
            case RESULT_SCORE:
                    return EventType.RESULTS_SCORES;
        }
        
        return null;
    }

    private BetType convertBetTypeToWs(Bet.BetType betType) {
        switch(betType){
            case STREET:
                return BetType.STREET;
            case BASKET:
                return BetType.BASKET;
            case COLUMN_1:
                return BetType.COLUMN_1;
            case COLUMN_2:
                return BetType.COLUMN_2;
            case COLUMN_3:
                return BetType.COLUMN_3;
            case CORNER:
                return BetType.CORNER;
            case DERNIERE_DOUZAINE:
                return BetType.DERNIERE_DOUZAINE;
            case PAIR:
                return BetType.PAIR;
            case MANQUE:
                return BetType.MANQUE;
            case MOYENNE_DOUZAINE:
                return BetType.MOYENNE_DOUZAINE;
            case NOIR:
                return BetType.NOIR;
            case IMPAIR:
                return BetType.IMPAIR;
            case PASSE:
                return BetType.PASSE;
            case PREMIERE_DOUZAINE:
                return BetType.PREMIERE_DOUZAINE;
            case ROUGE:
                return BetType.ROUGE;
            case SIX_LINE:
                return BetType.SIX_LINE;
            case SNAKE:
                return BetType.SNAKE;
            case SPLIT:
                return BetType.SPLIT;
            case STRAIGHT:
                return BetType.STRAIGHT;
            case TOP_LINE:
                return BetType.TOP_LINE;
            case TRIO:
                return BetType.TRIO;
        }
        
        return null;
    }

    private Game findGameByPlayer(Player player) {
        for(engine.Game game : games)
            for(engine.Player currentPlayer : game.getGameDetails().getPlayers())
                if(player == currentPlayer)
                    return game;
        
        return null;
    }

    private Bet.BetType convertWsBetType(BetType betType) {
        switch (betType){
case STREET:
                return engine.bets.Bet.BetType.STREET;
            case BASKET:
                return engine.bets.Bet.BetType.BASKET;
            case COLUMN_1:
                return engine.bets.Bet.BetType.COLUMN_1;
            case COLUMN_2:
                return engine.bets.Bet.BetType.COLUMN_2;
            case COLUMN_3:
                return engine.bets.Bet.BetType.COLUMN_3;
            case CORNER:
                return engine.bets.Bet.BetType.CORNER;
            case DERNIERE_DOUZAINE:
                return engine.bets.Bet.BetType.DERNIERE_DOUZAINE;
            case PAIR:
                return engine.bets.Bet.BetType.PAIR;
            case MANQUE:
                return engine.bets.Bet.BetType.MANQUE;
            case MOYENNE_DOUZAINE:
                return engine.bets.Bet.BetType.MOYENNE_DOUZAINE;
            case NOIR:
                return engine.bets.Bet.BetType.NOIR;
            case IMPAIR:
                return engine.bets.Bet.BetType.IMPAIR;
            case PASSE:
                return engine.bets.Bet.BetType.PASSE;
            case PREMIERE_DOUZAINE:
                return engine.bets.Bet.BetType.PREMIERE_DOUZAINE;
            case ROUGE:
                return engine.bets.Bet.BetType.ROUGE;
            case SIX_LINE:
                return engine.bets.Bet.BetType.SIX_LINE;
            case SNAKE:
                return engine.bets.Bet.BetType.SNAKE;
            case SPLIT:
                return engine.bets.Bet.BetType.SPLIT;
            case STRAIGHT:
                return engine.bets.Bet.BetType.STRAIGHT;
            case TOP_LINE:
                return engine.bets.Bet.BetType.TOP_LINE;
            case TRIO:
                return engine.bets.Bet.BetType.TRIO;            
        }
        
        return null;
    }

    private boolean gameReadyToStart(Game game) {
        int numOfHumans = game.getGameDetails().getHumanPlayers();
        int counter = 0;        
        
        if(game.getGameDetails().isIsGameFromXML()){
            if (!game.getGameDetails().getPlayers().stream().noneMatch((player) -> (player.getPlayerDetails().getPlayerID() == 0))) {
                return false;
            }
        }else{
            counter = game.getGameDetails().getPlayers().stream().filter((player) -> (player.getPlayerDetails().getIsHuman())).map((_item) -> 1).reduce(counter, Integer::sum);
            if(counter == numOfHumans)
                return true;
        }
        
        return false;
    }

    private void startPlayersTimers(Game game) {
        for(Player player : game.getGameDetails().getPlayers())
            if(player.getPlayerDetails().getIsHuman()){
                PlayerTimer timer = timers.get(player.getPlayerDetails().getPlayerID());
                timer.startTimer(new RemovePlayer(game, player.getPlayerDetails().getPlayerID()), TimeUnit.SECONDS.toMillis(MAX_SECONDS_FOR_ROUND));
            }
    }

    private boolean isGameReadyForEndRound(Game game) {
        int readyPlayers = 0;
        int activeHumans = 0;
        
        for(engine.Player player : game.getGameDetails().getPlayers())
            if(player.getPlayerDetails().getIsHuman() && player.getPlayerDetails().getIsActive()){
                activeHumans++;
                if(player.getPlayerDetails().getPlayerAction() == engine.Player.PlayerAction.FINISHED_BETTING)
                readyPlayers++;    
            }
        if(readyPlayers == activeHumans){
            return true;
        }
        
        return false;
    }

    private int[] convertListToArr(List<Integer> numbers) {
        if(numbers.isEmpty())
            return null;
        
        int[] res = new int[numbers.size()];
        int i=0;
        
        for(Integer current : numbers)
            res[i++] = current;
        
        return res;
    }

    private void playComputerMoves(Game game) {
        game.getGameDetails().getPlayers().stream().filter((player) -> (!player.getPlayerDetails().getIsHuman())).filter((player) -> (player.getPlayerDetails().getAmount().intValue() > 0)).forEach((player) -> {
            player.getPlayerDetails().getBets().add(new ColorBet(BigInteger.ONE, Bet.BetType.NOIR, Color.black));
        });
    }

    private Player GetPlayerFromGame(String name, Game game) {
        for(Player player : game.getGameDetails().getPlayers())
            if(player.getPlayerDetails().getName().equals(name))
                return player;
        
        return null;
    }

    private void createComputerPlayers(Game game) {
        int computerPlayers = game.getGameDetails().getComputerPlayers();
        
        for(int i = 0 ; i < computerPlayers ; i++){
            engine.Player.PlayerDetails pd = new engine.Player.PlayerDetails("computer" + i, false,BigInteger.valueOf(game.getGameDetails().getInitialSumOfMoney()));
            Player newPlayer = new Player(pd);
            game.getGameDetails().getPlayers().add(newPlayer);
        }
    }

    private int getCountOfHumans(Game.GameDetails gameDetails) {
        int count = 0;
        for(Player player : gameDetails.getPlayers())
            if(player.getPlayerDetails().getIsHuman())
                count++;
        
        return count;
    }

    private boolean isAffordableBet(Player player, int betMoney) {
        return player.getPlayerDetails().getMoney().intValue() >= betMoney;
    }
    
    private class RemovePlayer extends TimerTask{
        private engine.Game game;
        private int playerId;

        public RemovePlayer(engine.Game game, int playerId){
            this.game = game;
            this.playerId = playerId;
        }
        
        @Override
        public void run() {
            removePlayerFromGame();
        }
        
        private void removePlayerFromGame() {
            for(Player currentPlayer : game.getGameDetails().getPlayers())
                if(currentPlayer.getPlayerDetails().getPlayerID() == playerId){
                    game.getEvents().add(new engine.Event(currentPlayer.getPlayerDetails().getName(), engine.Event.EventType.PLAYER_RESIGNED, game));
                    currentPlayer.getPlayerDetails().setIsActive(false);
                    currentPlayer.getPlayerDetails().setPlayerAction(engine.Player.PlayerAction.RESIGNED);
                    if(isGameReadyForEndRound(game))
                        endRound(game);
                    System.out.println("removed player" + currentPlayer.getPlayerDetails().getPlayerID());
                    break;
                }
        }
        
        
    }
    
    private void endRound(Game game){
        spinRoulette(game);
        engine.Event event = new engine.Event(engine.Event.EventType.NUMBER_RESULT, game);
        event.setWinningNumber(game.getTable().getCurrentBallPosition().getValue());
        game.getEvents().add(event);
        game.getGameDetails().getPlayers().stream().map((player) -> {
            if (player.getPlayerDetails().getBets().size() > 0) {
                player.getPlayerDetails().getBets().stream().forEach((bet) -> {
                    BigInteger moneyBefore = player.getPlayerDetails().getMoney();
                    player.getPlayerDetails().setMoney(player.getPlayerDetails().getMoney().add(bet.winningSum(game.getTable().getCurrentBallPosition(), game.getTable().getCells().length)));
                    int earned = player.getPlayerDetails().getMoney().intValue() - moneyBefore.intValue();
                    game.getEvents().add(new engine.Event(player.getPlayerDetails().getName(), engine.Event.EventType.RESULT_SCORE, game, null, timers.get(player.getPlayerDetails().getPlayerID()).getTimeOutCount(), null, earned));
                });
            }
            return player;
        }).forEach((player) -> {
            player.getPlayerDetails().getBets().clear();
        });
        
        System.out.println("new number="+ game.getTable().getCurrentBallPosition().getValue());
        playComputerMoves(game);
        if (!isAnybodyLeft(game))
            game.getEvents().add(new engine.Event(engine.Event.EventType.GAME_OVER, game));
    }    
    
    private void spinRoulette(engine.Game game) {
        game.getTable().spinRoulette();
    }
    
    private boolean isAnybodyLeft(engine.Game game){
        return game.getGameDetails().getPlayers().stream().anyMatch((player) -> (player.getPlayerDetails().getIsActive() && player.getPlayerDetails().getIsHuman()));        
    }
}