package views;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.Food;
import lib.LongValue;
import lib.Sprite;
import lib.User;
import java.io.File;
import java.util.*;

/**
 * Created by martin on 13.12.15.
 */
public class GameView extends Pane {

    private int goodScore;
    private final int healthAtStart = 4; //elude hulk mis alguses kaasa antakse,MUUDA KUI VAJA!
    private int healthRemaining = healthAtStart; //elude hulk mis järgi on, kui see =0, siis mäng läbi!!
    private int maksimumFoodAllowed = 25;
    public AnimationTimer animationTimer;
    public User user1 = new User(0, 38, 75, 16);
    public User user2 = new User(63, 35, 101, 17);
    public Font theFont = Font.font( "Tahoma", FontWeight.BOLD, 24 );

    public SimpleBooleanProperty gameOver = new SimpleBooleanProperty(); //Mängu lõppu jälgiv boolean
    ArrayList<Food> foodList = new ArrayList<Food>(); //Alla sadava toidu konteiner
    public ArrayList<String> input = new ArrayList<String>(); //klahvivajutuste konteiner

    public int getGoodScore () {
        return goodScore;
    }

    //Helide haldamine
    private boolean soundIsOn = true;

    public void toggleSound () {
        soundIsOn = (!soundIsOn);
    }

    public void PlaySound (String soundFileName) {
        if (soundIsOn) {
            Media sound = new Media(new File(soundFileName).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            }
    }

    //Konstruktor mängu objekti loomiseks
    public GameView() {
        this.setHeight(600);
        this.setWidth(800);

        //kasutajad, loome kasutades collision box muutujate modifitseerimisega konstruktorit
        user1.setImage("images/kasutaja1sinine.png");
        user1.setPosition(200, 355);
        user1.setMoveLeft("Q");
        user1.setMoveRight("W");

        user2.setImage("images/kasutaja2punane.png");
        user2.setPosition(600, 365);
        user2.setMoveLeft("LEFT");
        user2.setMoveRight("RIGHT");

        this.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!input.contains(code))
                input.add(code);
        });

        this.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);


        gc.setFont( theFont );
        gc.setLineWidth(1);


        // Mänguekraanil olevad kujutised ja pildid
        Sprite skyBackgroundSprite =  new Sprite();
        skyBackgroundSprite.setImage("images/taevas.png");
        skyBackgroundSprite.setPosition(0, 0);

        Sprite grassSprite =  new Sprite();
        grassSprite.setImage("images/grass2.png");
        grassSprite.setPosition(0, 540);
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        //See on toiduloopija, mis lisav toitu meie foodList-i
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (foodList.size() < maksimumFoodAllowed)
                    foodList.add(new Food());
            }
        }, 0, 800);

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                gc.clearRect(0, 0, 800, 600);
                skyBackgroundSprite.render(gc);

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //anname kasutajatele kiirust vastavalt vajutatud klahvidele
                user1.setVelocity(0,0);
                if (input.contains(user1.getMoveLeft()) && user1.getPositionX()>-80 )
                    user1.addVelocity(-150,0);
                if (input.contains(user1.getMoveRight()) && user1.getPositionX()<750)
                    user1.addVelocity(150,0);

                user2.setVelocity(0,0);
                if (input.contains(user2.getMoveLeft()) && user2.getPositionX()>-80)
                    user2.addVelocity(-150,0);
                if (input.contains(user2.getMoveRight())&& user2.getPositionX()<720)
                    user2.addVelocity(150,0);

                //liigutame kasutajaid
                user2.update(elapsedTime);
                user1.update(elapsedTime);

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food foodSprite = foodIter.next();
                    foodSprite.render(gc);
                    if ( user1.intersects(foodSprite)|| user2.intersects(foodSprite) )
                    {
                        if (foodSprite.good) {
                            goodScore++; //kui toit oli tervislik suurendame skoori
                            PlaySound("src/sounds/213424__taira-komori__short-pickup03.mp3");
                        }
                        else {
                            healthRemaining--; //kui toit oli paha, vähendame elusid
                            PlaySound("src/sounds/249615__vincentm400__confirm.mp3");
                        }
                        foodIter.remove(); //viska toit minema
                    }
                    else if ( foodSprite.intersects(grassSprite)) {
                        foodIter.remove();
                        if (foodSprite.good) {PlaySound("src/sounds/149899__animationisaac__box-crash.mp3");}
                    }
                    foodSprite.update(elapsedTime);
                }

                // render

                grassSprite.render(gc);
                user1.render(gc);
                user2.render(gc);

                // Näita halva skoori suurust
                gc.setFill( Color.RED );
                gc.setStroke( Color.DARKBLUE );
                String pointsText = "Health Remaining:" + (healthRemaining);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "GoodScore:" + (100 * goodScore);
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                gc.fillText( goodPointsText, 360, 72 );
                gc.strokeText( goodPointsText, 360, 72 );

                /** MEGA TÄHTIS!!! Lõpetab mängu kui elud otsa saavad. Muudab m2ngLbi SimpleBooleanProperty variably, millele
                 * on ehitatud kuulaja Main meetodi jaoks. Sealt käivitub HighScoreide kuvamine
                 */

                if (healthRemaining < 1) {
                    stopGame();
                    gameOver.set(true);
                }
            }
        };

        //tekitab ajastaja, mis loobib iga 0,8s tagant toitu alla
    }

    public void stopGame() {
            animationTimer.stop();
    }

    public void resetGame() {
        foodList = new ArrayList<Food>();
        goodScore = 0;
        healthRemaining = healthAtStart;
        user1.setPosition(200, 365);
        user2.setPosition(600, 365);
        animationTimer.start();
        gameOver.set(false);
    }
}
