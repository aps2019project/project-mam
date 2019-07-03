package command.clientCommand;

import Model.enums.ErrorType;
import Model.shop.Shop;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class BuyCmd extends ClientCommand {
    private String cardName;
    private Shop shop = Shop.getInstance();

    public BuyCmd(String cardName) {
        this.cardName = cardName;
        type = BUY;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        if (shop.cardNameIsAvailable(cardName)) {
            if (shop.priceIsEnough(shop.getCardPrice(cardName), handler.getUser())) {
                if (shop.canBuyCard(cardName)) {
                    shop.buyCard(cardName, handler.getUser());
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, handler.getUser(), Result.SUCCESSFUL, ErrorType.SUCCESSFUL_BUY.getMessage()), output);
                } else
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.CAN_NOT_BUY_CARD.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.MONEY_IS_NOT_ENOUGH.getMessage()), output);
        } else if (shop.itemNameIsAvailable(cardName)) {
            if (shop.isPossibleToAddItem(handler.getUser())) {
                if (shop.priceIsEnough(shop.getItemPrice(cardName), handler.getUser())) {
                    shop.buyItem(cardName, handler.getUser());
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.SUCCESSFUL, ErrorType.SUCCESSFUL_BUY.getMessage()), output);
                } else
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.MONEY_IS_NOT_ENOUGH.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.THREE_ITEM.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.UNAVAILABLE_CARD_OR_ITEM.getMessage()), output);

    }
}
