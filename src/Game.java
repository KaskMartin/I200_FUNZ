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
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Application
{
    Scene theMenu, theGame, theHighscores, theSettings, theHelp;
    int badScore, goodScore;

    public static void main(String[] args)
    {
        launch(args);
    }

    public static Kasutaja kasutaja; //Kasutajaseaded - Hoiab kasutaja seadeid
    public static HighScores edetabel; //Edetabel - väljastab tulemused


    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Püüa ainult tervislikku toitu!");
        String user1KeyLeft = "LEFT";
        String user1KeyRight = "RIGHT";
        String user2KeyLeft = "A";
        String user2KeyRight = "D";

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

        Button helpButton = new Button("Help");
        helpButton.setFont(theFont);

        Button exitButton = new Button("Exit");
        exitButton.setFont(theFont);

        Button backMenuAButton = new Button("Back to Menu");
        backMenuAButton.setFont(theFontSmall);

        Button backMenuBButton = new Button("Back to Menu");
        backMenuBButton.setFont(theFontSmall);

        Button backMenuCButton = new Button("Back to Menu");
        backMenuCButton.setFont(theFontSmall);

        Button backMenuDButton = new Button("Back to Menu");
        backMenuDButton.setFont(theFontSmall);

        //Layout
        startButton.setTranslateY(100);
        startButton.setTranslateX(300);

        highscoresButton.setTranslateY(200);
        highscoresButton.setTranslateX(300);

        settingsButton.setTranslateY(300);
        settingsButton.setTranslateX(300);

        helpButton.setTranslateY(400);
        helpButton.setTranslateX(300);

        exitButton.setTranslateY(500);
        exitButton.setTranslateX(300);

        backMenuAButton.setTranslateY(0);
        backMenuAButton.setTranslateX(0);

        backMenuCButton.setTranslateY(120);
        backMenuCButton.setTranslateX(0);

        backMenuDButton.setTranslateY(0);
        backMenuDButton.setTranslateX(0);

        Group rootMenu = new Group();
        rootMenu.getChildren().addAll(menuPealkiri, startButton, highscoresButton, settingsButton, helpButton, exitButton);
        theMenu = new Scene(rootMenu, 800, 600);

        Group rootHighscore = new Group();

        rootHighscore.getChildren().addAll(backMenuBButton);
        theHighscores = new Scene (rootHighscore, 800, 600);


        Group rootSettings = new Group();
        Label settingsInfo = new Label("Vaheta mänguklahvide kombinatsiooni");
        rootSettings.getChildren().addAll(settingsInfo, backMenuCButton);
        theSettings = new Scene(rootSettings, 800, 600);


        Group rootHelp = new Group();
        Label settingsHelp = new Label("Mängujuhis");
        rootHelp.getChildren().addAll(settingsHelp, backMenuDButton);
        theHelp = new Scene(rootHelp, 800, 600);
        //----------------------------------------------------------------start settingnupp
        final ToggleGroup group = new ToggleGroup();

        RadioButton var1 = new RadioButton("<- ->");
            var1.setToggleGroup(group);
            var1.setUserData("<- ->");

        //___________________________________________________edetabel algus

        //try {
            //FileOutputStream edetabel = new FileOutputStream("Edetabel");
            //object outputstream
            //write object (this);
            //close();


        //_________________________________________________


        RadioButton var2 = new RadioButton("A D");
            var2.setToggleGroup(group);
            var2.setUserData("A A");

        //RadioButton var3 = new RadioButton("Muu kombinatsioon, blabla");
            //var3.setToggleGroup(group);
            //var3.setUserData("Muu");

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
        });

        HBox hbox = new HBox();
        VBox vbox = new VBox();

        vbox.getChildren().add(var1);
        vbox.getChildren().add(var2);
        //vbox.getChildren().add(var3);
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
        backMenuDButton.setOnAction(e -> stage.setScene(theMenu));
        highscoresButton.setOnAction(e -> stage.setScene(theHighscores));
        settingsButton.setOnAction(e -> stage.setScene(theSettings));
        helpButton.setOnAction(e -> stage.setScene(theHelp));
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

        Sprite kasutaja1Sprite = new Sprite();
        kasutaja1Sprite.setImage("images/basket.png");
        kasutaja1Sprite.setPosition(50, 400);
        Sprite maapinnas =  new Sprite();
        maapinnas.setImage("images/maapind.png");
        maapinnas.setPosition(0, 550);
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        int maksimumFoodAllowed = 25;
        ArrayList<Food> foodList = new ArrayList<Food>();

        AnimationTimer animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.

                gc.clearRect(0, 0, 800, 600);

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //püüdmis korvi liigutamine
                kasutaja1Sprite.setVelocity(0,0);
                if (input.contains(user1KeyLeft))
                    kasutaja1Sprite.addVelocity(-150,0);
                if (input.contains(user1KeyRight))
                    kasutaja1Sprite.addVelocity(150,0);

                kasutaja1Sprite.update(elapsedTime);

                // kokkupõrgete avastamine
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food food = foodIter.next();
                    food.render(gc);
                    if ( kasutaja1Sprite.intersects(food) )
                    {
                        if (food.good)
                            goodScore++;
                        else
                            badScore++;
                        foodIter.remove(); //viska toit minema
                    }
                    else if ( food.intersects(maapinnas)) {
                        foodIter.remove();
                    }
                    food.update(elapsedTime);

                }

                // render

                maapinnas.render( gc );
                kasutaja1Sprite.render( gc );


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
        };

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (foodList.size() < maksimumFoodAllowed)
                    foodList.add(new Food());
            }
        };

        timer.scheduleAtFixedRate(task, 0, 800);

        animationTimer.start();

        if (badScore > 10) {
            animationTimer.stop();
            // HighScores.newHighScore(goodScore);
        }

        //tekitab akna 1, sellest alustame näitamist
        stage.setTitle("Püüa ainult tervislikku toitu!");
        stage.setScene(theMenu);
        menuPealkiri.setFont(theFont);
        stage.show();
    }
}