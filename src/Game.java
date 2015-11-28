import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Application
{
    Scene theMenu, theGame, theHighscores, theSettings;
    int badScore, goodScore;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Püüa ainult tervislikku toitu!");
        String user1KeyLeft = "LEFT";
        String user1KeyRight = "RIGHT";

        //-------------------------------------------------------------------------menu start
        Label menuPealkiri = new Label("Püüa ainult tervislikku toitu!");
        Button startButton = new Button("Start");
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        Font theFontSmall = Font.font("Helvetica", FontWeight.NORMAL, 12);
        startButton.setFont(theFont);
        Button highscoresButton = new Button("Highscores");
        highscoresButton.setFont(theFont);
        Button settingsButton = new Button("Settings");
        settingsButton.setFont(theFont);
        Button exitButton = new Button("Exit");
        exitButton.setFont(theFont);
        Button backMenuAButton = new Button("Back to Menu");
        backMenuAButton.setFont(theFontSmall);
        Button backMenuBButton = new Button("Back to Menu");
        backMenuBButton.setFont(theFontSmall);
        Button backMenuCButton = new Button("Back to Menu");
        backMenuCButton.setFont(theFontSmall);

        //Layout
        startButton.setTranslateY(100);
        startButton.setTranslateX(300);
        highscoresButton.setTranslateY(200);
        highscoresButton.setTranslateX(300);
        settingsButton.setTranslateY(300);
        settingsButton.setTranslateX(300);
        exitButton.setTranslateY(400);
        exitButton.setTranslateX(300);
        backMenuAButton.setTranslateY(0);
        backMenuAButton.setTranslateX(0);
        backMenuCButton.setTranslateY(120);
        backMenuCButton.setTranslateX(0);

        Group rootMenu = new Group();
        rootMenu.getChildren().addAll(menuPealkiri, startButton, highscoresButton, settingsButton, exitButton);
        theMenu = new Scene(rootMenu, 800, 600);

        Group rootHighscore = new Group();
        rootHighscore.getChildren().addAll(backMenuBButton);
        theHighscores = new Scene (rootHighscore, 800, 600);

        Group rootSettings = new Group();
        rootSettings.getChildren().addAll(backMenuCButton);
        theSettings = new Scene(rootSettings, 800, 600);
        //----------------------------------------------------------------start settingnupp
        final ToggleGroup group = new ToggleGroup();

        RadioButton var1 = new RadioButton("<- ->");
        var1.setToggleGroup(group);
        var1.setUserData("<- ->");

        RadioButton var2 = new RadioButton("Q W");
        var2.setToggleGroup(group);
        var2.setUserData("Q W");

        RadioButton var3 = new RadioButton("Muu kombinatsioon, blabla");
        var3.setToggleGroup(group);
        var3.setUserData("Muu");

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
        });

        HBox hbox = new HBox();
        VBox vbox = new VBox();

        vbox.getChildren().add(var1);
        vbox.getChildren().add(var2);
        vbox.getChildren().add(var3);
        vbox.setSpacing(10);

        hbox.getChildren().add(vbox);
        hbox.setSpacing(50);
        hbox.setPadding(new Insets(20, 10, 10, 20));

        ((Group) theSettings.getRoot()).getChildren().add(hbox);
        //------------------------------------------------------------------end settingnupp
        startButton.setOnAction(e -> stage.setScene(theGame));
        backMenuAButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuBButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuCButton.setOnAction(e -> stage.setScene(theMenu));
        highscoresButton.setOnAction(e -> stage.setScene(theHighscores));
        settingsButton.setOnAction(e -> stage.setScene(theSettings));
        exitButton.setOnAction(e -> System.exit(0));

        //------------------------------------------------------------------endmenu

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group rootGame = new Group();
        rootGame.getChildren().add( canvas );
        theGame = new Scene (rootGame);
        rootGame.getChildren().addAll(backMenuAButton);
        ArrayList<String> input = new ArrayList<String>();

        // implementeerime nupuvajutuste ära tundmiseks EventHandleri.
        /**
        // theGame.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });
        */

        theGame.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if ( !input.contains(code) )
                input.add(code);
        });
        /**
        theGame.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
        */

        theGame.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite basket = new Sprite();
        basket.setImage("images/basket.png");
        basket.setPosition(50, 400);
        Sprite maapinnas =  new Sprite();
        maapinnas.setImage("images/maapind.png");
        maapinnas.setPosition(0, 550);
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        // int maksimumFoodAllowed = 10;


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                ArrayList<Food> foodList = new ArrayList<Food>();

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //püüdmiskorvi liigutamine
                basket.setVelocity(0,0);
                if (input.contains(user1KeyLeft))
                    basket.addVelocity(-150,0);
                if (input.contains(user1KeyRight))
                    basket.addVelocity(150,0);

                basket.update(elapsedTime);

             //   if (foodList.size() < maksimumFoodAllowed)
             //       foodList.add(new Food());

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        foodList.add(new Food());
                    }
                };

                timer.scheduleAtFixedRate(task, 0, 200);

                // kokkupõrgete avastamine
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food food = foodIter.next();
                    if ( basket.intersects(food) )
                    {
                        if (food.good)
                            goodScore++;
                        else
                            badScore++;
                        foodIter.remove(); //viska toit minema
                    }
                    if ( maapinnas.intersects(food)) {
                        foodIter.remove();
                    }
                    food.update(elapsedTime);
                }

                // render
                gc.clearRect(0, 0, 800, 600);
                maapinnas.render( gc );
                basket.render( gc );

                for ( Food food : foodList )
                    food.render(gc);

                // Näita halva skoori suurust
                gc.setFill( Color.RED );
                gc.setStroke( Color.DARKBLUE );
                String pointsText = "BadScore:" + (100 * badScore);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "GoodScore:" + (100 * goodScore);
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                gc.fillText( goodPointsText, 360, 72 );
                gc.strokeText( goodPointsText, 360, 72 );

            }
        }.start();

        //tekitab akna 1, sellest alustame näitamist
        stage.setTitle("Püüa ainult tervislikku toitu!");
        stage.setScene(theMenu);
        menuPealkiri.setFont(theFont);
        stage.show();
    }
}