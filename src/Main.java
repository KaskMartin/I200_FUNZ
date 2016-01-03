import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import views.*;
import lib.HighScores;
import java.io.File;
import static lib.HighScores.nameEntryButton;
import static lib.HighScores.nameEntryField;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public ImageView soundOnImage = new ImageView("images/soundON.png");
    public ImageView soundOffImage = new ImageView("images/soundOFF.png");

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
                layout.soundOffOnButton.setGraphic(soundOnImage);
            else
                layout.soundOffOnButton.setGraphic(soundOffImage);
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
                int finalScore = 100 * game.getUsersCombinedScore();
                System.out.println("Mäng sai läbi!"); //TESTING!
                HighScores.addScore(finalScore);
            }
        });

        //Seadetes tehtud muutuste haldamine
        settings.user1Image1Button.setOnAction(e -> {
            game.user1.setCollision(0, 37, 75, 17);
            game.user1.setImage("images/kasutaja1sinine.png");
        });

        settings.user1Image2Button.setOnAction(e -> {
            game.user1.setCollision(63, 35, 101, 17); //Muuda collision box ära!!!
            game.user1.setImage("images/kasutaja2sinine.png");
        });

        settings.user1Image3Button.setOnAction(e -> {
            game.user1.setCollision(57, 18, 85, 15);
            game.user1.setImage("images/kasutaja3sinine.png");
        });

        settings.user1Image4Button.setOnAction(e -> {
            game.user1.setCollision(0, 130, 73, 16); //Muuda collision box ära!!!
            game.user1.setImage("images/kasutaja4sinine.png");
        });

        settings.user2Image1Button.setOnAction(e -> {
            game.user2.setCollision(0, 37, 75, 17);
            game.user2.setImage("images/kasutaja1punane.png");
        });

        settings.user2Image2Button.setOnAction(e -> {
            game.user2.setCollision(63, 35, 101, 17); //Muuda collision box ära!!!
            game.user2.setImage("images/kasutaja2punane.png");
        });

        settings.user2Image3Button.setOnAction(e -> {
            game.user2.setCollision(57, 18, 85, 15);
            game.user2.setImage("images/kasutaja3punane.png");
        });

        settings.user2Image4Button.setOnAction(e -> {
            game.user2.setCollision(0, 130, 73, 16); //Muuda collision box ära!!!
            game.user2.setImage("images/kasutaja4punane.png");
        });

        //idee poolest tahaksime et edetabel uuendaks ennast kui uus skoor on sisestatud. HETKEL EI TÖÖTA!
        nameEntryButton.addEventHandler(ActionEvent.ACTION, event -> {

            System.out.println("name has been entered");
            currentHighScores.ResetHighScoresToDisplay();
            layout.setContent(currentHighScores);
        });


        nameEntryField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                currentHighScores.ResetHighScoresToDisplay();
                layout.setContent(currentHighScores);
            }
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