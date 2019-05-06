package Model;

import view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class User {
    private static ArrayList<User> users = new ArrayList<>();
    private String name;
    private String password;
    private int numberOfWin;
    private Collection collection;
    private ArrayList<StringBuilder> lastGames;
    private int money;
    private int idCounter;

    public User(String name, String password) {
        this.name = name;
        this.money = 150000;
        this.numberOfWin = 0;
        this.password = password;
        collection = new Collection();
        lastGames = new ArrayList<>();
        idCounter = 1;
       // User.addUser(this);

       // this.numberOfWin = Integer.parseInt(password);
    }

    public User() {
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


    public static ArrayList<User> getUsers() {
        return users;
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
        return collection.getMainDeck();
    }

    public Collection getCollection() {
        return collection;
    }

    public ArrayList<StringBuilder> getLastGames() {
        return lastGames;
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

    public void addGameToLastGames(String opponent, boolean isPlayerWin, int time) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(opponent);
        stringBuilder.append("/");
        if (isPlayerWin)
            stringBuilder.append("win/");
        else
            stringBuilder.append("loss/");
        stringBuilder.append(Integer.toString(time));
        lastGames.add(stringBuilder);
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
        for (User users : User.getUsers()) {
            info.append(counter).append(" - UserName : ").append(users.getName());
            info.append(" - Wins : ").append(users.getNumberOfWin()).append("\n");
            counter++;
        }
        return info.toString();
    }

}



















