
package ws.roulette;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.roulette package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPlayerDetailsResponse_QNAME = new QName("http://roulette.ws/", "getPlayerDetailsResponse");
    private final static QName _InvalidXML_QNAME = new QName("http://roulette.ws/", "InvalidXML");
    private final static QName _CreateGameFromXML_QNAME = new QName("http://roulette.ws/", "createGameFromXML");
    private final static QName _MakeBet_QNAME = new QName("http://roulette.ws/", "makeBet");
    private final static QName _GetGameDetailsResponse_QNAME = new QName("http://roulette.ws/", "getGameDetailsResponse");
    private final static QName _DuplicateGameName_QNAME = new QName("http://roulette.ws/", "DuplicateGameName");
    private final static QName _MakeBetResponse_QNAME = new QName("http://roulette.ws/", "makeBetResponse");
    private final static QName _GetPlayerDetails_QNAME = new QName("http://roulette.ws/", "getPlayerDetails");
    private final static QName _Resign_QNAME = new QName("http://roulette.ws/", "resign");
    private final static QName _GetPlayersDetailsResponse_QNAME = new QName("http://roulette.ws/", "getPlayersDetailsResponse");
    private final static QName _CreateGameFromXMLResponse_QNAME = new QName("http://roulette.ws/", "createGameFromXMLResponse");
    private final static QName _ResignResponse_QNAME = new QName("http://roulette.ws/", "resignResponse");
    private final static QName _GetWaitingGamesResponse_QNAME = new QName("http://roulette.ws/", "getWaitingGamesResponse");
    private final static QName _GetGameDetails_QNAME = new QName("http://roulette.ws/", "getGameDetails");
    private final static QName _JoinGameResponse_QNAME = new QName("http://roulette.ws/", "joinGameResponse");
    private final static QName _InvalidParameters_QNAME = new QName("http://roulette.ws/", "InvalidParameters");
    private final static QName _FinishBetting_QNAME = new QName("http://roulette.ws/", "finishBetting");
    private final static QName _GameDoesNotExists_QNAME = new QName("http://roulette.ws/", "GameDoesNotExists");
    private final static QName _GetEvents_QNAME = new QName("http://roulette.ws/", "getEvents");
    private final static QName _JoinGame_QNAME = new QName("http://roulette.ws/", "joinGame");
    private final static QName _CreateGameResponse_QNAME = new QName("http://roulette.ws/", "createGameResponse");
    private final static QName _GetPlayersDetails_QNAME = new QName("http://roulette.ws/", "getPlayersDetails");
    private final static QName _GetEventsResponse_QNAME = new QName("http://roulette.ws/", "getEventsResponse");
    private final static QName _CreateGame_QNAME = new QName("http://roulette.ws/", "createGame");
    private final static QName _FinishBettingResponse_QNAME = new QName("http://roulette.ws/", "finishBettingResponse");
    private final static QName _GetWaitingGames_QNAME = new QName("http://roulette.ws/", "getWaitingGames");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.roulette
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWaitingGamesResponse }
     * 
     */
    public GetWaitingGamesResponse createGetWaitingGamesResponse() {
        return new GetWaitingGamesResponse();
    }

    /**
     * Create an instance of {@link GetGameDetails }
     * 
     */
    public GetGameDetails createGetGameDetails() {
        return new GetGameDetails();
    }

    /**
     * Create an instance of {@link ResignResponse }
     * 
     */
    public ResignResponse createResignResponse() {
        return new ResignResponse();
    }

    /**
     * Create an instance of {@link InvalidParameters }
     * 
     */
    public InvalidParameters createInvalidParameters() {
        return new InvalidParameters();
    }

    /**
     * Create an instance of {@link JoinGameResponse }
     * 
     */
    public JoinGameResponse createJoinGameResponse() {
        return new JoinGameResponse();
    }

    /**
     * Create an instance of {@link GetEvents }
     * 
     */
    public GetEvents createGetEvents() {
        return new GetEvents();
    }

    /**
     * Create an instance of {@link JoinGame }
     * 
     */
    public JoinGame createJoinGame() {
        return new JoinGame();
    }

    /**
     * Create an instance of {@link GameDoesNotExists }
     * 
     */
    public GameDoesNotExists createGameDoesNotExists() {
        return new GameDoesNotExists();
    }

    /**
     * Create an instance of {@link FinishBetting }
     * 
     */
    public FinishBetting createFinishBetting() {
        return new FinishBetting();
    }

    /**
     * Create an instance of {@link CreateGame }
     * 
     */
    public CreateGame createCreateGame() {
        return new CreateGame();
    }

    /**
     * Create an instance of {@link FinishBettingResponse }
     * 
     */
    public FinishBettingResponse createFinishBettingResponse() {
        return new FinishBettingResponse();
    }

    /**
     * Create an instance of {@link GetWaitingGames }
     * 
     */
    public GetWaitingGames createGetWaitingGames() {
        return new GetWaitingGames();
    }

    /**
     * Create an instance of {@link CreateGameResponse }
     * 
     */
    public CreateGameResponse createCreateGameResponse() {
        return new CreateGameResponse();
    }

    /**
     * Create an instance of {@link GetPlayersDetails }
     * 
     */
    public GetPlayersDetails createGetPlayersDetails() {
        return new GetPlayersDetails();
    }

    /**
     * Create an instance of {@link GetEventsResponse }
     * 
     */
    public GetEventsResponse createGetEventsResponse() {
        return new GetEventsResponse();
    }

    /**
     * Create an instance of {@link InvalidXML }
     * 
     */
    public InvalidXML createInvalidXML() {
        return new InvalidXML();
    }

    /**
     * Create an instance of {@link CreateGameFromXML }
     * 
     */
    public CreateGameFromXML createCreateGameFromXML() {
        return new CreateGameFromXML();
    }

    /**
     * Create an instance of {@link MakeBet }
     * 
     */
    public MakeBet createMakeBet() {
        return new MakeBet();
    }

    /**
     * Create an instance of {@link GetPlayerDetailsResponse }
     * 
     */
    public GetPlayerDetailsResponse createGetPlayerDetailsResponse() {
        return new GetPlayerDetailsResponse();
    }

    /**
     * Create an instance of {@link GetGameDetailsResponse }
     * 
     */
    public GetGameDetailsResponse createGetGameDetailsResponse() {
        return new GetGameDetailsResponse();
    }

    /**
     * Create an instance of {@link GetPlayerDetails }
     * 
     */
    public GetPlayerDetails createGetPlayerDetails() {
        return new GetPlayerDetails();
    }

    /**
     * Create an instance of {@link DuplicateGameName }
     * 
     */
    public DuplicateGameName createDuplicateGameName() {
        return new DuplicateGameName();
    }

    /**
     * Create an instance of {@link MakeBetResponse }
     * 
     */
    public MakeBetResponse createMakeBetResponse() {
        return new MakeBetResponse();
    }

    /**
     * Create an instance of {@link CreateGameFromXMLResponse }
     * 
     */
    public CreateGameFromXMLResponse createCreateGameFromXMLResponse() {
        return new CreateGameFromXMLResponse();
    }

    /**
     * Create an instance of {@link GetPlayersDetailsResponse }
     * 
     */
    public GetPlayersDetailsResponse createGetPlayersDetailsResponse() {
        return new GetPlayersDetailsResponse();
    }

    /**
     * Create an instance of {@link Resign }
     * 
     */
    public Resign createResign() {
        return new Resign();
    }

    /**
     * Create an instance of {@link RouletteFault }
     * 
     */
    public RouletteFault createRouletteFault() {
        return new RouletteFault();
    }

    /**
     * Create an instance of {@link GameDetails }
     * 
     */
    public GameDetails createGameDetails() {
        return new GameDetails();
    }

    /**
     * Create an instance of {@link PlayerDetails }
     * 
     */
    public PlayerDetails createPlayerDetails() {
        return new PlayerDetails();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getPlayerDetailsResponse")
    public JAXBElement<GetPlayerDetailsResponse> createGetPlayerDetailsResponse(GetPlayerDetailsResponse value) {
        return new JAXBElement<GetPlayerDetailsResponse>(_GetPlayerDetailsResponse_QNAME, GetPlayerDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "InvalidXML")
    public JAXBElement<InvalidXML> createInvalidXML(InvalidXML value) {
        return new JAXBElement<InvalidXML>(_InvalidXML_QNAME, InvalidXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameFromXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "createGameFromXML")
    public JAXBElement<CreateGameFromXML> createCreateGameFromXML(CreateGameFromXML value) {
        return new JAXBElement<CreateGameFromXML>(_CreateGameFromXML_QNAME, CreateGameFromXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "makeBet")
    public JAXBElement<MakeBet> createMakeBet(MakeBet value) {
        return new JAXBElement<MakeBet>(_MakeBet_QNAME, MakeBet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getGameDetailsResponse")
    public JAXBElement<GetGameDetailsResponse> createGetGameDetailsResponse(GetGameDetailsResponse value) {
        return new JAXBElement<GetGameDetailsResponse>(_GetGameDetailsResponse_QNAME, GetGameDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateGameName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "DuplicateGameName")
    public JAXBElement<DuplicateGameName> createDuplicateGameName(DuplicateGameName value) {
        return new JAXBElement<DuplicateGameName>(_DuplicateGameName_QNAME, DuplicateGameName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "makeBetResponse")
    public JAXBElement<MakeBetResponse> createMakeBetResponse(MakeBetResponse value) {
        return new JAXBElement<MakeBetResponse>(_MakeBetResponse_QNAME, MakeBetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getPlayerDetails")
    public JAXBElement<GetPlayerDetails> createGetPlayerDetails(GetPlayerDetails value) {
        return new JAXBElement<GetPlayerDetails>(_GetPlayerDetails_QNAME, GetPlayerDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "resign")
    public JAXBElement<Resign> createResign(Resign value) {
        return new JAXBElement<Resign>(_Resign_QNAME, Resign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayersDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getPlayersDetailsResponse")
    public JAXBElement<GetPlayersDetailsResponse> createGetPlayersDetailsResponse(GetPlayersDetailsResponse value) {
        return new JAXBElement<GetPlayersDetailsResponse>(_GetPlayersDetailsResponse_QNAME, GetPlayersDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameFromXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "createGameFromXMLResponse")
    public JAXBElement<CreateGameFromXMLResponse> createCreateGameFromXMLResponse(CreateGameFromXMLResponse value) {
        return new JAXBElement<CreateGameFromXMLResponse>(_CreateGameFromXMLResponse_QNAME, CreateGameFromXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResignResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "resignResponse")
    public JAXBElement<ResignResponse> createResignResponse(ResignResponse value) {
        return new JAXBElement<ResignResponse>(_ResignResponse_QNAME, ResignResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingGamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getWaitingGamesResponse")
    public JAXBElement<GetWaitingGamesResponse> createGetWaitingGamesResponse(GetWaitingGamesResponse value) {
        return new JAXBElement<GetWaitingGamesResponse>(_GetWaitingGamesResponse_QNAME, GetWaitingGamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getGameDetails")
    public JAXBElement<GetGameDetails> createGetGameDetails(GetGameDetails value) {
        return new JAXBElement<GetGameDetails>(_GetGameDetails_QNAME, GetGameDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "joinGameResponse")
    public JAXBElement<JoinGameResponse> createJoinGameResponse(JoinGameResponse value) {
        return new JAXBElement<JoinGameResponse>(_JoinGameResponse_QNAME, JoinGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "InvalidParameters")
    public JAXBElement<InvalidParameters> createInvalidParameters(InvalidParameters value) {
        return new JAXBElement<InvalidParameters>(_InvalidParameters_QNAME, InvalidParameters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishBetting }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "finishBetting")
    public JAXBElement<FinishBetting> createFinishBetting(FinishBetting value) {
        return new JAXBElement<FinishBetting>(_FinishBetting_QNAME, FinishBetting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameDoesNotExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "GameDoesNotExists")
    public JAXBElement<GameDoesNotExists> createGameDoesNotExists(GameDoesNotExists value) {
        return new JAXBElement<GameDoesNotExists>(_GameDoesNotExists_QNAME, GameDoesNotExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getEvents")
    public JAXBElement<GetEvents> createGetEvents(GetEvents value) {
        return new JAXBElement<GetEvents>(_GetEvents_QNAME, GetEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "joinGame")
    public JAXBElement<JoinGame> createJoinGame(JoinGame value) {
        return new JAXBElement<JoinGame>(_JoinGame_QNAME, JoinGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "createGameResponse")
    public JAXBElement<CreateGameResponse> createCreateGameResponse(CreateGameResponse value) {
        return new JAXBElement<CreateGameResponse>(_CreateGameResponse_QNAME, CreateGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayersDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getPlayersDetails")
    public JAXBElement<GetPlayersDetails> createGetPlayersDetails(GetPlayersDetails value) {
        return new JAXBElement<GetPlayersDetails>(_GetPlayersDetails_QNAME, GetPlayersDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getEventsResponse")
    public JAXBElement<GetEventsResponse> createGetEventsResponse(GetEventsResponse value) {
        return new JAXBElement<GetEventsResponse>(_GetEventsResponse_QNAME, GetEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "createGame")
    public JAXBElement<CreateGame> createCreateGame(CreateGame value) {
        return new JAXBElement<CreateGame>(_CreateGame_QNAME, CreateGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishBettingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "finishBettingResponse")
    public JAXBElement<FinishBettingResponse> createFinishBettingResponse(FinishBettingResponse value) {
        return new JAXBElement<FinishBettingResponse>(_FinishBettingResponse_QNAME, FinishBettingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingGames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://roulette.ws/", name = "getWaitingGames")
    public JAXBElement<GetWaitingGames> createGetWaitingGames(GetWaitingGames value) {
        return new JAXBElement<GetWaitingGames>(_GetWaitingGames_QNAME, GetWaitingGames.class, null, value);
    }

}
