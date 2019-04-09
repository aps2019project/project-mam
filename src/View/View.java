package View;

import Model.ErrorType;

public class View {
    private static final View VIEW = new View();
    private View(){}

    public static View getInstance(){
        return VIEW;
    }

    public void printError(ErrorType type){}

    public void showHelpForAccountMenu(){}
    public void showHelpForMainMenu(){}
}
