package Controller.GameController;

import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Game;
import Model.size.Coordinate;
import Model.size.Resolution;
import Model.user.AI;
import Model.user.User;
import command.ServerCommand;
import command.clientCommand.ClientCommand;
import command.clientCommand.EndGameCmd;
import command.clientCommand.EndTurnCmd;
import command.clientCommand.SaveCmd;
import command.clientCommand.GetGameCmd;
import config.Config;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import view.pages.Page;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameController {
    private MapController mapCtrl = MapController.getInstance();
    private Game game = Game.getInstance();
    private ImageController imageCtrl = ImageController.getInstance();
    private AI ai = AI.getInstance();
    private Coordinate coordinate = Coordinate.getInstance();

    public Label firstPlayerName;
    public Label firstPlayerMana;
    public Label secondPlayerName;
    public Label secondPlayerMana;
    public Label item1mana;
    public Label item2mana;
    public Label handCard1mana;
    public Label handCard2mana;
    public Label handCard3mana;
    public Label handCard4mana;
    public Label handCard5mana;
    public Label label;

    public ImageView graveyard;
    public ImageView exit;
    public ImageView help;
    public ImageView endTurn;
    public ImageView forward;

    public Circle item1;
    public Circle item2;
    public Circle nextCard;
    public Circle handCard1;
    public Circle handCard2;
    public Circle handCard3;
    public Circle handCard4;
    public Circle handCard5;

    //------------------me-----------
    public ImageView backGround;
    public Pane pane;

    public ScrollPane gameInfoSP;
    public Label gameInfoL;

    public ScrollPane graveyardSP;
    public Label graveyardL;

    public ScrollPane userInfo1SP;
    public Label userInfo1L;

    public ScrollPane userInfo2SP;
    public Label userInfo2L;

    //------------------me-----------


    public Rectangle[][] cells = new Rectangle[5][9];
    public ArrayList<Circle> handCards = new ArrayList<>();
    public ArrayList<Label> handCardsMana = new ArrayList<>();
    private MediaPlayer musicPlayer = AudioController.getInstance().getPlayer("music_battlemap_duskfall.m4a");

    //private int speed = 1;
    //private int turnTime = 60;


    int count = 0;
    Timer timer = new Timer();

    public void initializeGame() {
        GsonWriter.writeGame(game);

        mapCtrl.setController(this);
        mapCtrl.initialize(cells, pane, label, handCards);
        if (game.isMulti() || game.isLive())
            catchServerCommand();
        if (game.isMyTurn())
            if (!game.isLive())
                setTimer();
        setUpMusic();
    }

    private void setUpMusic() {
        musicPlayer.play();
        musicPlayer.setAutoPlay(true);
        musicPlayer.setVolume(6);
    }

    public void init() {
        handCards.add(handCard1);
        handCards.add(handCard2);
        handCards.add(handCard3);
        handCards.add(handCard4);
        handCards.add(handCard5);

        handCardsMana.add(handCard1mana);
        handCardsMana.add(handCard2mana);
        handCardsMana.add(handCard3mana);
        handCardsMana.add(handCard4mana);
        handCardsMana.add(handCard5mana);
    }

    /*public int getSpeed() {
        return speed;
    }*/

    public void setForward() {
        Config.speed = 2;
    }

    public void setUserInfo1() {
        if (userInfo1SP.isVisible())
            userInfo1SP.setVisible(false);
        else {
            userInfo1SP.setVisible(true);
            userInfo1L.setText(game.getFirstUser().getName());
        }
    }

    public void setUserInfo2() {
        if (userInfo2SP.isVisible())
            userInfo2SP.setVisible(false);
        else {
            userInfo2SP.setVisible(true);
            userInfo2L.setText(game.getSecondUser().getName());
        }
    }

    public void setGameInfo() {
        if (gameInfoSP.isVisible())
            gameInfoSP.setVisible(false);
        else {
            gameInfoSP.setVisible(true);
            gameInfoL.setText(game.getGameInfo());
        }
    }

    public void setGraveyard() {
        if (graveyardSP.isVisible())
            graveyardSP.setVisible(false);
        else {
            graveyardSP.setVisible(true);
            StringBuilder info = new StringBuilder();
            for (Card card : game.getFirstPlayerGraveYard()) {
                info.append(card.getInfo()).append("\n");
            }
            graveyardL.setText(info.toString());
        }
    }

    public void setExit() {

        timer.cancel();
        count = 0;

        //test
        User.user.getGames().add(game.getId());
        GsonWriter.sendClientCommand(new EndGameCmd(game.getId()), Page.getOutput());
        //GsonWriter.sendClientCommand(new SaveCmd(User.user), Page.getOutput());
        //GsonWriter.writeGame(game);
        //

        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void setHelp() {
        label.setText("heeeeelp!!!");
    }

    public void setEndTurn() {
        if (!game.isMyTurn())
            return;
        Config.speed = 1;
        endTurn();
        //User.user.getCurrentGame().getCommands().add(new EndTurnCmd());
        //if (game.isMulti())
            GsonWriter.sendClientCommand(new EndTurnCmd(), Page.getOutput());
    }

    public void endTurn() {
        if (game.getNextFirstPlayerCard() != null)
            mapCtrl.removeNextCard();
        if (game.isMyTurn() && !game.isLive()) {
            timer.cancel();
            count = 0;
        } else if (!game.isLive())
            setTimer();
        AudioController.getInstance().onEndTurn();
        game.endTurn();
        label.setText("---<End turn>---");
        if (game.isGameEnd()) {
            User.user.getGames().add(game.getId());
            GsonWriter.sendClientCommand(new EndGameCmd(game.getId()), Page.getOutput());
            //GsonWriter.sendClientCommand(new SaveCmd(User.user), Page.getOutput());
            //GsonWriter.writeGame(game);
            label.setText("-----<game ended>------\n" + game.getWinnerName() + " win");
            AudioController.getInstance().onEndGame();
        } else {
            mapCtrl.updateHand();
            mapCtrl.updatePlayersMana();
            mapCtrl.updateNextCard();
            mapCtrl.updateMap();
            if (!game.isMyTurn() && game.getSecondUser().getName().equals("AI") && !game.isLive()) {
                ai.getCommand();
            }
        }
    }

    private void setTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (count == Config.turnTime) {
                    System.out.println("ended");
                    Platform.runLater(() -> setEndTurn());
                    count = 0;
                    cancel();
                } else System.out.println(count++);
            }
        }, 0, 1000);
    }

    private void catchServerCommand() {
        Thread catchCommand = new Thread(() -> {
            while (true) {
                ServerCommand command = GsonReader.getServerCommand(Page.getInput());
                switch (command.getType()) {
                    case SELECT:
                        Platform.runLater(() -> mapCtrl.selectCard(command.getCardId()));
                        break;
                    case MOVE:
                        if (game.isLive()) {
                            if (game.isMyTurn() || game.getSecondUser().getName().equals("AI"))
                                Platform.runLater(() -> mapCtrl.moveCard(command.getRow(), command.getColumn()));
                            else
                                Platform.runLater(() -> mapCtrl.moveCard(command.getRow(), 8 - command.getColumn()));
                        } else
                            Platform.runLater(() -> mapCtrl.moveCard(command.getRow(), command.getColumn()));
                        break;
                    case ATTACK:
                        Platform.runLater(() -> mapCtrl.attack(Integer.parseInt(command.getCardId())));
                        break;
                    case ENDTURN:
                        /*if (game.isLive())
                            game.changeBaseTurn();*/
                        Platform.runLater(this::endTurn);
                        break;
                    case INSERT:
                        if (game.isLive()) {
                            if (game.isMyTurn() || game.getSecondUser().getName().equals("AI"))
                                Platform.runLater(() -> mapCtrl.insertCard(command.getCardName(), command.getRow(), command.getColumn()));
                            else
                                Platform.runLater(() -> mapCtrl.insertCard(command.getCardName(), command.getRow(), 8 - command.getColumn()));
                        } else
                            Platform.runLater(() -> mapCtrl.insertCard(command.getCardName(), command.getRow(), command.getColumn()));
                        break;
                    case GET_GAME:
                        GsonWriter.sendClientCommand(new GetGameCmd(Game.getInstance(), command.getUserName()), Page.getOutput());
                        System.out.println("game send");
                        break;
                }
            }
        });
        catchCommand.setName("catchCommand");
        catchCommand.start();
    }
}
