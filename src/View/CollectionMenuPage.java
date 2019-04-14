package View;

public class CollectionMenuPage extends ConsolePage {

    @Override
    public void help() {
        System.out.print("Exit \nshow \nsearch [card name | item name] \nsave \ncreate deck [deck name] \n" +
                "delete deck[name] \nadd [card id | card id | hero id] to deck [deck name] \nremove deck [deck name]" +
                "validate deck [deck name] \nselect deck [deck name] \nshow all decks \nshow deck [deck name]");
    }

    @Override
    public void handleCommand(String command) {

    }

}
