package Model;

import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static ArrayList<User> users = new ArrayList<>();
    private String name;
    private String password;
    private int numberOfWin;
    private Deck mainDeck;
    private Collection collection;
    private ArrayList<StringBuilder> lastGames;

    public User(String name, String password) {
        this.name = name;
        this.numberOfWin = 0;
        this.password = password;
        mainDeck = new Deck();
        collection = new Collection();
        lastGames = new ArrayList<>();
    }

    public User() {
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfWin() {
        return numberOfWin;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public Collection getCollection() {
        return collection;
    }

    public ArrayList<StringBuilder> getLastGames() {
        return lastGames;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
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
        boolean isUserNameNew = true;
        for (User user : users)
            if (user.name.equals(userName)) {
                isUserNameNew = false;
                //View.getInstance().printError("username is'nt new");
                return false;
            }

        return true;
    }

    public static User login(String userName, String password) {
        boolean isUserNameValid = false;
        for (User user : users)
            if (user.name.equals(userName)) {
                isUserNameValid = true;
                if (user.password.equals(password))
                    return user;
            }
        if (!isUserNameValid)
            View.getInstance().printError("userName is inValid");
        else
            View.getInstance().printError("password is incorrect");

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

    public boolean isMainDeckValid(){
        return true;
    }


}



















