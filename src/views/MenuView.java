package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by martin on 13.12.15.
 */
public class MenuView extends Pane {
    public Button startButton = new Button("Start");
    public Button highscoresButton = new Button("Highscores");
    public Button settingsButton = new Button("Settings");
    public Button helpButton = new Button("Help");
    public Button exitButton = new Button("Exit");

    public MenuView(){
        Font theFont = Font.font("Consolas", FontWeight.LIGHT, 24);
        Label menuPealkiri = new Label("Püüa ainult tervislikku toitu!");


        startButton.setFont(theFont);
        highscoresButton.setFont(theFont);
        settingsButton.setFont(theFont);
        helpButton.setFont(theFont);
        exitButton.setFont(theFont);
        menuPealkiri.setFont(theFont);

        startButton.setTranslateY(100);
        startButton.setTranslateX(300);
        startButton.setPrefSize(200,20);

        highscoresButton.setTranslateY(175);
        highscoresButton.setTranslateX(300);
        highscoresButton.setPrefSize(200, 20);

        settingsButton.setTranslateY(250);
        settingsButton.setTranslateX(300);
        settingsButton.setPrefSize(200, 20);

        helpButton.setTranslateY(325);
        helpButton.setTranslateX(300);
        helpButton.setPrefSize(200, 20);

        exitButton.setTranslateY(400);
        exitButton.setTranslateX(300);
        exitButton.setPrefSize(200,20);

    this.getChildren().addAll(menuPealkiri, startButton, highscoresButton, settingsButton, helpButton, exitButton);
    }
}
