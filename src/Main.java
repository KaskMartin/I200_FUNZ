import com.sun.javafx.property.adapter.PropertyDescriptor;
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
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setResizable(false);

        MenuView menu = new MenuView();
        GameView game = new GameView();
        HelpView currentHighScores = new HelpView(); //NB!  pandud help siia praegu peaks olema: HighScoreView currentHighScores = new HighScoreView();
        HelpView help = new HelpView();
        SettingsView settings = new SettingsView();
        Layout layout = new Layout(menu);
        layout.hideBackButton();
        layout.backMenuButton.setOnAction(e -> {
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
            game.resetGame();
            layout.setContent(game);
        });
        menu.highscoresButton.setOnAction(e -> {
            layout.setContent(currentHighScores);
        });
        menu.settingsButton.setOnAction(e -> {
            layout.setContent(settings);
        });
        menu.helpButton.setOnAction(e -> {
            layout.setContent(help);
        });
        menu.exitButton.setOnAction(e -> System.exit(0));

        game.m2ngL2bi.addListener(e-> {
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