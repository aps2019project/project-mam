package Controller;

import Model.Buffs.Buff;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import Model.enums.BuffType;
import Model.enums.ImpactType;
import Model.enums.SPActivationTime;
import Model.enums.TargetCommunity;
import Model.shop.Shop;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import view.pages.Page;

import static Model.enums.BuffType.*;
import static Model.enums.ImpactType.*;
import static Model.enums.SPActivationTime.*;
import static Model.enums.TargetCommunity.*;

public class CustomController {

    public ToggleGroup cardType = new ToggleGroup();
    ToggleGroup spellTarget = new ToggleGroup();
    ToggleGroup buffType = new ToggleGroup();
    ToggleGroup enemyOrFriend = new ToggleGroup();
    ToggleGroup attackType = new ToggleGroup();
    ToggleGroup activation = new ToggleGroup();

    public VBox cardVB;
    public VBox spellVB;
    public VBox minionVB;

    public RadioButton minion;
    public RadioButton hero;
    public RadioButton spell;

    public RadioButton oneE;
    public RadioButton oneI;
    public RadioButton allE;
    public RadioButton allI;
    public RadioButton enemyH;
    public RadioButton insiderH;
    public RadioButton own;
    public RadioButton two;
    public RadioButton three;

    public RadioButton holy;
    public RadioButton power;
    public RadioButton poison;
    public RadioButton stun;
    public RadioButton weakness;
    public RadioButton disarm;

    public RadioButton friend;
    public RadioButton enemy;

    public RadioButton melee;
    public RadioButton ranged;
    public RadioButton hybrid;

    public RadioButton passive;
    public RadioButton onDeath;
    public RadioButton onAttack;
    public RadioButton onInsert;

    public TextField cardName;
    public TextField cost;

    public TextField buffName;
    public TextField delay;
    public TextField last;
    public TextField effectValue;

    public TextField ap;
    public TextField hp;
    public TextField range;
    public TextField coolDown;

    public Button create;

    public Label label;
    public ImageView back;

    public CustomController() {
    }

    public void init() {
        create.setStyle("-jfx-button-type: FLAT;" +
                "-fx-background-color: rgba(45,45,45,0.7);" +
                "-fx-text-fill: #fff5fd;");


        minion.setToggleGroup(cardType);
        minion.setSelected(true);
        minion.setUserData("minion");
        hero.setToggleGroup(cardType);
        hero.setUserData("hero");
        spell.setToggleGroup(cardType);
        spell.setUserData("spell");
        cardType.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (cardType.getSelectedToggle() != null) {
                restate(cardType);
            }
        });

        oneE.setToggleGroup(spellTarget);
        oneE.setSelected(true);
        oneE.setUserData(ONE_ENEMY_FORCE);
        oneI.setToggleGroup(spellTarget);
        oneI.setUserData(ONE_INSIDER_FORCE);
        allE.setToggleGroup(spellTarget);
        allE.setUserData(ALL_ENEMY_FORCES);
        allI.setToggleGroup(spellTarget);
        allI.setUserData(ALL_INSIDER_FORCES);
        enemyH.setToggleGroup(spellTarget);
        enemyH.setUserData(ENEMY_HERO);
        insiderH.setToggleGroup(spellTarget);
        insiderH.setUserData(INSIDER_HERO);
        own.setToggleGroup(spellTarget);
        own.setUserData(OWN);
        two.setToggleGroup(spellTarget);
        two.setUserData(TWO_IN_TWO);
        three.setToggleGroup(spellTarget);
        three.setUserData(THREE_IN_THREE);
        spellTarget.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (spellTarget.getSelectedToggle() != null) {
                    restate(spellTarget);
                }
            }
        });

        holy.setToggleGroup(buffType);
        holy.setSelected(true);
        holy.setUserData(HOLY);
        power.setToggleGroup(buffType);
        power.setUserData(ATTACK_POWER);
        poison.setToggleGroup(buffType);
        poison.setUserData(POISON);
        stun.setToggleGroup(buffType);
        stun.setUserData(STUN);
        weakness.setToggleGroup(buffType);
        weakness.setUserData(ATTACK_WEAKNESS);
        disarm.setToggleGroup(buffType);
        disarm.setUserData(DISARM);
        buffType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (buffType.getSelectedToggle() != null) {
                    restate(buffType);
                }
            }
        });

        friend.setToggleGroup(enemyOrFriend);
        friend.setSelected(true);
        friend.setUserData(ONE_INSIDER_FORCE);
        enemy.setToggleGroup(enemyOrFriend);
        enemy.setUserData(ONE_ENEMY_FORCE);
        enemyOrFriend.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (enemyOrFriend.getSelectedToggle() != null) {
                    restate(enemyOrFriend);
                }
            }
        });

        melee.setToggleGroup(attackType);
        melee.setSelected(true);
        melee.setUserData(MELEE);
        ranged.setToggleGroup(attackType);
        ranged.setUserData(RANGED);
        hybrid.setToggleGroup(attackType);
        hybrid.setUserData(HYBRID);
        attackType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (attackType.getSelectedToggle() != null) {
                    restate(attackType);
                }
            }
        });

        passive.setToggleGroup(activation);
        passive.setSelected(true);
        passive.setUserData(PASSIVE);
        onDeath.setToggleGroup(activation);
        onDeath.setUserData(ON_DEATH);
        onAttack.setToggleGroup(activation);
        onAttack.setUserData(ON_ATTACK);
        onInsert.setToggleGroup(activation);
        onInsert.setUserData(ON_INSERT);
        activation.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (activation.getSelectedToggle() != null) {
                    restate(activation);
                }
            }
        });
    }


    private Card card;


    public void restate(ToggleGroup toggleGroup) {
        label.setText(toggleGroup.getSelectedToggle().getUserData().toString());
    }

    @FXML
    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void onCreateClick() {
        if (!isInfoComplete())
            return;
        initCard();
        Shop.getCards().add(card);
        label.setText("created");
        System.out.println("created");
    }

    private void initCard() {
        switch (cardType.getSelectedToggle().getUserData().toString()) {
            case "minion":
                initMinion();
                break;
            case "hero":
                initHero();
                break;
            case "spell":
                initSpell();
        }
    }

    private void initSpell() {
        card = new Spell(cardName.getText(), Integer.parseInt(cost.getText()), 2);
        card.addBuff(new Buff((BuffType) buffType.getUserData(), Integer.parseInt(delay.getText()) ,
                Integer.parseInt(effectValue.getText()), (TargetCommunity) spellTarget.getUserData()));
    }

    private void initHero() {
        card = new Hero(cardName.getText(), Integer.parseInt(cost.getText()),
                Integer.parseInt(hp.getText()), Integer.parseInt(ap.getText()),
                (ImpactType) attackType.getUserData(), Integer.parseInt(range.getText()),
                2, Integer.parseInt(coolDown.getText()));
        card.addBuff(new Buff((BuffType) buffType.getUserData(), Integer.parseInt(delay.getText()) ,
                Integer.parseInt(effectValue.getText()), (TargetCommunity) enemyOrFriend.getUserData()));
    }

    private void initMinion() {
        card = new Minion(cardName.getText(), Integer.parseInt(cost.getText()),
                2, Integer.parseInt(hp.getText()), Integer.parseInt(ap.getText()), Integer.parseInt(range.getText()),
                (ImpactType) attackType.getUserData(), (SPActivationTime) activation.getUserData());
        card.addBuff(new Buff((BuffType) buffType.getUserData(), Integer.parseInt(delay.getText()) ,
                Integer.parseInt(effectValue.getText()), (TargetCommunity) enemyOrFriend.getUserData()));
    }

    private boolean isInfoComplete() {
        if (cardName.getText().equals("") ||
                cost.getText().equals("") ||
                buffName.getText().equals("") ||
                delay.getText().equals("") ||
                last.getText().equals("")) {
            label.setText("please fill fields!");
            return false;
        }
        if (cardType.getSelectedToggle().getUserData().toString().equals("minion") &&
                (ap.getText().equals("") || hp.getText().equals("") || range.getText().equals(""))){
            label.setText("please fill fields!");
            return false;
        }
        if (cardType.getSelectedToggle().getUserData().toString().equals("hero") && (range.getText().equals("") ||
                ap.getText().equals("") || hp.getText().equals("") || coolDown.getText().equals(""))){
            label.setText("please fill fields!");
            return false;
        }
        return true;
    }
}
