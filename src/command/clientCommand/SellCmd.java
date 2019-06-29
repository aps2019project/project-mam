package command.clientCommand;

import Model.enums.ErrorType;
import Model.shop.Shop;
import Model.user.User;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class SellCmd extends ClientCommand {
    private String cardId;
    private Shop shop = Shop.getInstance();

    public SellCmd(String cardId) {
        this.cardId = cardId;
        type = SELL;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        int ID = Integer.parseInt(cardId);
        if (shop.sellCard(ID, handler.getUser())) {
            GsonWriter.sendServerCommand(new ServerCommand(SELL, handler.getUser(), Result.SUCCESSFUL), output);
        } else if (shop.sellItem(ID, User.user)) {
            GsonWriter.sendServerCommand(new ServerCommand(SELL, handler.getUser(), Result.SUCCESSFUL), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(SELL, Result.FAILED, ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage()), output);
    }
}
