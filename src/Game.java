import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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

import java.util.*;

public class Game extends Application
{
    Scene theMenu, theGame, theHighscores, theSettings, theHelp;
    int goodScore;
    int healthRemaining = 10; //elude hulk mis alguses kaasa antakse, kui see =0, siis mäng läbi!!
    int finalScore;
    public String name;
    public TextField nimeSisestamiseV2li;


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
        String user2KeyLeft = "Q";
        String user2KeyRight = "W";

        //-------------------------------------------------------------------------menu start

        Label menuPealkiri = new Label("Püüa ainult tervislikku toitu!");
        Button startButton = new Button("Start");
        Font theFont = Font.font("Consolas", FontWeight.LIGHT, 24);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);
        startButton.setFont(theFont);

        //Nupud menüüs
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

        Button backMenuEButton = new Button("Back to Menu");
        backMenuDButton.setFont(theFontSmall);

        //Nuppude paigutus
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

        backMenuAButton.setTranslateY(0);
        backMenuAButton.setTranslateX(0);

        backMenuCButton.setTranslateY(0);
        backMenuCButton.setTranslateX(0);

        backMenuDButton.setTranslateY(0);
        backMenuDButton.setTranslateX(0);

        backMenuEButton.setTranslateY(0);
        backMenuEButton.setTranslateX(0);



        Group rootMenu = new Group();
        rootMenu.getChildren().addAll(menuPealkiri, startButton, highscoresButton, settingsButton, helpButton, exitButton);
        theMenu = new Scene(rootMenu, 800, 600);

        edetabel = new HighScores();
        Group rootHighscore = new Group();
        Label settingsHighscore = new Label("Edetabel");
        settingsHighscore.setFont(theFontSmall);
        settingsHighscore.setTranslateX(50);
        settingsHighscore.setTranslateY(100);
        Text tekst = new Text();
        tekst.setTranslateX(50);
        tekst.setTranslateY(130);
        tekst.setText(edetabel.getHighScores());
        rootHighscore.getChildren().addAll(settingsHighscore, backMenuBButton,tekst);
        theHighscores = new Scene(rootHighscore, 800, 600);

        //Help
        Text juhend = new Text("1. Püüa toitu kasutades nooleklahve liikumiseks paremale või vasakule.\n2. Kasutaja " +
                "kaks saab kasutada klahve Q ja W.\n3. Püüa ainult tervislikku toitu, see annab sulle plusspunkte.\n4." +
                " Halva toidu püüdmine vähendab elusid. Mäng lõppeb, kui elud otsa saavad.\n\n\nMängu autorid: Martin Kask, Kersti Miller, Aet Udusaar 2015");
        juhend.setTranslateX(50);
        juhend.setTranslateY(150);
        juhend.setFont(theFontSmall);

        Group rootHelp = new Group();
        Label settingsHelp = new Label("Mängujuhis");
        settingsHelp.setFont(theFontSmall);
        settingsHelp.setTranslateX(50);
        settingsHelp.setTranslateY(100);
        rootHelp.getChildren().addAll(settingsHelp, backMenuDButton, juhend);
        theHelp = new Scene(rootHelp, 800, 600);

        //Settings
        Group rootSettings = new Group();
        Label settingsInfo1 = new Label("Vaheta mänguklahvide kombinatsiooni \n\nKasutaja 1");
            settingsInfo1.setTranslateX(100);
            settingsInfo1.setTranslateY(60);
            settingsInfo1.setFont(theFontSmall);
        Label settingsInfo2 = new Label("Vaheta mänguklahvide kombinatsiooni \n\nKasutaja 2");
            settingsInfo2.setTranslateX(475);
            settingsInfo2.setTranslateY(60);
            settingsInfo2.setFont(theFontSmall);
        rootSettings.getChildren().addAll(settingsInfo1, backMenuCButton, settingsInfo2);
        theSettings = new Scene(rootSettings, 800, 600);

        final ToggleGroup valikunupp = new ToggleGroup();

        RadioButton var1 = new RadioButton("<- ->");
        var1.setToggleGroup(valikunupp);
        var1.setUserData("<- ->");
        var1.setFont(theFontSmall);

        RadioButton var2 = new RadioButton("Q W");
        var2.setToggleGroup(valikunupp);
        var2.setUserData("Q W");
        var2.setFont(theFontSmall);

        RadioButton var3 = new RadioButton("<- ->");
        var3.setToggleGroup(valikunupp);
        var3.setUserData("<- ->");
        var3.setFont(theFontSmall);

        RadioButton var4 = new RadioButton("Q W");
        var4.setToggleGroup(valikunupp);
        var4.setUserData("Q W");
        var4.setFont(theFontSmall);

        valikunupp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
        });

        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        VBox vbox = new VBox();
        VBox vbox2 = new VBox();

        vbox.getChildren().add(var1);
        vbox.getChildren().add(var2);
        vbox.setSpacing(20);
        vbox.setTranslateX(70);
        vbox.setTranslateY(0);

        vbox2.getChildren().add(var3);
        vbox2.getChildren().add(var4);
        vbox2.setSpacing(20);
        vbox2.setTranslateX(70);
        vbox2.setTranslateY(50);

        hbox.getChildren().add(vbox);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox.setTranslateX(50);
        hbox.setTranslateY(100);

        hbox2.getChildren().add(vbox2);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox2.setTranslateX(450);
        hbox2.setTranslateY(100);

        ((Group) theSettings.getRoot()).getChildren().add(hbox);
        ((Group) theSettings.getRoot()).getChildren().add(hbox2);


        startButton.setOnAction(e -> {
            resetGame();
            stage.setScene(theGame);
        });
        backMenuAButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuBButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuCButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuDButton.setOnAction(e -> stage.setScene(theMenu));
        backMenuEButton.setOnAction(e -> stage.setScene(theMenu));
        highscoresButton.setOnAction(e -> stage.setScene(theHighscores));
        settingsButton.setOnAction(e -> stage.setScene(theSettings));
        helpButton.setOnAction(e -> stage.setScene(theHelp));
        exitButton.setOnAction(e -> System.exit(0));


        //------------------------------------------------------------------endmenu

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group rootGame = new Group();
        rootGame.getChildren().add(canvas);
        theGame = new Scene (rootGame);
        rootGame.getChildren().addAll(backMenuAButton);
        ArrayList<String> input = new ArrayList<String>();

        // implementeerime nupuvajutuste ära tundmiseks EventHandleri.
        theGame.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if ( !input.contains(code) )
                input.add(code);
        });
        theGame.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        theFont = Font.font( "Tahoma", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite kasutaja1Sprite = new User();
        kasutaja1Sprite.setImage("images/kasutaja01.png");
        kasutaja1Sprite.setPosition(200, 400);

        Sprite kasutaja2Sprite = new User();
        kasutaja2Sprite.setImage("images/kasutaja03.png");
        kasutaja2Sprite.setPosition(50, 400);

        // Mänguekraanil olevad kujutised ja pildid

        Sprite taevas =  new Sprite();
        taevas.setImage("images/taevas.png");
        taevas.setPosition(0, 0);

        Sprite maapinnas =  new Sprite();
        maapinnas.setImage("images/grass2.png");
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
                taevas.render( gc );

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //anname kasutajatele kiirust vastavalt vajutatud klahvidele
                kasutaja1Sprite.setVelocity(0,0);
                if (input.contains(user1KeyLeft) && kasutaja1Sprite.getPositionX()>-80 )
                    kasutaja1Sprite.addVelocity(-150,0);
                if (input.contains(user1KeyRight) && kasutaja1Sprite.getPositionX()<750)
                    kasutaja1Sprite.addVelocity(150,0);

                kasutaja2Sprite.setVelocity(0,0);
                if (input.contains(user2KeyLeft) && kasutaja2Sprite.getPositionX()>-80)
                    kasutaja2Sprite.addVelocity(-150,0);
                if (input.contains(user2KeyRight)&& kasutaja2Sprite.getPositionX()<720)
                    kasutaja2Sprite.addVelocity(150,0);

                //liigutame kasutajaid
                kasutaja2Sprite.update(elapsedTime);
                kasutaja1Sprite.update(elapsedTime);

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food food = foodIter.next();
                    food.render(gc);
                    if ( kasutaja1Sprite.intersects(food)||kasutaja2Sprite.intersects(food) )
                    {
                        if (food.good)
                            goodScore++; //kui toit oli tervislik suurendame skoori
                        else
                            healthRemaining--; //kui toit oli paha, vähendame elusid
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
                kasutaja2Sprite.render( gc );



                // Näita halva skoori suurust
                gc.setFill( Color.RED );
                gc.setStroke( Color.DARKBLUE );
                String pointsText = "Health Remaining:" + (100 * healthRemaining);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "GoodScore:" + (100 * goodScore);
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                gc.fillText( goodPointsText, 360, 72 );
                gc.strokeText( goodPointsText, 360, 72 );


                if (healthRemaining < 1) {
                    this.stop();
                    finalScore = 100 * goodScore;

                    String kasutajaPooltSisestatudNimi = getName();
                    edetabel.addNewScore(kasutajaPooltSisestatudNimi, finalScore);

                    System.out.println(edetabel.getHighScores());
                    resetGame();
                    stage.setScene(theGame);
                }
            }
        };


        //tekitab ajastaja, mis loobib iga 0,8s tagant toitu alla
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

        stage.setOnCloseRequest(e -> {
            animationTimer.stop();
            timer.cancel();
        });

        //tekitab akna 1, sellest alustame näitamist
        stage.setTitle("Püüa ainult tervislikku toitu!");
        stage.setScene(theMenu);
        menuPealkiri.setFont(theFont);
        stage.show();
    }

    public void resetGame() {
        goodScore = 0;
        healthRemaining = 10;

    }

    public String getName() {
        Dialog<Game> dialog = new Dialog<>();
        Label label = new Label("Siseta oma nimi: ");
        nimeSisestamiseV2li = new TextField();
        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(nimeSisestamiseV2li, 2, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType nimeSisestamisNupp = new ButtonType("OK");

        dialog.getDialogPane().getButtonTypes().add(nimeSisestamisNupp);
        dialog.show();
        return nimeSisestamiseV2li.getText();
    }


}