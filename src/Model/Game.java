package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Game {
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int BASIC_FLAG_COUNT = 7;
    private static final int FIRST_PLAYER_TURN = 1;
    private static final int SECOND_PLAYER_TURN = 2;
    private static int basicMana = 2;
    private Map map;
    private Deck firstPlayerDeck;
    private Deck secondPlayerDeck;

    private User firstUser;
    private User secondUser;

    private ArrayList<Card> firstPlayerGraveYard;
    private ArrayList<Card> secondPlayerGraveYard;

    private HashMap<Integer, Card> firstPlayerHand;
    private HashMap<Integer, Card> secondPlayerHand;

    private Card currentCard = null;
    private Card nextfirstPlayerCard = null;
    private Card nextSecondPlayerCard = null;


    private String firstPlayerName;
    private String secondPlayerName;
    private String mode;
    private String kind;

    private int turn;
    private int flagCount;
    private int currentTurnMana;
    private int firstPlayerMana;
    private int secondPlayerMana;


    public Game(Deck firstPlayerDeck, Deck secondPlayerDeck, String firstPlayerName,
                String secondPlayerName, String mode, int flagCount) {
        this.firstPlayerDeck = firstPlayerDeck;
        this.secondPlayerDeck = secondPlayerDeck;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.mode = mode;
        this.flagCount = flagCount;
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        currentTurnMana = basicMana;
        firstPlayerMana = basicMana;
        secondPlayerMana = basicMana;
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
        currentTurnMana = basicMana;
        firstPlayerMana = basicMana;
        secondPlayerMana = basicMana;
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
        turn++;
    }

    public boolean isGameEnded() {
        return false;
    }

    public void SelectCard(int cardId) {
        if (getTurn() % 2 == FIRST_PLAYER_TURN)
            currentCard = map.getFirstPlayerCellCard().get(cardId).getCard();
        else
            currentCard = map.getSecondPlayerCellCard().get(cardId).getCard();
    }

    public boolean isCardInFirstPlayerCellCard(int cardId) {
        return map.getFirstPlayerCellCard().get(cardId) != null;
    }

    public boolean isCardInSecondPlayerCellCard(int cardId) {
        return map.getSecondPlayerCellCard().get(cardId) != null;
    }

    public void moveCurrentCardTo(int x, int y) {
        if (turn % 2 == 1) {
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getFirstPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getFirstPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getFirstPlayerCellCard().get(currentCard.getId()).setCard(null);
        } else {
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getSecondPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getSecondPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getSecondPlayerCellCard().get(currentCard.getId()).setCard(null);
        }
        currentCard.setCanMove(false);
        currentCard.setRow(x);
        currentCard.setColumn(y);
    }

    public void setPlayersHand() {
        Random random = new Random();
        int rand;
        while (firstPlayerHand.size() <= 5) {
            rand = random.nextInt(firstPlayerDeck.getCards().size());
            addRandomCardFromFirstPlayerDeckToHand(rand);
        }
        while (secondPlayerHand.size() <= 5) {
            rand = random.nextInt(secondPlayerDeck.getCards().size());
            addRandomCardFromSecondPlayerDeckToHand(rand);
        }
    }

    public void addRandomCardFromFirstPlayerDeckToHand(int rand) {
        if (firstPlayerDeck.getCards().size() != 0) {
            firstPlayerHand.put(firstPlayerDeck.getCards().get(rand).getId(), firstPlayerDeck.getCards().get(rand));
            firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(rand));
        }
    }

    public void addRandomCardFromSecondPlayerDeckToHand(int rand) {
        if (secondPlayerDeck.getCards().size() != 0) {
            firstPlayerHand.put(firstPlayerDeck.getCards().get(rand).getId(), firstPlayerDeck.getCards().get(rand));
            secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(rand));
        }
    }

    public void updateFirstPlayerHand() {
        if (nextfirstPlayerCard != null) {
            firstPlayerHand.put(nextfirstPlayerCard.getId(), nextfirstPlayerCard);
            nextfirstPlayerCard = null;
            Random random = new Random();
            while (firstPlayerHand.size() < 5) {
                addRandomCardFromFirstPlayerDeckToHand(random.nextInt());
            }
        }
    }

    public void updateSecondPlayerHand() {
        if (nextSecondPlayerCard != null) {
            secondPlayerHand.put(nextSecondPlayerCard.getId(), nextSecondPlayerCard);
            nextSecondPlayerCard = null;
            Random rand = new Random();
            while (secondPlayerHand.size() < 5)
                addRandomCardFromSecondPlayerDeckToHand(rand.nextInt());
        }
    }

    public void setNextfirstPlayerCard() {
        if (firstPlayerHand.size() < 5) {
            if (firstPlayerDeck.getCards().size() != 0) {
                Random random = new Random();
                nextfirstPlayerCard = firstPlayerDeck.getCards().get(random.nextInt());
            } else
                nextfirstPlayerCard = null;
        }
    }

    public void setNextSecondPlayerCard() {
        if (secondPlayerHand.size() < 5) {
            if (secondPlayerDeck.getCards().size() != 0) {
                Random random = new Random();
                int rand = random.nextInt();
                nextSecondPlayerCard = secondPlayerDeck.getCards().get(rand);
            } else
                nextSecondPlayerCard = null;
        }
    }

    public boolean isCardInPlayer1Hand(String cardName) {
        for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
            if (entry.getValue().getName().equals(cardName))
                return true;
        }
        return false;
    }

    public boolean isCardInplayer2Hand(String cardName) {
        for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
            if (entry.getValue().getName().equals(cardName))
                return true;
        }
        return false;
    }

    public String getGameInfo() {
        StringBuilder info = new StringBuilder();
        info.append("first player mana : ").append(firstPlayerMana).append("\nsecond player mana : ").
                append(secondPlayerMana);
        switch (mode) {
            case "1":
                int hero1 = 0;
                int hero2 = 0;
                for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                    if (entry.getValue().getCard().getCardType().equalsIgnoreCase("hero")){
                        hero1 = entry.getValue().getCard().getHP();
                        break;
                    }
                }
                for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                    if (entry.getValue().getCard().getCardType().equalsIgnoreCase("hero")){
                        hero2 = entry.getValue().getCard().getHP();
                        break;
                    }
                }
                info.append("first player hero HP: ").append(hero1).append("\n");
                info.append("second player hero HP: ").append(hero2);
                break;
            case "2":
                info.append("\nflag location : ").append(map.getFlags().get(0).getRow()).append(", ").
                        append(map.getFlags().get(0).getColumn()).append("\nflag keeper : ").
                        append(map.getFlags().get(0).getCard().getName());
                break;
            case "3":
                info.append("flag keeper from first team :\n");
                for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet())
                    if (entry.getValue().getFlagCount() != 0)
                        info.append(entry.getValue().getCard().getName()).append(" | ");
                info.append("flag keeper from second team :\n");
                for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet())
                    if (entry.getValue().getFlagCount() != 0)
                        info.append(entry.getValue().getCard().getName()).append(" | ");
        }
        return info.toString();
    }

    public String showMyMinions() {
        StringBuilder info = new StringBuilder();
        if (getTurn()%2 == 1){
            for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        else {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        return info.toString();
    }

    public String showOpMinions() {
        StringBuilder info = new StringBuilder();
        if (getTurn()%2 == 0){
            for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        else {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        return info.toString();
    }

    public String showCardInfo(int cardId){
        if (getTurn()%2 == 1) {
            for (Card card : firstPlayerDeck.getCards()) {
                if (card.getId() == cardId) {
                    return card.getCardInfoInGame();
                }
            }
        }
        else {
            for (Card card : secondPlayerDeck.getCards()) {
                if (card.getId() == cardId){
                    return card.getCardInfoInGame();
                }
            }
        }
        return "";
    }

    public String showHand() {
        StringBuilder info = new StringBuilder();
        if (turn % 2 == 1) {
            for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
                info.append(entry.getValue().getImportantInfo()).append("\n");
            }
            info.append(nextfirstPlayerCard.getImportantInfo());
        }
        else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                info.append(entry.getValue().getImportantInfo()).append("\n");
            }
            info.append(nextSecondPlayerCard.getImportantInfo());
        }
        return info.toString();
    }

    private void insertCard(String cardName, int x, int y, HashMap<Integer, Card> Hand) {
        for (java.util.Map.Entry<Integer, Card> entry : Hand.entrySet())
            if (entry.getValue().getName().equals(cardName)) {
                map.getCells()[x][y].setCard(entry.getValue());
                entry.getValue().setRow(x);
                entry.getValue().setColumn(y);
                if (turn % 2 == 1)
                    map.getFirstPlayerCellCard().put(entry.getValue().getId(), map.getCells()[x][y]);
                else
                    map.getSecondPlayerCellCard().put(entry.getValue().getId(), map.getCells()[x][y]);
                currentCard = entry.getValue();
                Hand.remove(entry.getValue().getId());
                break;
            }
    }

    public void insertPlayer2Card(String cardName, int x, int y) {
        insertCard(cardName, x, y, secondPlayerHand);
    }

    public void insertPlayer1Card(String cardName, int x, int y) {
        insertCard(cardName, x, y, firstPlayerHand);
    }

    public void endTurn() {
        if (turn % 2 == 1) {
            firstPlayerMana = basicMana;
            updateFirstPlayerHand();
            updateCellCard(map.getFirstPlayerCellCard());
            activeStunBuffs(map.getFirstPlayerCellCard());
        } else {
            basicMana++;
            secondPlayerMana = basicMana;
            updateSecondPlayerHand();
            updateCellCard(map.getSecondPlayerCellCard());
            activeStunBuffs(map.getSecondPlayerCellCard());
        }
        changeTurn();
    }

    public void updateCellCard(HashMap<Integer, Cell> cards) {
        for (java.util.Map.Entry<Integer, Cell> entry : cards.entrySet()) {
            entry.getValue().getCard().setCanMove(true);
            entry.getValue().getCard().setCanAttack(true);
        }
    }

    public void attack(int cardId) {
        currentCard.setCanAttack(false);
        currentCard.setCanMove(false);
        if (turn % 2 == 1) {
            map.getSecondPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAp());

        } else {
            map.getFirstPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAp());
        }
        counterAttack(cardId, currentCard.getId());
    }

    private void counterAttack(int attackerId, int defenderId) {

    }
    //----------------------------------buffs--------------------------------

    private void activeSPower(Card attacker, Card defender) {
        for (Buff buff : attacker.getSpecialPower()) {
            if (buff.getType() != BuffType.HOLY && buff.getType() != BuffType.ATTACK_POWER &&
                    buff.getType() != BuffType.HEALTH_POWER)
                defender.getBuffs().add(buff);
            else attacker.getBuffs().add(buff);
        }
    }

    private void activeHolyBuffs(Card card) {
        card.incrementOfHp(1);
    }

    private void activeStunBuffs(HashMap<Integer, Cell> cards) {
        for (java.util.Map.Entry<Integer, Cell> entry : cards.entrySet())
            for (Buff buff : entry.getValue().getCard().getBuffs())
                if (buff.getType() == BuffType.STUN) {
                    entry.getValue().getCard().setCanMove(false);
                    entry.getValue().getCard().setCanAttack(false);
                }


    }

    private void activeDisarmBuffs(HashMap<Integer, Cell> cards) {
        for (java.util.Map.Entry<Integer, Cell> entry : cards.entrySet())
            for (Buff buff : entry.getValue().getCard().getBuffs())
                if (buff.getType() == BuffType.DISARM)
                    entry.getValue().getCard().setCanCounterAttack(false);
    }

    private void activePoisonBuffs(HashMap<Integer, Cell> cards) {
        for (java.util.Map.Entry<Integer, Cell> entry : cards.entrySet())
            for (Buff buff : entry.getValue().getCard().getBuffs())
                if (buff.getType() == BuffType.POISON)
                    entry.getValue().getCard().decrementOfHp(1);
    }

    private void activePowerBuffs(Card card) {
        for (Buff buff : card.getBuffs())
            if (buff.getType() == BuffType.HEALTH_POWER && !buff.isStarted()) {
                card.incrementOfHp(buff.getBuffPower());
                buff.setStarted(true);

            } else if (buff.getType() == BuffType.ATTACK_POWER && !buff.isStarted()) {
                card.incrementOfAp(buff.getBuffPower());
                buff.setStarted(true);
            }
    }

    private void activeWeaknessBuffs(Card card) {
        for (Buff buff : card.getBuffs())
            if (buff.getType() == BuffType.ATTACK_WEAKNESS && !buff.isStarted()) {
                card.decrementOfAp(buff.getBuffPower());
                buff.setStarted(true);

            } else if (buff.getType() == BuffType.HEALTH_WEAKNESS && !buff.isStarted()) {
                card.decrementOfAp(buff.getBuffPower());
                buff.setStarted(true);
            }
    }


}




