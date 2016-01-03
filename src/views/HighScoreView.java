package views;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lib.HighScores;

/**
 * Created by martin on 13.12.15.
 */
public class HighScoreView extends Pane {
    private Text text = new Text();

    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

    public void ResetHighScoresToDisplay () {
        String highScoresToDisplay = HighScores.printOutHighScores();
        text.setText(highScoresToDisplay);
        text.setTranslateX(50);
        text.setTranslateY(130);
    }

    public HighScoreView() {
        this.setHeight(600);
        this.setWidth(800);
        Label highScoreLabel = new Label("Edetabel");
        ResetHighScoresToDisplay ();
        highScoreLabel.setFont(theFontSmall);
        highScoreLabel.setTranslateX(50);
        highScoreLabel.setTranslateY(100);
        this.getChildren().addAll(highScoreLabel, text);
    }

}
