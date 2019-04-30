package Model;

public class AI {

    private static final User ai = new User("AI", "0");

    public static User getAI() {
        return ai;
    }

    private String command = null;

    public String getCommand() {
        return command;
    }
}
