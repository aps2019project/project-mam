package Model;

import Model.Buffs.Buff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.lang.Math.abs;


public class Game {
    private static final String SINGLE_PLAYER = "single player";
    private static final String MULTI_PLAYER = "multi player";
    private static final String FIRST_MODE = "1";
    private static final String SECOND_MODE = "2";
    private static final String THIRD_MODE = "3";
    private static final int BASIC_FLAG_COUNT = 7;
    private static final int FIRST_PLAYER_TURN = 1;
    private static final int SECOND_PLAYER_TURN = 2;
    private int basicMana = 2;
    private int extraPlayer1Mana = 0;
    private int extraPlayer2Mana = 0;
    private Map map = new Map();
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
    private int havingFlagCount = 0;
    private int currentTurnMana;
    private int firstPlayerMana;
    private int secondPlayerMana;

    private ArrayList<CollectableItem> player1Collectable = new ArrayList<>();
    private ArrayList<CollectableItem> player2Collectable = new ArrayList<>();
    private Item currentItem;

    private boolean isGameEnd = false;
    private int winner;

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
        this.firstPlayerDeck = firstUser.getMainDeck();
        this.secondPlayerDeck = secondUser.getMainDeck();
        firstPlayerGraveYard = new ArrayList<>();
        secondPlayerGraveYard = new ArrayList<>();
        turn = FIRST_PLAYER_TURN;
        currentTurnMana = basicMana;
        firstPlayerMana = basicMana;
        secondPlayerMana = basicMana;
        firstPlayerHand = new HashMap<>();
        secondPlayerHand = new HashMap<>();
        setPlayersHand();
        setNextfirstPlayerCard();
        setNextSecondPlayerCard();
        startGame();
    }

    public Map getMap() {
        return map;
    }

    public Card getCurrentCard() {
        return currentCard;
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

    public int getBasicMana() {
        return basicMana;
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

    public boolean isGameEnd() {
        return isGameEnd;
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

    public int getExtraPlayer1Mana() {
        return extraPlayer1Mana;
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

    public void startGame() {

        map.getCells()[2][0].setCard(firstPlayerDeck.getHero());
        firstPlayerDeck.getHero().setRow(2);
        firstPlayerDeck.getHero().setColumn(0);
        map.getCells()[2][8].setCard(secondPlayerDeck.getHero());
        secondPlayerDeck.getHero().setRow(2);
        secondPlayerDeck.getHero().setColumn(8);

        map.getFirstPlayerCellCard().put(firstPlayerDeck.getHero().getId(), map.getCells()[2][0]);
        map.getSecondPlayerCellCard().put(secondPlayerDeck.getHero().getId(), map.getCells()[2][8]);
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

    }

    public void updateHavingFlagCount() {
        if (havingFlagCount != 0)
            havingFlagCount++;
        if (mode.equals(SECOND_MODE) && (getPlayer1FlagCount() == 1 ||
                getPlayer2FlagCount() == 1) && havingFlagCount == 0)
            havingFlagCount = 1;
    }

    public int getWinner() {
        return winner;
    }

    public void checkGameResult() {
        if (mode.equals(SECOND_MODE)) {
            if (havingFlagCount == 6) {
                for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                    if (entry.getValue().getFlagCount() == 1) {
                        winner = 1;
                        isGameEnd = true;
                    }
                }
                if (!isGameEnd) {
                    winner = 2;
                    isGameEnd = true;
                }
            }

        } else if (mode.equals(THIRD_MODE)) {
            if (getPlayer1FlagCount() > flagCount / 2) {
                isGameEnd = true;
                winner = 1;
            } else if (getPlayer2FlagCount() > flagCount / 2) {
                isGameEnd = true;
                winner = 2;
            }
        }
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
        if (getTurn() % 2 == FIRST_PLAYER_TURN)
            currentCard = map.getFirstPlayerCellCard().get(cardId).getCard();
        else
            currentCard = map.getSecondPlayerCellCard().get(cardId).getCard();
    }

    public boolean isCardInPlayerCellCard(int cardId) {
        if (getTurn() % 2 == 1) {
            return map.getFirstPlayerCellCard().get(cardId) != null;
        } else {
            return map.getSecondPlayerCellCard().get(cardId) != null;
        }
    }

    public boolean isCardInOppPlayerCellCard(int cardId) {
        if (getTurn() % 2 == 0) {
            return map.getFirstPlayerCellCard().get(cardId) != null;
        } else {
            return map.getSecondPlayerCellCard().get(cardId) != null;
        }
    }

    public void moveCurrentCardTo(int x, int y) {
        currentCard.setCanMove(false);
        currentCard.setRow(x);
        currentCard.setColumn(y);
        if (turn % 2 == 1) {
            if (map.getCells()[x][y].haveCollectableItem()) {
                player1Collectable.add(map.getCells()[x][y].getCollectableItem());
                map.getCells()[x][y].setCollectableItem(null);
            }
            map.getCells()[currentCard.getRow()][currentCard.getColumn()].setCard(null);
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getFirstPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getFirstPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getFirstPlayerCellCard().get(currentCard.getId()).setRow(x);
            map.getFirstPlayerCellCard().get(currentCard.getId()).setColumn(y);
        } else {
            if (map.getCells()[x][y].haveCollectableItem()) {
                player2Collectable.add(map.getCells()[x][y].getCollectableItem());
                map.getCells()[x][y].setCollectableItem(null);
            }
            map.getCells()[currentCard.getRow()][currentCard.getColumn()].setCard(null);
            map.getCells()[x][y].setCard(currentCard);
            map.getCells()[x][y].incrementOfFlag(map.getSecondPlayerCellCard().get(currentCard.getId()).getFlagCount());
            map.getSecondPlayerCellCard().get(currentCard.getId()).setFlagCount(0);
            map.getSecondPlayerCellCard().get(currentCard.getId()).setRow(x);
            map.getSecondPlayerCellCard().get(currentCard.getId()).setColumn(y);
        }
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
            if (!map.isCellEmpty(x, y + 1) && !map.isCellEmpty(x - 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == -1 && currentCard.getRow() - y == -1) {
            if (!map.isCellEmpty(x, y - 1) && !map.isCellEmpty(x - 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == 1 && currentCard.getRow() - y == 1) {
            if (!map.isCellEmpty(x, y + 1) && !map.isCellEmpty(x + 1, y)) {
                return false;
            }
        } else if (currentCard.getColumn() - x == 1 && currentCard.getRow() - y == -1) {
            if (!map.isCellEmpty(x, y - 1) && !map.isCellEmpty(x + 1, y)) {
                return false;
            }
        }
        return true;
    }

    public void setPlayersHand() {
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

    public void addRandomCardFromFirstPlayerDeckToHand(int rand) {
        if (firstPlayerDeck.getCards().size() != 0) {
            firstPlayerHand.put(firstPlayerDeck.getCards().get(rand).getId(), firstPlayerDeck.getCards().get(rand));
            firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(rand));
        }
    }

    public void addRandomCardFromSecondPlayerDeckToHand(int rand) {
        if (secondPlayerDeck.getCards().size() != 0) {
            secondPlayerHand.put(secondPlayerDeck.getCards().get(rand).getId(), secondPlayerDeck.getCards().get(rand));
            secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(rand));
        }
    }

    public void addRandomCardFromFirstPlayerDeckToNextCard(int rand) {
        nextfirstPlayerCard = firstPlayerDeck.getCards().get(rand);
        firstPlayerDeck.removeCard(firstPlayerDeck.getCards().get(rand));
    }

    public void addRandomCardFromSecondPlayerDeckToNextCard(int rand) {
        nextSecondPlayerCard = secondPlayerDeck.getCards().get(rand);
        secondPlayerDeck.removeCard(secondPlayerDeck.getCards().get(rand));
    }

    public void updateFirstPlayerHand() {
        if (nextfirstPlayerCard != null && firstPlayerHand.size() < 5) {
            firstPlayerHand.put(nextfirstPlayerCard.getId(), nextfirstPlayerCard);
            if (firstPlayerDeck.getCards().size() != 0) {
                Random random = new Random();
                addRandomCardFromFirstPlayerDeckToNextCard(random.
                        nextInt(firstPlayerDeck.getCards().size()));
            } else nextfirstPlayerCard = null;
        }
    }

    public void updateSecondPlayerHand() {
        if (nextSecondPlayerCard != null && secondPlayerHand.size() < 5) {
            secondPlayerHand.put(nextSecondPlayerCard.getId(), nextSecondPlayerCard);
            if (secondPlayerDeck.getCards().size() != 0) {
                Random rand = new Random();
                addRandomCardFromSecondPlayerDeckToNextCard(rand.
                        nextInt(secondPlayerDeck.getCards().size()));
            }
        } else nextSecondPlayerCard = null;
    }

    public void setNextfirstPlayerCard() {
        if (firstPlayerHand.size() <= 5) {
            if (firstPlayerDeck.getCards().size() != 0) {
                Random random = new Random();
                nextfirstPlayerCard = firstPlayerDeck.getCards().
                        get(random.nextInt(firstPlayerDeck.getCards().size()));
            } else
                nextfirstPlayerCard = null;
        }
    }

    public void setNextSecondPlayerCard() {
        if (secondPlayerHand.size() <= 5) {
            if (secondPlayerDeck.getCards().size() != 0) {
                Random random = new Random();
                int rand = random.nextInt(secondPlayerDeck.getCards().size());
                nextSecondPlayerCard = secondPlayerDeck.getCards().get(rand);
            } else
                nextSecondPlayerCard = null;
        }
    }

    public boolean isCardInPlayerHand(String cardName) {
        if (getTurn() % 2 == 1) {
            for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName))
                    return true;
            }
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                if (entry.getValue().getName().equals(cardName))
                    return true;
            }
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

    public String showMyMinions() {
        StringBuilder info = new StringBuilder();
        if (getTurn() % 2 == 1) {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        } else {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        return info.toString();
    }

    public String showOpMinions() {
        StringBuilder info = new StringBuilder();
        if (getTurn() % 2 == 0) {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getFirstPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        } else {
            for (java.util.Map.Entry<Integer, Cell> entry : map.getSecondPlayerCellCard().entrySet()) {
                info.append(entry.getValue().getCard().getImportantInfo()).append("\n");
            }
        }
        return info.toString();
    }

    public String showCardInfo(int cardId) {
        if (getTurn() % 2 == 1) {
            for (Card card : firstPlayerDeck.getCards()) {
                if (card.getId() == cardId) {
                    return card.getCardInfoInGame();
                }
            }
        } else {
            for (Card card : secondPlayerDeck.getCards()) {
                if (card.getId() == cardId) {
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
                info.append(entry.getValue().getInfo()).append("\n");
            }
            info.append("next card: ").append(nextfirstPlayerCard.getInfo());
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                info.append(entry.getValue().getInfo()).append("\n");
            }
            info.append("next card:").append(nextSecondPlayerCard.getInfo());
        }
        return info.toString();
    }

    private void insertCard(String cardName, int x, int y, HashMap<Integer, Card> Hand) {
        for (java.util.Map.Entry<Integer, Card> entry : Hand.entrySet())
            if (entry.getValue().getName().equalsIgnoreCase(cardName)) {
                if (getTurn() % 2 == 1 && firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_INSERT)
                    for (Buff buff : firstPlayerDeck.getItem().getBuffs()) {
                        buffAlocator(map.getFirstPlayerCellCard().get(entry.getKey()), buff);
                    }
                else if (getTurn() % 2== 0 && secondPlayerDeck.getItem().
                        getSpActivationTime() == SPActivationTime.ON_INSERT)
                    for (Buff buff : secondPlayerDeck.getItem().getBuffs()) {
                        buffAlocator(map.getSecondPlayerCellCard().get(entry.getKey()), buff);
                    }
                map.getCells()[x][y].setCard(entry.getValue());
                entry.getValue().setRow(x);
                entry.getValue().setColumn(y);
                if (turn % 2 == 1) {
                    if (map.getCells()[x][y].haveCollectableItem())
                        player1Collectable.add(map.getCells()[x][y].getCollectableItem());
                    map.getFirstPlayerCellCard().put(entry.getValue().getId(), map.getCells()[x][y]);
                    firstPlayerMana -= entry.getValue().getMP();
                } else {
                    if (map.getCells()[x][y].haveCollectableItem())
                        player2Collectable.add(map.getCells()[x][y].getCollectableItem());
                    map.getSecondPlayerCellCard().put(entry.getValue().getId(), map.getCells()[x][y]);
                    secondPlayerMana -= entry.getValue().getMP();
                }
                if (map.getCells()[x][y].getFlagCount() != 0) {

                }
                currentCard = entry.getValue();
                Hand.remove(entry.getValue().getId());
                break;
            }
    }

    public void insertPlayerCard(String cardName, int x, int y) {
        if (getTurn() % 2 == 1) {
            insertCard(cardName, x, y, firstPlayerHand);
        } else {
            insertCard(cardName, x, y, secondPlayerHand);
        }
    }

    public void endTurn() {
        if (turn % 2 == 0) {
            basicMana++;
            firstPlayerMana = basicMana + extraPlayer1Mana;
            updateFirstPlayerHand();
            updateCellCard(map.getFirstPlayerCellCard());
        } else {
            secondPlayerMana = basicMana + extraPlayer2Mana;
            updateSecondPlayerHand();
            updateCellCard(map.getSecondPlayerCellCard());
        }
        updateHavingFlagCount();
        changeTurn();
        checkGameResult();
        activePassiveBuffs();
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
            if (firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_ATTACK){
                for (Buff buff : firstPlayerDeck.getItem().getBuffs()) {
                    buffAlocator(map.getFirstPlayerCellCard().get(currentCard.getId()), buff);
                }
            }
            map.getSecondPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAP());
            if ((currentCard instanceof Minion && currentCard.getSPActivationTime() == SPActivationTime.ON_ATTACK)
                    || currentCard.getName().equals("zahak")) {
                for (Buff buff : currentCard.getSpecialPower()) {
                    Buff newBuff = buff.copy();
                    newBuff.setCard(map.getSecondPlayerCellCard().get(cardId).getCard());
                    Buff.addBuff(newBuff);
                }
                if (currentCard.getName().equals("zahak")) {
                    if (getTurn() % 2 == 1)
                        firstPlayerMana -= currentCard.getMP();
                    else
                        secondPlayerMana -= currentCard.getMP();
                }
            }

        } else {
            if (secondPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_ATTACK){
                for (Buff buff : secondPlayerDeck.getItem().getBuffs()) {
                    buffAlocator(map.getSecondPlayerCellCard().get(currentCard.getId()), buff);
                }
            }
            map.getFirstPlayerCellCard().get(cardId).getCard().decrementOfHp(currentCard.getAP());
            if (currentCard.getSPActivationTime() == SPActivationTime.ON_ATTACK)
                for (Buff buff : currentCard.getSpecialPower()) {
                    Buff newBuff = buff.copy();
                    newBuff.setCard(map.getFirstPlayerCellCard().get(cardId).getCard());
                    Buff.addBuff(newBuff);
                }
        }
        Buff.updateBuffs();
        if (canCounterAttack(currentCard.getId(), cardId))
            counterAttack(cardId, currentCard.getId());
        checkHpState(map.getFirstPlayerCellCard(), firstPlayerGraveYard, 1);
        checkHpState(map.getSecondPlayerCellCard(), secondPlayerGraveYard, 2);
        Buff.updateBuffs();
    }

    public boolean isOppAvailableForAttack(int targetId, int attackerId) {
        int distance;
        Card attacker;
        if (getTurn() % 2 == 1) {
            attacker = map.getFirstPlayerCellCard().get(attackerId).getCard();
            Cell target = map.getSecondPlayerCellCard().get(targetId);
            distance = map.getManhatanDistance(attacker.getRow(), attacker.getColumn(), target.getRow(),
                    target.getColumn());
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
        return false;

    }

    public boolean isCardIdValidForAttack(int cardId) {
        if (getTurn() % 2 == 1 && map.getSecondPlayerCellCard().get(cardId) == null)
            return false;
        return getTurn() % 2 != 0 || map.getFirstPlayerCellCard().get(cardId) != null;
    }

    public boolean canCounterAttack(int targetId, int CountererId) {
        return isOppAvailableForAttack(targetId, CountererId);
    }

    private void counterAttack(int attackerId, int defenderId) {
        if (turn % 2 == 1) {
            map.getFirstPlayerCellCard().get(defenderId).getCard().
                    decrementOfHp(map.getSecondPlayerCellCard().get(attackerId).getCard().getAP());

        } else {
            map.getSecondPlayerCellCard().get(defenderId).getCard().
                    decrementOfHp(map.getFirstPlayerCellCard().get(attackerId).getCard().getAP());
        }
    }

    public boolean canComboAttack(Card defender, ArrayList<Card> attackers) {
        if (!isCardIdValidForAttack(defender.getId()))
            return false;
        for (Card attacker : attackers) {
            if (!attacker.isCanAttack())
                return false;
            if (!isOppAvailableForAttack(defender.getId(), attacker.getId()))
                return false;
        }
        return true;
    }

    public void comboAttack(int defenderId, int... attackerId) {
        ArrayList<Card> attackers = new ArrayList<>();
        Card defender;
        if (getTurn() % 2 == 1) {
            defender = map.getSecondPlayerCellCard().get(defenderId).getCard();
            for (int id : attackerId)
                attackers.add(map.getFirstPlayerCellCard().get(id).getCard());
        } else {
            defender = map.getFirstPlayerCellCard().get(defenderId).getCard();
            for (int id : attackerId)
                attackers.add(map.getSecondPlayerCellCard().get(id).getCard());
        }
        if (canComboAttack(defender, attackers)) {
            for (Card attacker : attackers) {
                defender.decrementOfHp(attacker.getAP());
                attacker.setCanAttack(false);
                attacker.setCanMove(false);
            }
            counterAttack(attackers.get(0).getId(), defenderId);
        } else {
            defender.decrementOfHp(attackers.get(0).getAP());
            attackers.get(0).setCanAttack(false);
            attackers.get(0).setCanMove(false);
            counterAttack(attackers.get(0).getId(), defenderId);
        }

    }


    //----------------------------------buffs--------------------------------

    private void activePassiveBuffs() {
        if (getTurn() % 2 == 1) {
            Buff.activePassiveBuff(map.getFirstPlayerCellCard());
        } else {
            Buff.activePassiveBuff(map.getSecondPlayerCellCard());
        }
    }


    //--------------------------------------------------------------------------------

    private void checkHpState(HashMap<Integer, Cell> cells, ArrayList<Card> graveYard, int player) {
        for (java.util.Map.Entry<Integer, Cell> entry : cells.entrySet()) {
            if (entry.getValue().getCard().getHP() <= 0) {
                if (player == 1 && firstPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_DEATH) {
                    for (Buff buff : firstPlayerDeck.getItem().getBuffs()) {
                        buffAlocator(entry.getValue(), buff);
                    }
                } else if (player == 2 && secondPlayerDeck.getItem().getSpActivationTime() == SPActivationTime.ON_DEATH)
                    for (Buff buff : entry.getValue().getCard().getSpecialPower()) {
                        buffAlocator(entry.getValue(), buff);
                    }
                Buff.updateBuffs();
                if (entry.getValue().getCard() instanceof Hero) {
                    winner = player;
                    isGameEnd = true;
                    break;
                }
                if (entry.getValue().getFlagCount() != 0)
                    havingFlagCount = 0;
                graveYard.add(entry.getValue().getCard());
                map.getCells()[entry.getValue().getRow()][entry.getValue().getColumn()].setCard(null);
                cells.remove(entry);
            }
        }
    }

    private void buffAlocator(Cell entry, Buff buff) {
        for (Cell cell : buff.getSpecialPowerTargetCells(entry, null,
                getMyTeam(entry),
                getOppTeam(entry), map)) {
            Buff newBuff = buff.copy();
            newBuff.setCard(cell.getCard());
            Buff.addBuff(newBuff);
        }
    }

    private HashMap<Integer, Cell> getMyTeam(Cell cell) {
        if (map.getFirstPlayerCellCard().containsValue(cell))
            return map.getFirstPlayerCellCard();
        else
            return map.getSecondPlayerCellCard();
    }

    private HashMap<Integer, Cell> getOppTeam(Cell cell) {
        if (map.getFirstPlayerCellCard().containsValue(cell))
            return map.getSecondPlayerCellCard();
        else
            return map.getFirstPlayerCellCard();
    }

    public boolean haveEnoughMana(String cardName) {
        if (getTurn() % 2 == 1) {
            for (java.util.Map.Entry<Integer, Card> entry : firstPlayerHand.entrySet()) {
                if (entry.getValue().getName().equalsIgnoreCase(cardName)) {
                    if (entry.getValue().getMP() <= firstPlayerMana) {
                        return true;
                    }
                }
            }
        } else {
            for (java.util.Map.Entry<Integer, Card> entry : secondPlayerHand.entrySet()) {
                if (entry.getValue().getName().equals(cardName)) {
                    if (entry.getValue().getMP() <= secondPlayerMana) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidCellForInsert(int x, int y) {
        if (map.isCellEmpty(x, y) && map.isTargetInMap(x, y) && isInsiderForceAbutment(x, y)) {
            return true;
        }
        return false;
    }

    public boolean isInsiderForceAbutment(int x, int y) {
        if (getTurn() % 2 == 1) {
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

    public String showNextCard() {
        if (getTurn() % 2 == 1) {
            return nextfirstPlayerCard.getCardInfoInGame();
        } else return nextSecondPlayerCard.getCardInfoInGame();
    }

    public void useSP(int x, int y) {
        if ((currentCard instanceof Minion && currentCard.getSPActivationTime() == SPActivationTime.ON_SPAWN) ||
                (currentCard instanceof Hero && currentCard.getCooldown() == 0)) {
            Card target = map.getCells()[x][y].getCard();
            for (Buff buff : currentCard.getSpecialPower()) {
                Buff newBuff = buff.copy();
                newBuff.setCard(target);
                Buff.addBuff(newBuff);
            }
            Buff.updateBuffs();
            if (currentCard instanceof Hero) {
                currentCard.setCooldown(currentCard.getBASE_COOL_DOWN());
                if (getTurn() % 2 == 1)
                    firstPlayerMana -= currentCard.getMP();
                else
                    secondPlayerMana -= currentCard.getMP();
            }
        }
    }

    public void updateCoolDown(Card hero) {
        if (hero.getCooldown() != 0)
            hero.decrementOfCoolDown(1);
    }

    public Card getHero(HashMap<Integer, Cell> cell) {
        for (java.util.Map.Entry<Integer, Cell> entry : cell.entrySet()) {
            if (entry.getValue().getCard() instanceof Hero)
                return entry.getValue().getCard();
        }
        return null;
    }

    public void addCollectable(CollectableItem item) {
        if (getTurn() % 2 == 1)
            player1Collectable.add(item);
        else
            player2Collectable.add(item);
    }

    //-------------------------grave Yard ----------------------

    public String getInfoInGraveYard(int cardId) {
        if (getTurn() % 2 == 1) {
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
        if (getTurn() % 2 == 1)
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
        if (getTurn() % 2 == 1)
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
        if (getTurn() % 2 == 1) {
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
            if (getTurn() % 2 == 1)
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


}













