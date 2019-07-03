package command.clientCommand;

import Model.card.Card;
import Model.shop.Shop;
import command.CommandType;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;
import java.io.IOException;

public class CustomCmd extends ClientCommand {
    private Card card;

    public CustomCmd(Card card) {
        this.card = card;
        type = CommandType.CREATE_CUSTOM;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        try {
            Shop.getCards().add(card);
            GsonWriter.writeCustomCard(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
