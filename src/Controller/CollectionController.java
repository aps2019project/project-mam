package Controller;

import Model.deck.Deck;
import Model.enums.ErrorType;
import gson.GsonReader;
import gson.GsonWriter;
import Model.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.pages.Page;

import java.io.FileNotFoundException;
import java.io.IOException;


public class CollectionController {

    public Label search_lb;
    public Label add_lb;
    public Label create_lb;
    public Label validate_lb;
    public Label delete_lb;
    public Label select_lb;
    public Label show_lb;
    public Label port_lb;

    public Label showAll_lb = new Label();

    public TextField cSearch;
    public TextField cardId;
    public TextField nameToAdd;
    public TextField nameToCreate;
    public TextField nameToValidate;
    public TextField nameToDelete;
    public TextField nameToSelect;
    public TextField nameToShow;
    public TextField nameToPort;

    public Button search;
    public Button add;
    public Button remove;
    public Button create;
    public Button validate;
    public Button delete;
    public Button select;
    public Button showAll;
    public Button show;
    public Button export_btn;
    public Button import_btn;

    public ImageView back;

    public ScrollPane scrollPane;
    public Label scrollPane_lb;


    @FXML
    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void setSearch() {
        searchInCollection(cSearch.getText(), search_lb);
    }

    @FXML
    public void setAdd() {
        addCardToDeck(cardId.getText(), nameToAdd.getText());
    }

    @FXML
    public void setRemove() {
        removeFromDeck(cardId.getText(), nameToAdd.getText());
    }

    @FXML
    public void setCreate() {
        createDeck(nameToCreate.getText());
    }

    @FXML
    public void setValidate() {
        validateDeck(nameToValidate.getText());
    }

    @FXML
    public void setDelete() {
        deleteDeck(nameToDelete.getText());
    }

    @FXML
    public void setSelect() {
        selectMainDeck(nameToSelect.getText());
    }

    @FXML
    public void setShowAll() {
        /*showAll_lb.resizeRelocate(100, 100, 700, 500);
        showAllDecks(showAll_lb);*/
        scrollPane.setVisible(true);
        scrollPane_lb.setText(User.user.getCollection().showAllDecks());
    }

    @FXML
    public void setShow() {
        showDeck(nameToShow.getText());
    }

    @FXML
    public void setExport_btn() {
        if (User.user.getCollection().isDeckExist(nameToPort.getText())) {
            exportDeck(User.user.getCollection().getDeck(nameToPort.getText()));
            port_lb.setText("deck name: \"" + nameToPort.getText() + "\" exported");
        } else port_lb.setText("deck not found!");
    }

    @FXML
    public void setImport_btn() {
        importDeck(nameToPort.getText());
    }

    @FXML
    public void setScrollPane() {
        scrollPane.setVisible(false);
    }


    public void exportDeck(Deck deck) {
        try {
            GsonWriter.writeDeck(deck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importDeck(String deckName) {
        try {
            Deck deck = GsonReader.getDeck(deckName);
            if (User.user.getCollection().isCardsInDeckExist(deck)) {
                User.user.getCollection().getDecks().add(deck);
                port_lb.setText("deck name: \"" + deckName + "\" imported");
            } else port_lb.setText("you haven't this deck's cards!");
        } catch (FileNotFoundException e) {
            port_lb.setText("deck not found!");
        }
    }


    public static void searchInCollection(String name, Label label) {
        if (User.user.getCollection().searchCardInCollection(name)) {
            label.setText("Card ID: " + User.user.getCollection().getCardId(name));
        } else if (User.user.getCollection().searchItemInCollection(name)) {
            label.setText("Item ID: " + User.user.getCollection().getItemId(name));
        } else label.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage());
    }

    public void createDeck(String deckName) {
        if (!User.user.getCollection().checkIsExistDeck(deckName)) {
            User.user.getCollection().createDeck(deckName);
        } else create_lb.setText(ErrorType.DUPLICATE_DECK.getMessage());

    }

    public void deleteDeck(String deckName) {
        if (User.user.getCollection().checkIsExistDeck(deckName)) {
            User.user.getCollection().deleteDeck(deckName);
        } else delete_lb.setText(ErrorType.NOT_FOUND_DECK.getMessage());
    }

    public void addCardToDeck(String cardId, String deckName) {
        if (!User.user.getCollection().checkIsExistDeck(deckName)) {
            add_lb.setText(ErrorType.NOT_FOUND_DECK.getMessage());
            return;
        }
        int cardID = Integer.parseInt(cardId);
        if (User.user.getCollection().getCard(cardID) != null) {
            if (!User.user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (User.user.getCollection().getDeck(deckName).getCards().size() < 20) {
                    if (!User.user.getCollection().getDeck(deckName).cardIsExist(cardID)) {
                        User.user.getCollection().addCardToDeck(User.user.getCollection().getCard(cardID), deckName);
                        add_lb.setText(ErrorType.SUCCESSFUL_ADDING_CARD.getMessage());
                    } else add_lb.setText(ErrorType.REPETITIVE_CARD.getMessage());
                } else add_lb.setText(ErrorType.TWENTY_CARD.getMessage());
            } else if (User.user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (User.user.getCollection().getDeck(deckName).getHero() == null) {
                    User.user.getCollection().addHeroToDeck(User.user.getCollection().getCard(cardID), deckName);
                    add_lb.setText(ErrorType.SUCCESSFUL_ADDING_HERO.getMessage());
                } else add_lb.setText(ErrorType.EXTRA_HERO.getMessage());
            }
        } else if (User.user.getCollection().getItem(cardID) != null) {
            if (User.user.getCollection().getDeck(deckName).getItem() == null) {
                User.user.getCollection().addItemToDeck(User.user.getCollection().getItem(cardID), deckName);
                add_lb.setText(ErrorType.SUCCESSFUL_ADDING_ITEM.getMessage());
            } else add_lb.setText(ErrorType.EXTRA_USABLEITEM.getMessage());
        } else add_lb.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM_IN_COLLECTION.getMessage());
    }

    public void removeFromDeck(String cardId, String deckName) {
        if (!User.user.getCollection().checkIsExistDeck(deckName)) {
            add_lb.setText(ErrorType.NOT_FOUND_DECK.getMessage());
            return;
        }
        int cardID = Integer.parseInt(cardId);
        if (User.user.getCollection().isDeckExist(deckName)) {
            if (User.user.getCollection().getDeck(deckName).cardIsExist(cardID)) {
                User.user.getCollection().removeFromDeck(cardID, deckName);
                add_lb.setText(ErrorType.SUCCESSFUL_REMOVING.getMessage());
            } else add_lb.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage());
        }

        /*if (User.user.getCollection().getCard(cardID) != null) {
            if (!User.user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (User.user.getCollection().getDeck(deckName).getCards().size() < 20) {
                    if (!User.user.getCollection().getDeck(deckName).cardIsExist(cardID)) {
                        User.user.getCollection().addCardToDeck(User.user.getCollection().getCard(cardID), deckName);
                        add_lb.setText(ErrorType.SUCCESSFUL_ADDING_CARD.getMessage());
                    } else add_lb.setText(ErrorType.REPETITIVE_CARD.getMessage());
                } else add_lb.setText(ErrorType.TWENTY_CARD.getMessage());
            } else if (User.user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (User.user.getCollection().getDeck(deckName).getHero() == null) {
                    User.user.getCollection().addHeroToDeck(User.user.getCollection().getCard(cardID), deckName);
                    add_lb.setText(ErrorType.SUCCESSFUL_ADDING_HERO.getMessage());
                } else add_lb.setText(ErrorType.EXTRA_HERO.getMessage());
            }
        } else if (User.user.getCollection().getItem(cardID) != null) {
            if (User.user.getCollection().getDeck(deckName).getItem() == null) {
                User.user.getCollection().addItemToDeck(User.user.getCollection().getItem(cardID), deckName);
                add_lb.setText(ErrorType.SUCCESSFUL_ADDING_ITEM.getMessage());
            } else add_lb.setText(ErrorType.EXTRA_USABLEITEM.getMessage());
        } else add_lb.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM_IN_COLLECTION.getMessage());*/
    }

    public void selectMainDeck(String deckName) {
        if (User.user.getCollection().checkIsExistDeck(deckName)) {
            User.user.getCollection().setMainDeck(deckName);
            select_lb.setText(ErrorType.SELECT_MAIN_DECK.getMessage());
        } else select_lb.setText(ErrorType.NOT_FOUND_DECK.getMessage());
    }

    public void validateDeck(String deckName) {
        if (User.user.getCollection().isValidDeck(deckName)) {
            validate_lb.setText(ErrorType.VALID_DECK.getMessage());
        } else validate_lb.setText(ErrorType.INVALID_DECK.getMessage());
    }

    public void showDeck(String deckName) {
        if (User.user.getCollection().checkIsExistDeck(deckName)) {
            scrollPane.setVisible(true);
            scrollPane_lb.setText(User.user.getCollection().showDeck(deckName));
            //show_lb.setText(User.user.getCollection().showDeck(deckName));
        } else show_lb.setText(ErrorType.NOT_FOUND_DECK.getMessage());
    }

    public void showAllDecks(Label label) {
        label.setText(User.user.getCollection().showAllDecks());
    }

}
