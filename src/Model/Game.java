package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int BASIC_MANA = 2;
    private static final int BASIC_FLAG_COUNT = 7;
    private static final int FIRST_PLAYER_TURN = 1;
    private static final int SECOND_PLAYER_TURN = 2;
    private Map map;
    private Deck myDeck;
    private Deck oppDeck;

    private User firstUser;
    private User secondUser;

    private ArrayList<Card> firstPlayerGraveYard;
    private ArrayList<Card> secondPlayerGraveYard;

    private HashMap<Integer, Card> firstPlayerHand;
    private HashMap<Integer, Card> secondPlayerHand;

    private Card currentCard = null;
    private Card NextCard = null;


    private String firstPlayerName;
    private String secondPlayerName;
    private String mode;
    private String kind;

    private int turn;
    private int flagCount;
    private int currentTurnMana;
    private int firstPlayerMana;
    private int secondPlayerMana;


    public Game(Deck myDeck, Deck oppDeck, String firstPlayerName, String secondPlayerName, String mode, int flagCount) {
        this.myDeck = myDeck;
        this.oppDeck = oppDeck;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.mode = mode;
        this.flagCount = flagCount;
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        currentTurnMana = BASIC_MANA;
        firstPlayerMana = BASIC_MANA;
        secondPlayerMana = BASIC_MANA;
    }

    public Game(User firstUser, User secondUser, String mode, String kind, int flagCount) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.mode = mode;
        this.flagCount = flagCount;
        this.kind = kind;
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        currentTurnMana = BASIC_MANA;
        firstPlayerMana = BASIC_MANA;
        secondPlayerMana = BASIC_MANA;
    }

    public Map getMap() {
        return map;
    }

    public Deck getMyDeck() {
        return myDeck;
    }

    public Deck getOppDeck() {
        return oppDeck;
    }

    public ArrayList<Card> getSecondPlayerGraveYard() {
        return secondPlayerGraveYard;
    }

    public HashMap<Integer, Card> getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public HashMap<Integer, Card> getSecondPlayerHand() {
        return secondPlayerHand;
    }

    public ArrayList<Card> getGraveYard() {
        return firstPlayerGraveYard;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public String getMode() {
        return mode;
    }

    public int getTurn() {
        return turn;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public int getFirstPlayerMana() {
        return firstPlayerMana;
    }

    public int getSecondPlayerMana() {
        return secondPlayerMana;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setFirstPlayerMana(int firstPlayerMana) {
        this.firstPlayerMana = firstPlayerMana;
    }

    public void setSecondPlayerMana(int secondPlayerMana) {
        this.secondPlayerMana = secondPlayerMana;
    }

    public void setCurrentTurnMana(int currentTurnMana) {
        this.currentTurnMana = currentTurnMana;
    }

    public void changeTurn() {
        if (turn == SECOND_PLAYER_TURN)
            turn = FIRST_PLAYER_TURN;
        else
            turn = SECOND_PLAYER_TURN;
    }

    public void removeCardFromDeck(int turn, int cardId) {
    }

    public boolean isGameEnded() {
        return false;
    }

    public void SelectCard(int cardId){
        if(getTurn() == FIRST_PLAYER_TURN)
            currentCard = map.getFirstPlayerCellCard().get(cardId);
        else
            currentCard = map.getSecondPlayerCellCard().get(cardId);
    }

    public boolean isCardInMap(int cardId){


    }

    public void moveCurrentCardTo(int x, int y){
        map.getCells()[x][y].setCard(currentCard);
        currentCard.setRow(x);
        currentCard.setColumn(y);
    }








}
