package command.clientCommand;

import Model.enums.ErrorType;
import Model.shop.Shop;
import Model.user.User;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;

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
    public void handleCommand(DataOutputStream output) {
        if (shop.cardNameIsAvailable(cardName)) {
            if (shop.priceIsEnough(shop.getCardPrice(cardName), User.user)) {
                shop.buyCard(cardName, User.user);
                GsonWriter.sendServerCommand(new ServerCommand(BUY, User.user, Result.SUCCESSFUL, ErrorType.SUCCESSFUL_BUY.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.MONEY_IS_NOT_ENOUGH.getMessage()), output);
        } else if (shop.itemNameIsAvailable(cardName)) {
            if (shop.isPossibleToAddItem(User.user)) {
                if (shop.priceIsEnough(shop.getItemPrice(cardName), User.user)) {
                    shop.buyItem(cardName, User.user);
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.SUCCESSFUL, ErrorType.SUCCESSFUL_BUY.getMessage()), output);
                } else
                    GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.MONEY_IS_NOT_ENOUGH.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.THREE_ITEM.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(BUY, Result.FAILED, ErrorType.UNAVAILABLE_CARD_OR_ITEM.getMessage()), output);

    }
}
