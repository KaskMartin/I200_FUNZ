import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import views.*;
import views.gms.HighScores;
import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Menyy heli mängimise haldamine
    private boolean soundIsOn = true;

    public void toggleSound () {
        soundIsOn = (!soundIsOn);
    }

    public void PlayMenuSelectSound() {
        if (soundIsOn) {
            Media sound = new Media(new File("src/sounds/167127__crisstanza__pause.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            }
    }

    @Override
    public void start(Stage stage) {
        //Lava suuruse määratlemine
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setResizable(false);

        //Kutsume välja igast vaatest ühe objekti (kõik on klassi "Pane" lapsed)
        MenuView menu = new MenuView();
        GameView game = new GameView();
        HighScoreView currentHighScores = new HighScoreView();
        HelpView help = new HelpView();
        SettingsView settings = new SettingsView();

        //Kutsume välja üldise plaani (on klassi "Group" laps) ning liidame talle alustuseks menyy, peidame "TAGASI!" nupu
        Layout layout = new Layout(menu);
        layout.hideBackButton();

        //TAGASI! nupu haldamine
        layout.backMenuButton.setOnAction(e -> {
            PlayMenuSelectSound();
            game.stopGame();
            layout.setContent(menu);
            layout.hideBackButton();
        });

        //Heli nupu vajutamise haldamine
        layout.soundOffOnButton.setOnAction(e -> {
            game.toggleSound();
            toggleSound();
            if (soundIsOn)
                layout.soundOffOnButton.setTextFill(Color.GREEN);
            else
                layout.soundOffOnButton.setTextFill(Color.RED);
        });

        //idee poolest tahaksime et edetabel uuendaks ennast kui uus skoor on sisestatud. HETKEL EI TÖÖTA!
        HighScores.nameEntryButton.setOnAction(event -> {
            currentHighScores.ResetHighScoresToDisplay();
        });

        //Klaviatuuri nupuvajutuste haldamine
        layout.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!game.input.contains(code))
                game.input.add(code);
        });

        layout.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            game.input.remove(code);
        });

        //Peamenyys tehtud muutuste haldamine
        menu.startButton.setOnAction(e -> {
            PlayMenuSelectSound();
            game.resetGame();
            layout.setContent(game);
        });
        menu.highscoresButton.setOnAction(e -> {
            currentHighScores.ResetHighScoresToDisplay();
            PlayMenuSelectSound();
            layout.setContent(currentHighScores);
        });
        menu.settingsButton.setOnAction(e -> {
            PlayMenuSelectSound();
            layout.setContent(settings);
        });
        menu.helpButton.setOnAction(e -> {
            PlayMenuSelectSound();
            layout.setContent(help);
        });
        menu.exitButton.setOnAction(e -> {
            PlayMenuSelectSound();
            System.exit(0);
        });

        //mängu lõppemise haldamine. (GameView game-is asetseb SimpleBooleanProperty, mis muutub "true"-ks, kui mäng lõppeb)
        game.gameOver.addListener(e -> {
            if (game.gameOver.getValue()) {
                int finalScore = 100 * game.getGoodScore();
                System.out.println("Mäng sai läbi!"); //TESTING!
                HighScores.addScore(finalScore);
                currentHighScores.ResetHighScoresToDisplay();
                layout.setContent(currentHighScores);
            }
        });

        //Seadetes tehtud muutuste haldamine
        settings.user1Image1Button.setOnAction(e -> {
            game.user1.setCollision(3, 21, 75, 21);
            game.user1.setImage("images/kasutaja01.png");
        });

        settings.user1Image2Button.setOnAction(e -> {
            game.user1.setCollision(79, 18, 81, 26); //Muuda collision box ära!!!
            game.user1.setImage("images/kasutaja02.png");
        });

        settings.user1Image3Button.setOnAction(e -> {
            game.user1.setCollision(79, 18, 81, 26);
            game.user1.setImage("images/kasutaja03.png");
        });

        settings.user1Image4Button.setOnAction(e -> {
            game.user1.setCollision(79, 18, 81, 26); //Muuda collision box ära!!!
            game.user1.setImage("images/kasutaja04.png");
        });

        settings.user2Image1Button.setOnAction(e -> {
            game.user2.setCollision(3, 21, 75, 21);
            game.user2.setImage("images/kasutaja01.png");
        });

        settings.user2Image2Button.setOnAction(e -> {
            game.user2.setCollision(79, 18, 81, 26); //Muuda collision box ära!!!
            game.user2.setImage("images/kasutaja02.png");
        });

        settings.user2Image3Button.setOnAction(e -> {
            game.user2.setCollision(79, 18, 81, 26);
            game.user2.setImage("images/kasutaja03.png");
        });

        settings.user2Image4Button.setOnAction(e -> {
            game.user2.setCollision(79, 18, 81, 26); //Muuda collision box ära!!!
            game.user2.setImage("images/kasutaja04.png");
        });

        //Lavale tseeni sättimine ja lava näitamine
        stage.setTitle("Püüa ainult tervislikku toitu!");
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        //kui nupust kinni panna siis peaks ka aplikatsioon kinni minema
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
    }

}