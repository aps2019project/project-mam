package Model;

public class Game {
    private User firstPlayer, secondPlayer;
    private String mode;
    private int turn;
    private String kind;
    private int firstPlayerMana, secondPlayerMana;
    private Map map;
    private Deck firstPlayerDeck, secondPlayerDeck;


    public Game(User firstPlayer, User secondPlayer, String mode, String kind) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.mode = mode;
        this.kind = kind;
        map = new Map();
        turn = 0;
        firstPlayerMana = 2;
        secondPlayerMana = 2;
    }

    public User getFirstPlayer() {
        return firstPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public String getMode() {
        return mode;
    }

    public int getTurn() {
        return turn;
    }

    public String getKind() {
        return kind;
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

    public void changeTurn(){
        if (turn == 2)
            turn = 1;
        else
            turn = 2;
    }

    public void removeCardFromDeck(int turn, int cardId){}

    public boolean isGameEnded(){
        return false;
    }

    
}
