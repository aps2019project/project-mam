package Model;

import java.util.ArrayList;

public class Game {
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int BASIC_MANA = 2;
    private static final int BASIC_FLAG_COUNT = 7;
    private String firstPlayer;
    private String secondPlayer;
    private String mode;
    private String playerCount;
    private int turn;
    private int flagCount;
    private int firstPlayerMana;
    private int secondPlayerMana;
    private Map map;
    private Deck firstPlayerDeck;
    private Deck secondPlayerDeck;
    private ArrayList<Deck> graveYard;
    private Card currentCard = null;

    public Game(String firstPlayer, String secondPlayer, String mode, String playerCount,
                int flagCount, Deck firstPlayerDeck, Deck secondPlayerDeck) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.mode = mode;
        this.playerCount = playerCount;
        this.flagCount = flagCount;
        this.firstPlayerDeck = firstPlayerDeck;
        this.secondPlayerDeck = secondPlayerDeck;
        graveYard = new ArrayList<>();
        map = new Map();


    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public String getMode() {
        return mode;
    }

    public int getTurn() {
        return turn;
    }

    public String getKind() {
        return playerCount;
    }

    public int getFirstPlayerMana() {
        return firstPlayerMana;
    }

    public int getSecondPlayerMana() {
        return secondPlayerMana;
    }

    public Map getMap() {
        return map;
    }

    public Deck getFirstPlayerDeck() {
        return firstPlayerDeck;
    }

    public Deck getSecondPlayerDeck() {
        return secondPlayerDeck;
    }

    public void setFirstPlayerMana(int firstPlayerMana) {
        this.firstPlayerMana = firstPlayerMana;
    }

    public void setSecondPlayerMana(int secondPlayerMana) {
        this.secondPlayerMana = secondPlayerMana;
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


}
