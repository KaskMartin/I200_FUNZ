package views;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import views.gms.HighScores;

/**
 * Created by martin on 13.12.15.
 */
public class HighScoreView extends Pane {
    private String highScoresToDisplay = "";

    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

    public void ResetHighScoresToDisplay () {
        highScoresToDisplay = HighScores.printOutHighScores();
    }

    public HighScoreView() {
        this.setHeight(600);
        this.setWidth(800);
        Label highScoreLabel = new Label("Edetabel");
        ResetHighScoresToDisplay ();
        highScoreLabel.setFont(theFontSmall);
        highScoreLabel.setTranslateX(50);
        highScoreLabel.setTranslateY(100);
        Text text = new Text();
        text.setTranslateX(50);
        text.setTranslateY(130);
        text.setText(highScoresToDisplay);
        this.getChildren().addAll(highScoreLabel, text);
    }

}
