package Controller.GameController;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AudioController {
    private static AudioController ourInstance = new AudioController();

    public static AudioController getInstance() {
        return ourInstance;
    }

    private AudioController() {
    }

    private final String MOVE_PATH = "sfx_division_crest_reveal.m4a";
    private final String ATTACK_PATH = "sfx_f5_vindicator_attack_swing.m4a";


    private MediaPlayer getPlayer(String name) {
        Media media = new Media(new File("resources/sfx/" + name).toURI().toString());
        return new MediaPlayer(media);
    }

    private void setUpAudio(MediaPlayer player) {
        player.play();
        player.setAutoPlay(false);
    }

    public void onMove() {
        setUpAudio(getPlayer(MOVE_PATH));
    }

    public void onAttack() {
        setUpAudio(getPlayer(ATTACK_PATH));
    }

    public void onInsert() {
        setUpAudio(getPlayer("sfx_unit_run_magical_4.m4a"));
    }

    public void onSelect() {
        setUpAudio(getPlayer("sfx_ui_select.m4a"));
    }

    void onEndTurn(){
        setUpAudio(getPlayer("sfx_ui_yourturn_1.m4a"));
    }


}
