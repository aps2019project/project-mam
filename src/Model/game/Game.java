package Model.game;

import Controller.GameController.ImageController;
import Model.Buffs.Buff;
import Model.deck.Deck;
import Model.shop.Shop;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import Model.enums.ImpactType;
import Model.enums.SPActivationTime;
import Model.item.CollectableItem;
import Model.item.Item;
import Model.user.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.lang.Math.abs;


public class Game {
    private transient static Game game;

    private int id;
    private int baseTurn = 1;
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int FIRST_PLAYER_TURN = 1;
    private static final int SECOND_PLAYER_TURN = 2;
    private int basicMana = 3;
    private int extraPlayer1Mana = 0;
    private int extraPlayer2Mana = 0;
    private Map map = new Map();
    private Deck firstPlayerDeck = new Deck();
    private Deck secondPlayerDeck;

    private int firstCardCounter = 0;
    private int secondCardCounter = 0;

    private User firstUser;
    private User secondUser;

    private ArrayList<Card> firstPlayerGraveYard;
    private ArrayList<Card> secondPlayerGraveYard;

    private HashMap<Integer, Card> firstPlayerHand;
    private HashMap<Integer, Card> secondPlayerHand;

    private Card currentCard;
    private Card nextFirstPlayerCard;
    private Card nextSecondPlayerCard;

    private String mode;
    private String kind;

    private int turn;
    private int flagCount;
    private int havingFlagCount = 0;
    private int firstPlayerMana;
    private int secondPlayerMana;

    private ArrayList<CollectableItem> player1Collectable = new ArrayList<>();
    private ArrayList<CollectableItem> player2Collectable = new ArrayList<>();
    private Item currentItem;

    private boolean isGameEnd = false;
    private int winner;
    private String winnerName;
    private int price = 1000;

    private boolean isMulti = false;
    private boolean isLive = false;
    private boolean isReplay = false;

    public Game(Game lastGame, boolean isLive){
        //liveGame.setLive(true);
        lastGame.setLive(isLive);
        //game = liveGame;

        game = lastGame;
        //isReplay = true;
        /*ImageController.getInstance().initCardImage();
        ImageController.getInstance().initCollectibleImage();*/
    }

    public Game(User firstUser, User secondUser, String mode, String kind, int flagCount) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.mode = mode;
        this.flagCount = flagCount;
        this.kind = kind;
        this.firstPlayerDeck = firstUser.getMainDeck();
        this.secondPlayerDeck = secondUser.getMainDeck();
        game = this;
        ImageController.getInstance().initCardImage();
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        secondPlayerMana = basicMana;
        firstPlayerMana = basicMana - 1;
        firstPlayerHand = new HashMap<>();
        secondPlayerHand = new HashMap<>();
        setPlayersHand();
        setNextFirstPlayerCard();
        setNextSecondPlayerCard();
        startGame();
        ImageController.getInstance().initCollectibleImage();
        this.id = new Random().nextInt(100000);
    }

    public Game(User firstUser, User secondUser, String mode, String kind, int flagCount, int baseTurn) {
        isMulti = true;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.mode = mode;
        this.flagCount = flagCount;
        this.kind = kind;
        this.firstPlayerDeck = firstUser.getMainDeck();
        this.secondPlayerDeck = secondUser.getMainDeck();
        this.baseTurn = baseTurn;
        game = this;
        ImageController.getInstance().initCardImage();
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        if (baseTurn == 1) {
            secondPlayerMana = basicMana;
            firstPlayerMana = basicMana - 1;
        } else {
            secondPlayerMana = basicMana - 1;
            firstPlayerMana = basicMana;
        }
        firstPlayerHand = new HashMap<>();
        secondPlayerHand = new HashMap<>();
        //setId(firstPlayerDeck);
        //setId(secondPlayerDeck);
        setMultiPlayerHand();
        setNextFirstPlayerCard();
        setNextSecondPlayerCard();
        startGame();
        ImageController.getInstance().initCollectibleImage();
        this.id = new Random().nextInt(100000);
    }

    public int getId() {
        return id;
    }

    /*public Game(User firstUser, User secondUser, Map map, String mode, String kind, int flagCount, int baseTurn){
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.mode = mode;
        this.flagCount = flagCount;
        this.kind = kind;
        this.firstPlayerDeck = firstUser.getMainDeck();
        this.secondPlayerDeck = secondUser.getMainDeck();
        this.baseTurn = -1;
        this.map = map;
        game = this;
    }*/

    /*public Game(Game liveGame){
        //liveGame.setBaseTurn(1);
        *//*liveGame.setLive(true);
        game = liveGame;*//*
        //isLive = true;
    }*/

    public void changeBaseTurn(){
        if (baseTurn == 1)
            baseTurn = 0;
        else baseTurn = 1;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getBasicMana() {
        return basicMana;
    }

    public String getMode() {
        return mode;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public int getBaseTurn() {
        return baseTurn;
    }

    public void setBaseTurn(int baseTurn) {
        this.baseTurn = baseTurn;
    }

    public boolean isMulti() {
        return isMulti;
    }

    public boolean isReplay() {
        return isReplay;
    }

    public static Game getInstance() {
        return game;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public ArrayList<Card> getFirstPlayerGraveYard() {
        return firstPlayerGraveYard;
    }

    public Card getNextFirstPlayerCard() {
        return nextFirstPlayerCard;
    }

    public Card getNextSecondPlayerCard() {
        return nextSecondPlayerCard;
    }

    public String getKind() {
        return kind;
    }

    public int getHavingFlagCount() {
        return havingFlagCount;
    }

    public ArrayList<CollectableItem> getPlayer1Collectable() {
        return player1Collectable;
    }

    public ArrayList<CollectableItem> getPlayer2Collectable() {
        return player2Collectable;
    }

    public int getPrice() {
        return price;
    }

    public int getBasicMana1() {
        if (baseTurn == 1)
            return basicMana - 1;
        return basicMana;
    }

    public int getBasicMana2() {
        if (baseTurn == 1)
            return basicMana;
        return basicMana - 1;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public Map getMap() {
        return map;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public int getMana() {
        if (isMyTurn())
            return firstPlayerMana;
        return secondPlayerMana;
    }

    public int getFirstPlayerMana() {
        return firstPlayerMana;
    }

    public int getSecondPlayerMana() {
        return secondPlayerMana;
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

    public int getTurn() {
        return turn;
    }

    public void setExtraPlayer1Mana(int extraPlayer1Mana) {
        this.extraPlayer1Mana = extraPlayer1Mana;
    }

    public int getExtraPlayer2Mana() {
        return extraPlayer2Mana;
    }

    public void setExtraPlayer2Mana(int extraPlayer2Mana) {
        this.extraPlayer2Mana = extraPlayer2Mana;
    }

    public void changeTurn() {
        turn++;
    }

    private void decreaseMana(int amount) {
        if (isMyTurn())
            firstPlayerMana -= amount;
        else
            secondPlayerMana -= amount;
    }

    /*private void setId(Deck deck) {
        int counter = 0;
        if (deck.getCards().get(0).getId() == deck.getCards().get(1).getId())
            for (Card card : deck.getCards()) {
                card.setId(counter++);
            }
    }*/

    public void startGame() {
        setMap();
        if (flagCount == 1)
            map.getCells()[2][4].setFlagCount(1);
        else {
            Random rand = new Random();
            int x, y;
            for (int i = 0; i < flagCount / 2; i++) {
                x = rand.nextInt(5);
                y = rand.nextInt(9);
                map.getCells()[x][y].incrementOfFlag(1);
                map.getCells()[4 - x][8 - y].incrementOfFlag(1);
            }
            if (flagCount % 2 == 1)
                map.getCells()[2][4].incrementOfFlag(1);
        }
//        insertCollectibleToMap();
    }

    private void setMap() {
        map.getCells()[2][0].setCard(firstPlayerDeck.getHero());
        firstPlayerDeck.getHero().setRow(2);
        firstPlayerDeck.getHero().setColumn(0);

        map.getCells()[2][8].setCard(secondPlayerDeck.getHero());
        secondPlayerDeck.getHero().setRow(2);
        secondPlayerDeck.getHero().setColumn(8);

        map.getFirstPlayerCellCard().put(firstPlayerDeck.getHero().getId(), map.getCells()[2][0]);
        map.getSecondPlayerCellCard().put(secondPlayerDeck.getHero().getId(), map.getCells()[2][8]);
    }

    private void insertCollectibleToMap() {
        int x, y;
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            x = rand.nextInt(5);
            y = rand.nextInt(9);
            map.getCells()[x][y].setCollectableItem(Shop.getCollectibles().get(i));
            map.getCells()[4 - x][8 - y].setCollectableItem(Shop.getCollectibles().get(i));
        }
        map.initCollectableItemsList();
    }

    private void updateHavingFlagCount() {
        if (havingFlagCount != 0)
            havingFlagCount++;
        if (mode.equals(SECOND_MODE) && (getPlayer1FlagCount() == 1 ||
                getPlayer2FlagCount() == 1) && havingFlagCount == 0)
            havingFlagCount = 1;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void checkGameResult() {
        if (mode.equals(SECOND_MODE)) {
            if (havingFlagCount == 6) {
                for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                    if (entry.getValue().getFlagCount() == 1) {
                        if (kind != null && kind.equalsIgnoreCase("story"))
                            price = 1000;
                        winnerName = firstUser.getName();
                        firstUser.incrementOfMoney(price);
                        firstUser.incrementOfNumberOfWin();
                        winner = 1;
                        isGameEnd = true;
                    }
                }
                if (!isGameEnd) {
                    if (kind != null && kind.equalsIgnoreCase("story"))
                        price = 1000;
                    winnerName = secondUser.getName();
                    secondUser.incrementOfMoney(price);
                    secondUser.incrementOfNumberOfWin();
                    winner = 2;
                    isGameEnd = true;
                }
            }

        } else if (mode.equals(THIRD_MODE)) {
            if (getPlayer1FlagCount() > flagCount / 2) {
                if (kind != null && kind.equalsIgnoreCase("story"))
                    price = 1500;
                firstUser.incrementOfMoney(price);
                firstUser.incrementOfNumberOfWin();
                isGameEnd = true;
                winner = 1;
            } else if (getPlayer2FlagCount() > flagCount / 2) {
                if (kind != null && kind.equalsIgnoreCase("story"))
                    price = 1500;
                secondUser.incrementOfMoney(price);
                secondUser.incrementOfNumberOfWin();
                isGameEnd = true;
                winner = 2;
            }
        }
        if (winner == 1) {
            /*firstUser.addGameToLastGames(firstUser.getName(), true, 0);
            secondUser.addGameToLastGames(secondUser.getName(), false, 0);*/
        } else if (winner == 2) {
            /*firstUser.addGameToLastGames(secondUser.getName(), false, 0);
            secondUser.addGameToLastGames(firstUser.getName(), true, 0);*/
        }
    }

    public boolean isMyTurn() {
        return getTurn() % 2 == baseTurn;
    }

    public boolean isOppTurn() {
        return !isMyTurn();
    }


    public int getPlayer1FlagCount() {
        int count = 0;
        for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet())
            count += entry.getValue().getFlagCount();
        return count;
    }

    public int getPlayer2FlagCount() {
        int count = 0;
        for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet())
            count += entry.getValue().getFlagCount();
        return count;
    }

    public void selectCard(int cardId) {
        if (isMyTurn())
            currentCard = map.getFirstPlayerCellCard().get(cardId).getCard();
        else
            currentCard = map.getSecondPlayerCellCard().get(cardId).getCard();
    }

    public boolean isCardInPlayerCellCard(int cardId) {
        if (isMyTurn()) {
            return map.getFirstPlayerCellCard().get(cardId) != null;
        } else {
            return map.getSecondPlayerCellCard().get(cardId) != null;
        }
    }

    public boolean isCardInOppPlayerCellCard(int cardId) {
        if (isOppTurn()) {
            return map.getFirstPlayerCellCard().get(cardId) != null;
        } else {
            return map.getSecondPlayerCellCard().get(cardId) != null;
        }
    }

    public void moveCurrentCardTo(int x, int y) {
        currentCard.setCanMove(false);
        if (isMyTurn()) {
            if (map.getCells()[x][y].haveCollectableItem()) {
                player1Collectable.add(map.getCells()[x][y].getCollectableItem());
                map.getCells()[x][y].setCollectableItem(null);
            }
            map.getCells()[currentCard.getRow()][currentCard.getColumn()].setCard(null);
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getFirstPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getFirstPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getFirstPlayerCellCard().replace(currentCard.getId(), map.getCells()[x][y]);
        } else {
            if (map.getCells()[x][y].haveCollectableItem()) {
                player2Collectable.add(map.getCells()[x][y].getCollectableItem());
                map.getCells()[x][y].setCollectableItem(null);
            }
            map.getCells()[currentCard.getRow()][currentCard.getColumn()].setCard(null);
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getSecondPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getSecondPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getSecondPlayerCellCard().replace(currentCard.getId(), map.getCells()[x][y]);
        }

        currentCard.setRow(x);
        currentCard.setColumn(y);
    }

    public boolean cardCanMove(int x, int y) {
        if (map.getManhatanDistance(currentCard.getRow(), currentCard.getColumn(), x, y) > 2) {
            return false;
        }
        if (!map.isCellEmpty(x, y)) {
            return false;
        }
        if (!map.isTargetInMap(x, y)) {
            return false;
        }
        if (!checkOpCardInRout(x, y)) {
            return false;
        }
        return true;
    }

    public boolean checkOpCardInRout(int x, int y) {
        if (abs(currentCard.getColumn() - x) == 2 && abs(currentCard.getRow() - y) == 0) {
            if (!map.isCellEmpty((currentCard.getColumn() + x) / 2, y)) {
                return false;
            }
        } else if (abs(currentCard.getColumn() - x) == 0 && abs(currentCard.getRow() - y) == 2) {
            if (!map.isCellEmpty(x, (currentCard.getRow() + y) / 2)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == -1 && currentCard.getRow() - y == 1) {
            if (!map.isCellEmpty(x, y + 1) && x - 1 >= 0 && !map.isCellEmpty(x - 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == -1 && currentCard.getRow() - y == -1) {
            if (y - 1 >= 0 && !map.isCellEmpty(x, y - 1) && x - 1 >= 0 && !map.isCellEmpty(x - 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == 1 && currentCard.getRow() - y == 1) {
            if (!map.isCellEmpty(x, y + 1) && !map.isCellEmpty(x + 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == 1 && currentCard.getRow() - y == -1) {
            if (y - 1 >= 0 && !map.isCellEmpty(x, y - 1) && !map.isCellEmpty(x + 1, y)) {
                return false;
            }
        }
        return true;
    }

    private void setMultiPlayerHand() {
        int counter = 0;
        while (counter < 5) {
            addCardFromFirstPlayerDeckToHand();
            counter++;
        }
        counter = 0;
        while (counter < 5) {
            addCardFromSecondPlayerDeckToHand();
            counter++;
        }
    }

    private void addCardFromFirstPlayerDeckToHand() {
        if (firstPlayerDeck.getCards().size() != 0) {
            firstPlayerHand.put(firstPlayerDeck.getCards().get(firstCardCounter).getId(), firstPlayerDeck.getCards().get(firstCardCounter));
            firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(firstCardCounter++));
        }
    }

    private void addCardFromSecondPlayerDeckToHand() {
        if (secondPlayerDeck.getCards().size() != 0) {
            secondPlayerHand.put(secondPlayerDeck.getCards().get(secondCardCounter).getId(), secondPlayerDeck.getCards().get(secondCardCounter));
            secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(secondCardCounter++));
        }
    }

    private void setPlayersHand() {
        Random random = new Random();
        int rand;
        do {
            rand = random.nextInt(firstPlayerDeck.getCards().size());
            addRandomCardFromFirstPlayerDeckToHand(rand);
        } while (firstPlayerHand.size() <= 4);
        do {
            rand = random.nextInt(secondPlayerDeck.getCards().size());
            addRandomCardFromSecondPlayerDeckToHand(rand);
        } while (secondPlayerHand.size() <= 4);
    }

    private void addRandomCardFromFirstPlayerDeckToHand(int rand) {
        if (firstPlayerDeck.getCards().size() != 0) {
            firstPlayerHand.put(firstPlayerDeck.getCards().get(rand).getId(), firstPlayerDeck.getCards().get(rand));
            firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(rand));
        }
    }

    private void addRandomCardFromSecondPlayerDeckToHand(int rand) {
        if (secondPlayerDeck.getCards().size() != 0) {
            secondPlayerHand.put(secondPlayerDeck.getCards().get(rand).getId(), secondPlayerDeck.getCards().get(rand));
            secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(rand));
        }
    }

    private void addRandomCardFromFirstPlayerDeckToNextCard(int rand) {
        nextFirstPlayerCard = firstPlayerDeck.getCards().get(rand);
        firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(rand));
    }

    private void addRandomCardFromSecondPlayerDeckToNextCard(int rand) {
        nextSecondPlayerCard = secondPlayerDeck.getCards().get(rand);
        secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(rand));
    }

    private void addCardFromFirstPlayerDeckToNextCard() {
        nextFirstPlayerCard = firstPlayerDeck.getCards().get(0);
        firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(0));
    }

    private void addCardFromSecondPlayerDeckToNextCard() {
        nextSecondPlayerCard = secondPlayerDeck.getCards().get(0);
        secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(0));
    }

    private void updateFirstPlayerHand() {
        if (nextFirstPlayerCard != null && firstPlayerHand.size() < 5) {
            firstPlayerHand.put(nextFirstPlayerCard.getId(), nextFirstPlayerCard);
            if (firstPlayerDeck.getCards().size() != 0) {
                if (isMulti()) {
                    addCardFromFirstPlayerDeckToNextCard();
                } else {
                    Random random = new Random();
                    addRandomCardFromFirstPlayerDeckToNextCard(random.
                            nextInt(firstPlayerDeck.getCards().size()));
                }
            } else nextFirstPlayerCard = null;
        }
    }

    private void updateSecondPlayerHand() {
        if (nextSecondPlayerCard != null && secondPlayerHand.size() < 5) {
            secondPlayerHand.put(nextSecondPlayerCard.getId(), nextSecondPlayerCard);
            if (secondPlayerDeck.getCards().size() != 0) {
                if (isMulti()) {
                    addCardFromSecondPlayerDeckToNextCard();
                } else {
                    Random rand = new Random();
                    addRandomCardFromSecondPlayerDeckToNextCard(rand.
                            nextInt(secondPlayerDeck.getCards().size()));
                }
            } else nextSecondPlayerCard = null;
        }
    }

    private void setNextFirstPlayerCard() {
        if (firstPlayerHand.size() <= 5) {
            if (firstPlayerDeck.getCards().size() != 0) {
                if (isMulti()) {
                    nextFirstPlayerCard = firstPlayerDeck.getCards().get(0);
                } else {
                    Random random = new Random();
                    nextFirstPlayerCard = firstPlayerDeck.getCards().
                            get(random.nextInt(firstPlayerDeck.getCards().size()));
                }
            } else
                nextFirstPlayerCard = null;
        }
    }

    private void setNextSecondPlayerCard() {
        if (secondPlayerHand.size() <= 5) {
            if (secondPlayerDeck.getCards().size() != 0) {
                if (isMulti()) {
                    nextSecondPlayerCard = secondPlayerDeck.getCards().get(0);
                } else {
                    Random random = new Random();
                    int rand = random.nextInt(secondPlayerDeck.getCards().size());
                    nextSecondPlayerCard = secondPlayerDeck.getCards().get(rand);
                }
            } else
                nextSecondPlayerCard = null;
        }
    }

    public boolean isCardInPlayerHand(String cardName) {
        if (isMyTurn()) {
            for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName))
                    return true;
            }
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName))
                    return true;
            }
        }
        return false;
    }

    public String getGameInfo() {
        StringBuilder info = new StringBuilder();
        info.append("first player mana : ").append(firstPlayerMana).append("\nsecond player mana : ").
                append(secondPlayerMana).append("\n");
        switch (mode) {
            case "1":
                int hero1 = 0;
                int hero2 = 0;
                for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                    if (entry.getValue().getCard().getCardType().equalsIgnoreCase("hero")) {
                        hero1 = entry.getValue().getCard().getHP();
                        break;
                    }
                }
                for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                    if (entry.getValue().getCard().getCardType().equalsIgnoreCase("hero")) {
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

    private void insertCard(String cardName, int x, int y, HashMap<Integer, Card> Hand) {
        for (java.util.Map.Entry<Integer, Card> entry : Hand.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(cardName)) {
                decreaseMana(entry.getValue().getMP());
                if (!(entry.getValue() instanceof Spell)) {
                    insertMinionsOrHero(entry.getValue(), x, y);
                    entry.getValue().setCanMove(false);
                    entry.getValue().setCanAttack(false);
                    addCollectible(map.getCells()[x][y]);

                } else {
                    spellBuffCreator(entry.getValue(), getMap().getCells()[x][y]);
                }
                currentCard = entry.getValue();
                Hand.remove(entry.getValue().getId());
                break;
            }
        }
    }

    private void insertMinionsOrHero(Card card, int x, int y) {
        map.getCells()[x][y].setCard(card);
        card.setRow(x);
        card.setColumn(y);
        if (isMyTurn()) {
            map.getFirstPlayerCellCard().put(card.getId(), map.getCells()[x][y]);
            if (firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_SPAWN)
                buffCreator(card, map.getCells()[x][y]);
        } else if (isOppTurn()) {
            map.getSecondPlayerCellCard().put(card.getId(), map.getCells()[x][y]);
            if (secondPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_SPAWN)
                buffCreator(card, map.getCells()[x][y]);
        }
        if (map.getCells()[x][y].getFlagCount() != 0) {
            //TODO
        }
    }

    public void insertPlayerCard(String cardName, int x, int y) {
        if (isMyTurn()) {
            insertCard(cardName, x, y, firstPlayerHand);
        } else {
            insertCard(cardName, x, y, secondPlayerHand);
        }
    }

    public void endTurn() {
        if (getTurn() % 2 == 0) {
            if (basicMana < 9)
                basicMana++;
            updateCoolDown(getHero(map.getFirstPlayerCellCard()));
            updateCoolDown(getHero(map.getSecondPlayerCellCard()));
            if (baseTurn == 1)
                firstPlayerMana = basicMana + extraPlayer1Mana - 1;
            else firstPlayerMana = basicMana + extraPlayer1Mana;
            if (basicMana == 9)
                firstPlayerMana = 9;
            updateFirstPlayerHand();
            updateCellCard(map.getFirstPlayerCellCard());
            Buff.updateBuffs();
        } else {
            if (baseTurn == 1)
                secondPlayerMana = basicMana + extraPlayer2Mana;
            else secondPlayerMana = basicMana + extraPlayer2Mana - 1;
            if (basicMana == 9)
                secondPlayerMana = 9;
            updateSecondPlayerHand();
            updateCellCard(map.getSecondPlayerCellCard());
            Buff.refreshIsUsed();
        }
        updateHavingFlagCount();
        changeTurn();
        checkGameResult();
        Buff.refreshBuffs();
    }

    private void updateCellCard(HashMap<Integer, Cell> cards) {
        for (java.util.Map.Entry<Integer, Cell> entry : cards.entrySet()) {
            entry.getValue().getCard().setCanMove(true);
            entry.getValue().getCard().setCanAttack(true);
        }
    }

    public void attack(int cardId) {
        currentCard.setCanAttack(false);
        //currentCard.setCanMove(false);
        if (isMyTurn()) {
            if (firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_ATTACK) {
                buffCreator(currentCard, map.getSecondPlayerCellCard().get(cardId));
            }
            map.getSecondPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAP());
            if ((currentCard instanceof Minion && currentCard.getSPActivationTime() == SPActivationTime.ON_ATTACK)) {
                buffCreator(currentCard, map.getSecondPlayerCellCard().get(cardId));
            }

        } else {
            if (secondPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_ATTACK) {
                buffCreator(currentCard, map.getFirstPlayerCellCard().get(cardId));
            }
            map.getFirstPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAP());
            if (currentCard.getSPActivationTime() == SPActivationTime.ON_ATTACK)
                for (Buff buff : currentCard.getSpecialPower()) {
                    Buff newBuff = buff.copy();
                    newBuff.setCard(map.getFirstPlayerCellCard().get(cardId).getCard());
                    Buff.addBuff(newBuff);
                }
        }
        Buff.refreshBuffs();
        if (canCounterAttack(currentCard.getId(), cardId))
            counterAttack(cardId, currentCard.getId());
        checkHpState(map.getFirstPlayerCellCard(), firstPlayerGraveYard, 1);
        checkHpState(map.getSecondPlayerCellCard(), secondPlayerGraveYard, 2);
        Buff.refreshBuffs();
    }

    private void counterAttack(int attackerId, int defenderId) {
        if (isMyTurn()) {
            map.getFirstPlayerCellCard().get(defenderId).getCard().
                    decrementOfHp(map.getSecondPlayerCellCard().get(attackerId).getCard().getAP());

        } else {
            map.getSecondPlayerCellCard().get(defenderId).getCard().
                    decrementOfHp(map.getFirstPlayerCellCard().get(attackerId).getCard().getAP());
        }
    }

    public boolean isOppAvailableForAttack(int targetId, int attackerId, int turn) {
        int distance;
        Card attacker;
        try {
            if (isMyTurn()) {
                attacker = map.getFirstPlayerCellCard().get(attackerId).getCard();
                Cell target = map.getSecondPlayerCellCard().get(targetId);
                distance = map.getManhatanDistance(attacker.getRow(), attacker.getColumn(), target.getRow(), target.getColumn());
            } else {
                attacker = map.getSecondPlayerCellCard().get(attackerId).getCard();
                Cell target = map.getFirstPlayerCellCard().get(targetId);
                distance = map.getManhatanDistance(attacker.getRow(), attacker.getColumn(), target.getRow(),
                        target.getColumn());
            }
            if (attacker.getCardClass() == ImpactType.MELEE && distance == 1)
                return true;
            if (attacker.getCardClass() == ImpactType.RANGED && distance <= attacker.getTargetCommunity())
                return true;
            if (attacker.getCardClass() == ImpactType.HYBRID)
                return true;
            if (attacker.getCardClass() == ImpactType._NULL)
                return false;
        } catch (NullPointerException e) {
            return false;
        }

        return false;

    }

    private boolean isCardIdValidForAttack(int cardId) {
        if (isMyTurn() && map.getSecondPlayerCellCard().get(cardId) == null)
            return false;
        return isMyTurn() || map.getFirstPlayerCellCard().get(cardId) != null;
    }

    public boolean canCounterAttack(int targetId, int CountererId) {
        boolean isDisarm;
        if (isMyTurn())
            isDisarm = !map.getSecondPlayerCellCard().get(CountererId).getCard().canCounterAttack();
        else
            isDisarm = !map.getFirstPlayerCellCard().get(CountererId).getCard().canCounterAttack();
        return isOppAvailableForAttack(targetId, CountererId, getTurn() + 1) && !isDisarm;
    }

    private int getRewardPrice() {
        if (mode.equals("1"))
            return 500;
        if (mode.equals("2"))
            return 1000;
        else
            return 1500;
    }

    private void checkHpState(HashMap<Integer, Cell> cells, ArrayList<Card> graveYard, int player) {
        for (java.util.Map.Entry<Integer, Cell> entry : cells.entrySet()) {
            if (entry.getValue().getCard().getHP() <= 0) {
                if (player == 1 && firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_DEATH) {
                    buffCreator(entry.getValue().getCard(), entry.getValue());
                } else if (player == 2 && secondPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_DEATH)
                    buffCreator(entry.getValue().getCard(), entry.getValue());
                Buff.refreshBuffs();
                if (entry.getValue().getCard() instanceof Hero) {
                    price = getRewardPrice();
                    if (player == 1) {
                        winnerName = secondUser.getName();
                        secondUser.setMoney(price);
                        secondUser.setNumberOfWin(secondUser.getNumberOfWin() + 1);
                    } else {
                        winnerName = firstUser.getName();
                        firstUser.setMoney(price);
                        firstUser.setNumberOfWin(firstUser.getNumberOfWin() + 1);
                    }
                    winner = player;
                    isGameEnd = true;
                    break;
                }
                if (entry.getValue().getFlagCount() != 0)
                    havingFlagCount = 0;
                graveYard.add(entry.getValue().getCard());
                map.getCells()[entry.getValue().getRow()][entry.getValue().getColumn()].setCard(null);
                cells.remove(entry.getKey());
            }
        }
    }

    private void spellBuffCreator(Card attacker, Cell defender) {
        for (Buff buff : attacker.getSpecialPower()) {
            for (Cell cell : buff.getSpellTarget(defender, getMyTeam(), getOppTeam(), getMap())) {
                Buff newBuff = buff.copy();
                if (cell.getCard() != null)
                    newBuff.setCard(cell.getCard());
                /*newBuff.setCell(cell);
                newBuff.setStarted(true);
                Buff.addBuff(newBuff);*/
            }
        }
    }

    private void buffCreator(Card card, Cell cell) {
        for (Buff buff : card.getSpecialPower()) {
            for (Card cards : buff.getMinionsSPTarget(card, cell.getCard(), getMyTeam(), getOppTeam(), map)) {
                /*Buff newBuff = buff.copy();
                newBuff.setCard(cards);
                newBuff.setStarted(true);
                Buff.addBuff(newBuff);*/
            }
        }
    }

    private HashMap<Integer, Cell> getMyTeam() {
        if (isMyTurn())
            return map.getFirstPlayerCellCard();
        return map.getSecondPlayerCellCard();
    }

    private HashMap<Integer, Cell> getOppTeam() {
        if (isMyTurn())
            return map.getSecondPlayerCellCard();
        return map.getFirstPlayerCellCard();
    }

    public boolean haveEnoughMana(String cardName) {
        if (isMyTurn()) {
            for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName)) {
                    if (entry.getValue().getMP() <= firstPlayerMana) {
                        return true;
                    }
                }
            }
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName)) {
                    if (entry.getValue().getMP() <= secondPlayerMana) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCellValidForInsertMinion(int x, int y) {
        if (map.isTargetInMap(x, y) && map.isCellEmpty(x, y) && isInsiderForceAbutment(x, y)) {
            return true;
        }
        return false;
    }

    public boolean isCellValidForInsertSpell(Card card, int x, int y) {
        for (Buff buff : card.getSpecialPower()) {
            for (Cell cell : buff.getSpellTarget(map.getCells()[x][y], getMyTeam(), getOppTeam(), getMap())) {
                if (cell.getRow() == x && cell.getColumn() == y)
                    return true;
            }
            if (buff.getKind().equalsIgnoreCase("cellEffect"))
                return true;
        }
        return false;
    }

    public Card getCardInHand(String cardName) {
        if (isMyTurn()) {
            for (java.util.Map.Entry<Integer, Card> entry : getFirstPlayerHand().entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName))
                    return entry.getValue();
            }
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : getSecondPlayerHand().entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName))
                    return entry.getValue();
            }
        }
        return null;
    }

    public boolean isInsiderForceAbutment(int x, int y) {
        if (isMyTurn()) {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                if ((abs(entry.getValue().getRow() - x) == 1 && abs(entry.getValue().getColumn() - y) == 0)
                        || (abs(entry.getValue().getRow() - x) == 0 && abs(entry.getValue().getColumn() - y) == 1)) {
                    return true;
                }
            }
        } else {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                if ((abs(entry.getValue().getRow() - x) == 1 && abs(entry.getValue().getColumn() - y) == 0)
                        || (abs(entry.getValue().getRow() - x) == 0 && abs(entry.getValue().getColumn() - y) == 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateCoolDown(Card hero) {
        if (hero.getCooldown() != 0)
            hero.decrementOfCoolDown(1);
    }

    public Card getHero(HashMap<Integer, Cell> cell) {
        for (java.util.Map.Entry<Integer, Cell> entry : cell.entrySet()) {
            if (entry.getValue().getCard() != null)
                if (entry.getValue().getCard().getCardType().equalsIgnoreCase("hero"))
                    return entry.getValue().getCard();
        }
        return null;
    }

    private void addCollectible(Cell cell) {
        try {
            if (isMyTurn())
                player1Collectable.add(cell.getCollectableItem());
            else
                player2Collectable.add(cell.getCollectableItem());
        } catch (NullPointerException e) {
        }
    }

    //-------------------------grave Yard ----------------------

    public String getInfoInGraveYard(int cardId) {
        if (isMyTurn()) {
            for (Card card : firstPlayerGraveYard) {
                if (card.getId() == cardId)
                    return card.getCardInfoInGame();
            }
        } else
            for (Card card : secondPlayerGraveYard) {
                if (card.getId() == cardId)
                    return card.getCardInfoInGame();
            }
        return " ";
    }

    public String showInGraveYard() {
        StringBuilder info = new StringBuilder();
        if (isMyTurn())
            for (Card card : firstPlayerGraveYard) {
                info.append(card.getCardInfoInGame()).append("\n");
            }
        else
            for (Card card : secondPlayerGraveYard) {
                info.append(card.getCardInfoInGame()).append("\n");
            }
        return info.toString();
    }

    public String getItemInfo() {
        return currentItem.getInfo();
    }

    public void selectCollectableItem(int id) {
        if (isMyTurn())
            for (CollectableItem item : player1Collectable) {
                if (item.getId() == id)
                    currentItem = item;
            }
        else
            for (CollectableItem item : player2Collectable) {
                if (item.getId() == id)
                    currentItem = item;
            }
    }

    public String showCollectables() {
        StringBuilder info = new StringBuilder();
        if (isMyTurn()) {
            for (CollectableItem item : player1Collectable) {
                info.append(item.getInfo()).append("\n");
            }
        } else
            for (CollectableItem item : player2Collectable) {
                info.append(item.getInfo()).append("\n");
            }
        return info.toString();
    }

    public void useCollectible() {
        ArrayList<Cell> cells;
        for (Buff buff : currentItem.getBuffs()) {
            if (isMyTurn())
                cells = buff.getSpecialPowerTargetCells(null, null, map.getFirstPlayerCellCard()
                        , map.getSecondPlayerCellCard(), map);
            else
                cells = buff.getSpecialPowerTargetCells(null, null, map.getSecondPlayerCellCard()
                        , map.getFirstPlayerCellCard(), map);
            for (Cell cell : cells) {
                Buff newBuff = buff.copy();
                newBuff.setCard(cell.getCard());
                Buff.addBuff(newBuff);
            }
        }
    }

    public Card getCard(boolean isMyTurn, int id) {
        if (isMyTurn) {
            return getMap().getFirstPlayerCellCard().get(id).getCard();
        } else return getMap().getSecondPlayerCellCard().get(id).getCard();
    }


}













