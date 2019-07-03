package command.clientCommand;

import Model.enums.ErrorType;
import Model.shop.Shop;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class SearchCmd extends ClientCommand {
    private String cardName;
    private Shop shop = Shop.getInstance();

    public SearchCmd(String cardName) {
        this.cardName = cardName;
        type = SEARCH;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        if (shop.searchCard(cardName)) {
            GsonWriter.sendServerCommand(new ServerCommand(SEARCH, Result.SUCCESSFUL, shop.getCardInfo(cardName)), output);
        } else if (shop.searchItem(cardName)) {
            GsonWriter.sendServerCommand(new ServerCommand(SEARCH, Result.SUCCESSFUL, shop.getItemInfo(cardName)), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(SEARCH, Result.SUCCESSFUL, ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage()), output);
    }
}
