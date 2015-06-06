/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Game.GameStatus;
import engine.bets.Bet;
import engine.bets.Bet.BetType;
import static engine.bets.Bet.BetType.BASKET;
import static engine.bets.Bet.BetType.COLUMN_1;
import static engine.bets.Bet.BetType.COLUMN_2;
import static engine.bets.Bet.BetType.COLUMN_3;
import static engine.bets.Bet.BetType.CORNER;
import static engine.bets.Bet.BetType.DERNIERE_DOUZAINE;
import static engine.bets.Bet.BetType.IMPAIR;
import static engine.bets.Bet.BetType.MANQUE;
import static engine.bets.Bet.BetType.MOYENNE_DOUZAINE;
import static engine.bets.Bet.BetType.NOIR;
import static engine.bets.Bet.BetType.PAIR;
import static engine.bets.Bet.BetType.PASSE;
import static engine.bets.Bet.BetType.PREMIERE_DOUZAINE;
import static engine.bets.Bet.BetType.ROUGE;
import static engine.bets.Bet.BetType.SIX_LINE;
import static engine.bets.Bet.BetType.SNAKE;
import static engine.bets.Bet.BetType.SPLIT;
import static engine.bets.Bet.BetType.STRAIGHT;
import static engine.bets.Bet.BetType.STREET;
import static engine.bets.Bet.BetType.TOP_LINE;
import static engine.bets.Bet.BetType.TRIO;
import engine.bets.NumbersBet;
import generated.Bets;
import generated.ObjectFactory;
import generated.PlayerType;
import generated.Players;
import generated.Roulette;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Yuval Segall
 */
public class XMLGame {

    public static Game getXMLGame(InputStream XMLStream) throws JAXBException, BadParamsException {
        try {
            Game myGame;
            JAXBContext jaxbContext = JAXBContext.newInstance(Roulette.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Roulette xmlGame = (Roulette) jaxbUnmarshaller.unmarshal(XMLStream);

            String gameName = xmlGame.getName();
            Table.TableType tableType = xmlGame.getTableType().equals(generated.TableType.AMERICAN) ? Table.TableType.AMERICAN : Table.TableType.FRENCH;
            int minWages = xmlGame.getMinBetsPerPlayer();
            int maxWages = xmlGame.getMaxBetsPerPlayer();
            int initalSumOfMoney = xmlGame.getInitSumOfMoney();

            List<Player> players = new ArrayList<>();
            for (Players.Player xmlPlayer : xmlGame.getPlayers().getPlayer()) {
                Player.PlayerDetails playerD = new Player.PlayerDetails();
                playerD.setName(xmlPlayer.getName());
                if (xmlPlayer.getType().equals(PlayerType.HUMAN)) {
                    playerD.setIsHuman(true);
                } else {
                    playerD.setIsHuman(false);
                }
                playerD.setMoney(xmlPlayer.getMoney());
                if (xmlPlayer.getBets() != null) {
                    List<Bet> bets = new ArrayList<>();
                    for (generated.Bet xmlBet : xmlPlayer.getBets().getBet()) {
                        BetType betType = getMyBetTypeFromXMLBetType(xmlBet.getType());
                        int[] numbers = xmlBet.getNumber().isEmpty() ? null : new int[xmlBet.getNumber().size()];
                        int i = 0;
                        for (int num : xmlBet.getNumber()) {
                            numbers[i++] = num;
                        }
                        Bet myBet = Bet.makeBet(betType, xmlBet.getAmount(), numbers, tableType);
                        bets.add(myBet);
                    }
                    playerD.setBets(bets);
                }
                players.add(new Player(playerD));
            }

            myGame = new Game(new Game.GameDetails(gameName, minWages, maxWages, tableType, initalSumOfMoney, players, Game.GameStatus.ACTIVE));

            return myGame;
        } catch (JAXBException | BadParamsException ex) {
            throw new BadParamsException();
        }
    }

    public static void setXMLGame(String XMLFile, Game myGame) throws JAXBException {
        ObjectFactory of = new ObjectFactory();
        Roulette xmlGame = of.createRoulette();

        xmlGame.setInitSumOfMoney(myGame.getGameDetails().getInitialSumOfMoney());
        xmlGame.setMaxBetsPerPlayer(myGame.getGameDetails().getMaxWages());
        xmlGame.setMinBetsPerPlayer(myGame.getGameDetails().getMinWages());
        xmlGame.setName(myGame.getGameDetails().getGameName());
        if (myGame.getTable().getTableType().equals(Table.TableType.AMERICAN)) {
            xmlGame.setTableType(generated.TableType.AMERICAN);
        } else {
            xmlGame.setTableType(generated.TableType.FRENCH);
        }

        Players xmlPlayers = new Players();

        for (Player.PlayerDetails myPlayerD : myGame.getGameDetails().getPlayersDetails()) {
            Players.Player xmlPlayer = new Players.Player();
            xmlPlayer.setMoney(myPlayerD.getMoney());
            xmlPlayer.setName(myPlayerD.getName());
            if (myPlayerD.getIsHuman()) {
                xmlPlayer.setType(PlayerType.HUMAN);
            } else {
                xmlPlayer.setType(PlayerType.COMPUTER);
            }

            if (myPlayerD.getBets() != null) {
                Bets xmlPlayerBets = new Bets();
                myPlayerD.getBets().stream().map((myBet) -> {
                    generated.Bet xmlPlayerBet = new generated.Bet();
                    xmlPlayerBet.setAmount(myBet.getBetAmount());
                    xmlPlayerBet.setType(getXMLBetTypeFromMyBetType(myBet.getBetType()));
                    if (myBet.getBetType().isAskUserForNumbers()) {
                        List<Integer> numbers = new ArrayList<>();
                        if (myBet instanceof NumbersBet) {
                            for (int num : ((NumbersBet) myBet).getNumbers()) {
                                numbers.add(num);
                            }
                        }
                        xmlPlayerBet.getNumber().addAll(numbers);
                    }
                    return xmlPlayerBet;
                }).forEach((xmlPlayerBet) -> {
                    xmlPlayerBets.getBet().add(xmlPlayerBet);
                });
                xmlPlayer.setBets(xmlPlayerBets);
            }
            xmlPlayers.getPlayer().add(xmlPlayer);
        }

        xmlGame.setPlayers(xmlPlayers);

        File file = new File(XMLFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Roulette.class
        );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);

        jaxbMarshaller.marshal(xmlGame, file);

//            jaxbMarshaller.marshal(xmlGame, System.out);
    }

    private static Bet.BetType getMyBetTypeFromXMLBetType(generated.BetType xmlBetType) {
        switch (xmlBetType) {
            case BASKET:
                return Bet.BetType.BASKET;
            case COLUMN_1:
                return Bet.BetType.COLUMN_1;
            case COLUMN_2:
                return Bet.BetType.COLUMN_2;
            case COLUMN_3:
                return Bet.BetType.COLUMN_3;
            case CORNER:
                return Bet.BetType.CORNER;
            case DERNIERE_DOUZAINE:
                return Bet.BetType.DERNIERE_DOUZAINE;
            case IMPAIR:
                return Bet.BetType.IMPAIR;
            case MANQUE:
                return Bet.BetType.MANQUE;
            case MOYENNE_DOUZAINE:
                return Bet.BetType.MOYENNE_DOUZAINE;
            case NOIR:
                return Bet.BetType.NOIR;
            case PAIR:
                return Bet.BetType.PAIR;
            case PASSE:
                return Bet.BetType.PASSE;
            case PREMIERE_DOUZAINE:
                return Bet.BetType.PREMIERE_DOUZAINE;
            case ROUGE:
                return Bet.BetType.ROUGE;
            case SIX_LINE:
                return Bet.BetType.SIX_LINE;
            case SNAKE:
                return Bet.BetType.SNAKE;
            case SPLIT:
                return Bet.BetType.SPLIT;
            case STRAIGHT:
                return Bet.BetType.STRAIGHT;
            case STREET:
                return Bet.BetType.STREET;
            case TOP_LINE:
                return Bet.BetType.TOP_LINE;
            case TRIO:
                return Bet.BetType.TRIO;
        }
        return null;
    }

    private static generated.BetType getXMLBetTypeFromMyBetType(Bet.BetType myBetType) {
        switch (myBetType) {
            case BASKET:
                return generated.BetType.BASKET;
            case COLUMN_1:
                return generated.BetType.COLUMN_1;
            case COLUMN_2:
                return generated.BetType.COLUMN_2;
            case COLUMN_3:
                return generated.BetType.COLUMN_3;
            case CORNER:
                return generated.BetType.CORNER;
            case DERNIERE_DOUZAINE:
                return generated.BetType.DERNIERE_DOUZAINE;
            case IMPAIR:
                return generated.BetType.IMPAIR;
            case MANQUE:
                return generated.BetType.MANQUE;
            case MOYENNE_DOUZAINE:
                return generated.BetType.MOYENNE_DOUZAINE;
            case NOIR:
                return generated.BetType.NOIR;
            case PAIR:
                return generated.BetType.PAIR;
            case PASSE:
                return generated.BetType.PASSE;
            case PREMIERE_DOUZAINE:
                return generated.BetType.PREMIERE_DOUZAINE;
            case ROUGE:
                return generated.BetType.ROUGE;
            case SIX_LINE:
                return generated.BetType.SIX_LINE;
            case SNAKE:
                return generated.BetType.SNAKE;
            case SPLIT:
                return generated.BetType.SPLIT;
            case STRAIGHT:
                return generated.BetType.STRAIGHT;
            case STREET:
                return generated.BetType.STREET;
            case TOP_LINE:
                return generated.BetType.TOP_LINE;
            case TRIO:
                return generated.BetType.TRIO;
        }
        return null;
    }
}
