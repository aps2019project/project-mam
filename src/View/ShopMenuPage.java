package View;

public class ShopMenuPage extends ConsolePage {

    @Override
    public void help() {
        System.out.print("Exit \nshow collection \nsearch [item name | card name] \nbuy [card name | item name] \n" +
                "sell [card id | card id] \nshow");
    }

    @Override
    public void handleCommand(String command) {

    }
}
