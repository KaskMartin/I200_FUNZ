import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import views.*;
import views.gms.HighScores;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void PlayPauseSound() {
        Media sound = new Media(new File("src/sounds/167127__crisstanza__pause.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    @Override
    public void start(Stage stage) {
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setResizable(false);

        MenuView menu = new MenuView();
        GameView game = new GameView();
        //HelpView currentHighScores = new HelpView(); //NB!  pandud help siia praegu peaks olema: HighScoreView currentHighScores = new HighScoreView();
        HighScoreView currentHighScores = new HighScoreView();
        HighScores edetabel = new HighScores();
        HelpView help = new HelpView();
        SettingsView settings = new SettingsView();
        Layout layout = new Layout(menu);
        layout.hideBackButton();
        layout.backMenuButton.setOnAction(e -> {
            PlayPauseSound();
            game.stopGame();
            layout.setContent(menu);
            layout.hideBackButton();
        });

        layout.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!game.input.contains(code))
                game.input.add(code);
        });

        layout.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            game.input.remove(code);
        });

        menu.startButton.setOnAction(e -> {
            PlayPauseSound();
            game.resetGame();
            layout.setContent(game);
        });
        menu.highscoresButton.setOnAction(e -> {
            PlayPauseSound();
            layout.setContent(currentHighScores);
        });
        menu.settingsButton.setOnAction(e -> {
            PlayPauseSound();
            layout.setContent(settings);
        });
        menu.helpButton.setOnAction(e -> {
            PlayPauseSound();
            layout.setContent(help);
        });
        menu.exitButton.setOnAction(e -> {
            PlayPauseSound();
            System.exit(0);
        });

//<<<<<<< HEAD
        settings.mangijaPilt1.setOnAction(e -> {
            game.setUserData("image/kasutaja04.png");

        });
//=======
            game.m2ngL2bi.addListener(e -> {
                if (game.m2ngL2bi.getValue()) {
                    int finalScore = 100 * game.getGoodScore();
                    // currentHighScores.edetabel.addNewScore(currentHighScores.getName(), finalScore); ***Kui edetabel korda saab saab selle siit sise tagasi kommida

                    System.out.println("Mäng sai läbi!"); //TESTING!
                    edetabel.addScore(finalScore);
                    layout.setContent(currentHighScores);
                }
//>>>>>>> fb4b99bb2f1648963b46d547039fd5fec1668238
            });

            settings.mangijaPilt2.setOnAction(e -> {
                game.setUserData("image/kasutaja01sv.png");
            });

            settings.mangijaPilt3.setOnAction(e -> {
                game.setUserData("image/kasutaja04.png");
            });

            settings.mangijaPilt4.setOnAction(e -> {
                game.setUserData("image/kasutaja01sv.png");
            });

            settings.mangijaPilt5.setOnAction(e -> {
                game.setUserData("image/kasutaja04.png");
            });

            settings.mangijaPilt6.setOnAction(e -> {
                game.setUserData("image/kasutaja01sv.png");
            });

            settings.mangijaPilt7.setOnAction(e -> {
                game.setUserData("image/kasutaja04.png");
            });

            settings.mangijaPilt8.setOnAction(e -> {
                game.setUserData("image/kasutaja01sv.png");
            });

            game.m2ngL2bi.addListener(e -> {
                if (game.m2ngL2bi.getValue()) {
                    int finalScore = 100 * game.getGoodScore();
                    // currentHighScores.edetabel.addNewScore(currentHighScores.getName(), finalScore); ***Kui edetabel korda saab saab selle siit sise tagasi kommida
                    layout.setContent(currentHighScores);
                    System.out.println("Mäng sai läbi!"); //TESTING!
                }
            });

            stage.setTitle("Püüa ainult tervislikku toitu!");
            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> {
                System.exit(0);
            });
        }

    }