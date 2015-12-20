import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import views.*;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        MenuView menu = new MenuView();
        GameView game = new GameView();
        HelpView currentHighScores = new HelpView(); //NB!  pandud help siia praegu peaks olema: HighScoreView currentHighScores = new HighScoreView();
        HelpView help = new HelpView();
        SettingsView settings = new SettingsView();
        Layout layout = new Layout(menu, false);
        layout.backMenuButton.setOnAction(e -> {
            layout.hideBackButton();
            layout.setContent(menu);
        });

        menu.startButton.setOnAction(e -> {
            layout.showBackButton();
            game.resetGame();
            layout.setContent(game);
        });
        menu.highscoresButton.setOnAction(e -> {
            layout.showBackButton();
            layout.setContent(currentHighScores);
        });
        menu.settingsButton.setOnAction(e -> {
            layout.showBackButton();
            layout.setContent(settings);
        });
        menu.helpButton.setOnAction(e -> {
            layout.showBackButton();
            layout.setContent(help);
        });
        menu.exitButton.setOnAction(e -> System.exit(0));

        game.m2ngL2bi.addListener(e-> {
            int finalScore = 100 * game.getGoodScore();
            // currentHighScores.edetabel.addNewScore(currentHighScores.getName(), finalScore);
            layout.setContent(currentHighScores);
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