package Model;

import view.BattleMenuPage;

import java.util.ArrayList;

public class Game {
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int BASIC_MANA = 2;
    private static final int BASIC_FLAG_COUNT = 7;
    private static final int MY_TURN = 1;
    private static final int OPPONENT_TURN = 2;
    private Map map;
    private Deck myDeck;
    private Deck oppDeck;
    private ArrayList<Card> graveYard;
    private Card currentCard = null;
    private String myName;
    private String oppName;
    private String mode;
    private int turn;
    private int flagCount;
    private int currentTurnMana;
    private int myMana;
    private int oppMana;


    public Game(Deck myDeck, Deck oppDeck, String myName, String oppName, String mode, int flagCount) {
        this.myDeck = myDeck;
        this.oppDeck = oppDeck;
        this.myName = myName;
        this.oppName = oppName;
        this.mode = mode;
        this.flagCount = flagCount;
        graveYard = new ArrayList<>();
        turn = MY_TURN;
        currentTurnMana = BASIC_MANA;
        myMana = BASIC_MANA;
        oppMana = BASIC_MANA;
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

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public String getMyName() {
        return myName;
    }

    public String getOppName() {
        return oppName;
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

    public int getMyMana() {
        return myMana;
    }

    public int getOppMana() {
        return oppMana;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setMyMana(int myMana) {
        this.myMana = myMana;
    }

    public void setOppMana(int oppMana) {
        this.oppMana = oppMana;
    }

    public void setCurrentTurnMana(int currentTurnMana) {
        this.currentTurnMana = currentTurnMana;
    }

    public void changeTurn() {
        if (turn == 2)
            turn = 1;
        else
            turn = 2;
    }

    public void removeCardFromDeck(int turn, int cardId) {
    }

    public boolean isGameEnded() {
        return false;
    }

    public void SelectCard(int cardId){

    }

    public boolean isCardIdValid(int cardId){
        return true;

    }

    public void moveCurrentCardTo(int x, int y){}





}
