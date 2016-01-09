package views;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.*;

import java.io.File;
import java.util.*;

/**
 * Created by martin on 13.12.15.
 */
public class GameView extends Pane {

    private int usersCombinedScore;
    private final int healthAtStart = 5; //elude hulk mis alguses kaasa antakse,MUUDA KUI VAJA!
    private int healthRemaining = healthAtStart; //elude hulk mis järgi on, kui see =0, siis mäng läbi!!
    private int maximumFoodAllowed = 50;
    private int maximumPotionAllowed = 2;
    public AnimationTimer animationTimer;
    public User user1 = new User(0, 38, 75, 16);
    public User user2 = new User(63, 35, 101, 17);
    public Font theFont = Font.font( "Tahoma", FontWeight.BOLD, 24 );
    private Image healthoMeter = new Image("images/techno-heart_5.png");

    public void setHealthoMeter() {
        switch (healthRemaining) {
        case 0:
            this.healthoMeter = new Image("images/techno-heart_0.png");
            break;
        case 1:
            this.healthoMeter = new Image("images/techno-heart_1.png");
            break;
        case 2:
            this.healthoMeter = new Image("images/techno-heart_2.png");
            break;
        case 3:
            this.healthoMeter = new Image("images/techno-heart_3.png");
            break;
        case 4:
            this.healthoMeter = new Image("images/techno-heart_4.png");
            break;
        case 5:
            this.healthoMeter = new Image("images/techno-heart_5.png");
            break;
        default:
            System.out.println("Invalid Switch Input for healthLeft method");
            break;
        }
    }

    public SimpleBooleanProperty gameOver = new SimpleBooleanProperty(); //Mängu lõppu jälgiv boolean
    ArrayList<Food> foodList = new ArrayList<Food>(); //Alla sadava toidu konteiner
    ArrayList<Potion> potionsList = new ArrayList<Potion>(); //Alla sadava rohupurgi konteiner
    public ArrayList<String> input = new ArrayList<String>(); //klahvivajutuste konteiner

    public int getUsersCombinedScore() {
        return usersCombinedScore;
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
        TimerTask foodThrowingTask= new TimerTask() {
            @Override
            public void run() {
                if (foodList.size() < maximumFoodAllowed)
                    foodList.add(new Food());
            }
        };

        TimerTask healthPotionFallingTask = new TimerTask() {
            @Override
            public void run() {
                if (potionsList.size() < maximumPotionAllowed)
                    potionsList.add(new Potion());
            }
        };

        timer.scheduleAtFixedRate(foodThrowingTask, 0, 600);
        timer.scheduleAtFixedRate(healthPotionFallingTask, 5000, 10000);

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

                grassSprite.render(gc);
                //liigutame kasutajaid
                user2.update(elapsedTime);
                user1.update(elapsedTime);
                user1.render(gc);
                user2.render(gc);

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Potion> potionIter = potionsList.iterator();
                while ( potionIter.hasNext() ) {
                    Potion potionSprite = potionIter.next();
                    potionSprite.render(gc);
                    if ( user1.intersects(potionSprite)|| user2.intersects(potionSprite) )
                    {
                        if (healthRemaining < healthAtStart) {
                            healthRemaining++;
                            System.out.println("Yey, you got a brand new heart");
                        }
                        setHealthoMeter();
                        potionIter.remove();
                    }
                    else if ( potionSprite.intersects(grassSprite)) {
                        potionIter.remove();
                    }
                    potionSprite.update(elapsedTime);
                }

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food foodSprite = foodIter.next();
                    foodSprite.render(gc);
                    if ( user1.intersects(foodSprite)|| user2.intersects(foodSprite) )
                    {
                        if (foodSprite.good) {
                            usersCombinedScore += 10; //kui toit oli tervislik suurendame skoori
                            PlaySound("src/sounds/213424__taira-komori__short-pickup03.mp3");
                        }
                        else {
                            healthRemaining--;//kui toit oli paha, vähendame elusid
                            setHealthoMeter();//uuenda elude näitamise mõõdikut
                            PlaySound("src/sounds/249615__vincentm400__confirm.mp3");
                        }
                        foodIter.remove(); //viska toit minema
                    }
                    else if ( foodSprite.intersects(grassSprite)) {
                        foodIter.remove();
                        if (foodSprite.good) {
                            usersCombinedScore--;
                            PlaySound("src/sounds/149899__animationisaac__box-crash.mp3");
                        }
                    }
                    foodSprite.update(elapsedTime);
                }

                // Näita elusid, mis järgi on
                gc.drawImage(healthoMeter, 360, 36);

             // gc.setFill( Color.RED );
             // gc.setStroke( Color.DARKBLUE );
             // String pointsText = "Health Remaining:" + (healthRemaining);
             // gc.fillText( pointsText, 360, 36 );
             // gc.strokeText( pointsText, 360, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "Score:" + (usersCombinedScore);
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                gc.fillText( goodPointsText, 360, 36 );
                gc.strokeText( goodPointsText, 360, 36 );

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
        potionsList = new ArrayList<Potion>();
        usersCombinedScore = 0;
        healthRemaining = healthAtStart;
        setHealthoMeter();
        user1.setPosition(200, 365);
        user2.setPosition(600, 365);
        user1.setVelocity(0, 0);
        user2.setVelocity(0, 0);
        animationTimer.start();
        gameOver.set(false);
        System.out.println("Game has been reset");
    }
}
