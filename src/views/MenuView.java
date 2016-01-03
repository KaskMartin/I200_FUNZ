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
    //Loome nupud, valikute jaoks
    public Button startButton = new Button("_MÄNGU ALGUS");
    public Button highscoresButton = new Button("_EDETABEL");
    public Button settingsButton = new Button("_SEADED");
    public Button helpButton = new Button("_ABI");
    public Button exitButton = new Button("_VÄLJU");
    public Font theFont = Font.font("Consolas", FontWeight.LIGHT, 24);
    public Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

    public MenuView(){
        this.setHeight(600);
        this.setWidth(800);

        Label menuPealkiri = new Label("PÜÜA AINULT TERVISLIKKU TOITU!");
        menuPealkiri.setFont(theFontSmall);
        startButton.setFont(theFont);
        highscoresButton.setFont(theFont);
        settingsButton.setFont(theFont);
        helpButton.setFont(theFont);
        exitButton.setFont(theFont);
        menuPealkiri.setFont(theFont);
        menuPealkiri.setTranslateX(185);

        startButton.setTranslateY(100);
        startButton.setTranslateX(275);
        startButton.setPrefSize(250,20);

        highscoresButton.setTranslateY(175);
        highscoresButton.setTranslateX(275);
        highscoresButton.setPrefSize(250, 20);

        settingsButton.setTranslateY(250);
        settingsButton.setTranslateX(275);
        settingsButton.setPrefSize(250, 20);

        helpButton.setTranslateY(325);
        helpButton.setTranslateX(275);
        helpButton.setPrefSize(250, 20);

        exitButton.setTranslateY(400);
        exitButton.setTranslateX(275);
        exitButton.setPrefSize(250,20);


    this.getChildren().addAll(menuPealkiri, startButton, highscoresButton, settingsButton, helpButton, exitButton);

    }
}
