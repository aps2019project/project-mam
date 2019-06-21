package Controller;

import Model.enums.BuffType;
import Model.enums.ImpactType;
import Model.enums.TargetCommunity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import static Model.enums.BuffType.*;
import static Model.enums.ImpactType.*;
import static Model.enums.TargetCommunity.*;

public class CustomController {

    public ToggleGroup cardType = new ToggleGroup();
    ToggleGroup spellTarget = new ToggleGroup();
    ToggleGroup buffType = new ToggleGroup();
    ToggleGroup enemyOrFriend = new ToggleGroup();
    ToggleGroup attackType = new ToggleGroup();

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

    public TextField cardName;
    public TextField cost;

    public TextField buffName;
    public TextField delay;
    public TextField last;
    public TextField effectValue;

    public TextField ap;
    public TextField hp;
    public TextField range;
    public TextField activation;
    public TextField coolDown;

    public Label label;
    public ImageView back;

    public CustomController(){

    }

    public void init(){
        minion.setToggleGroup(cardType);
        minion.setSelected(true);
        minion.setUserData("minion");
        hero.setToggleGroup(cardType);
        hero.setUserData("hero");
        spell.setToggleGroup(cardType);
        spell.setUserData("spell");
        cardType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (cardType.getSelectedToggle() != null) {
                    restate(cardType);
                }
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
        spellTarget.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
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
        buffType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (buffType.getSelectedToggle() != null) {
                    restate(buffType);
                }
            }
        });

        friend.setToggleGroup(enemyOrFriend);
        friend.setSelected(true);
        friend.setUserData("friend");
        enemy.setToggleGroup(enemyOrFriend);
        enemy.setUserData("enemy");
        enemyOrFriend.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
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
        attackType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (attackType.getSelectedToggle() != null) {
                    restate(attackType);
                }
            }
        });
    }

    public void restate(ToggleGroup toggleGroup){
        label.setText(toggleGroup.getSelectedToggle().getUserData().toString());
    }


}
