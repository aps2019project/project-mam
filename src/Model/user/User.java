package Model.user;

import Model.collection.Collection;
import Model.deck.Deck;
import Model.game.Game;
import Model.game.LastGame;
import command.clientCommand.SaveCmd;
import gson.GsonWriter;
import javafx.scene.layout.Pane;
import view.pages.Page;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class User {
    public transient static User user = new User();
    private transient static ArrayList<User> users = new ArrayList<>();
    private ArrayList<LastGame> lastGames = new ArrayList<>();
    private transient LastGame currentGame = new LastGame();
    private String name;
    private String password;
    private int numberOfWin;
    private Collection collection;
    private int money;
    private int idCounter;
    private Deck mainDeck;
    private boolean isOnline = false;

    public User(String name, String password) {
        this.name = name;
        this.money = 150000;
        this.numberOfWin = 0;
        this.password = password;
        collection = new Collection();
        idCounter = 1;
    }

    public User() {
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public User(String name) {
        this.name = name;
    }

    public void setNumberOfWin(int numberOfWin) {
        this.numberOfWin = numberOfWin;
    }

    public int getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public LastGame getCurrentGame() {
        return currentGame;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumberOfWin() {
        return numberOfWin;
    }

    public int getMoney() {
        return money;
    }

    public Deck getMainDeck() {
        if (mainDeck != null)
            return mainDeck;
        return collection.getMainDeck();
    }

    public Collection getCollection() {
        return collection;
    }

    public void incrementOfMoney(int number){
        money += number;
    }

    public void incrementOfNumberOfWin(){
        numberOfWin += 1;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public void addGameToLastGames(Game game) {
        LastGame lastGame = new LastGame(game, "" + lastGames.size());
        lastGames.add(lastGame);
        currentGame = lastGame;
        GsonWriter.sendClientCommand(new SaveCmd(User.user), Page.getOutput());
    }

    public static boolean isUserNameNew(String userName) {
        for (User user : users)
            if (user.name.equals(userName)) {
                return false;
            }
        return true;
    }

    public static boolean isPassCorrect(String userName, String password) {
        for (User user : users)
            if (user.name.equals(userName)) {
                if (user.password.equals(password))
                    return true;
            }
        return false;
    }

    public static User login(String userName, String password) {
        for (User user : users)
            if (user.name.equals(userName)) {
                if (user.password.equals(password))
                    return user;
            }
        return null;
    }

    public static User[] showLeaderBoard() {
        User[] arr = new User[20];
        int numberOfUser = 0;
        for (User user : users) {
            arr[numberOfUser] = user;
            numberOfUser++;
        }
        for (int i = 0; i < numberOfUser; i++)
            for (int j = 1; j < numberOfUser - i; j++)
                if (arr[j].numberOfWin > arr[j - 1].numberOfWin) {
                    User temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
        return arr;
    }

    public Deck getRandomDeck() {
        Random random = new Random();
        int index = random.nextInt(this.collection.getDecks().size());
        return this.collection.getDecks().get(index);
    }

    public static User getUser(String userName) {
        for (User user : User.getUsers()) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    public static String showUsers(){
        StringBuilder info = new StringBuilder();
        Collections.sort(User.getUsers(), new SortUsers());
        int counter = 1;
        for (User user : User.getUsers()) {
            info.append(counter).append(" - UserName : ").append(user.getName());
            info.append(" - Wins : ").append(user.getNumberOfWin());
            if (user.isOnline)
                info.append("  online");
            else
                info.append(" ofline");
            info.append("\n");
            counter++;
        }
        return info.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}