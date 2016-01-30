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
    private int startingLevel = 0;
    private int currentLevel = 0;
    public AnimationTimer animationTimer;
    public Timer timer = new Timer();
    public User user1 = new User(0, 38, 75, 16);
    public User user2 = new User(63, 35, 101, 17);
    public Font theFont = Font.font( "Tahoma", FontWeight.BOLD, 24 );
    private Image healthoMeter = new Image("images/techno-heart_5.png");
    private double fallingStuffSpeed = 90;
    private double level1Threshold = 75;
    private double level1Speed = 95;
    private double level2Threshold = 150;
    private double level2Speed = 100;
    private double level3Threshold = 250;
    private double level3Speed = 105;
    private double level4Threshold = 375;
    private double level4Speed = 110;
    private double level5Threshold = 500;
    private double level5Speed = 115;
    private double level6Threshold = 600;
    private double level6Speed = 120;
    private double level7Threshold = 725;
    private double level7Speed = 125;
    private double level8Threshold = 850;
    private double level8Speed = 130;
    private double level9Threshold = 925;
    private double level9Speed = 135;
    private double level10Threshold = 1100;
    private double level10Speed = 140;
    private double nextLevelAt = level1Threshold;

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

    class FoodThrowingTask extends TimerTask {
        @Override
        public void run() {
            if (foodList.size() < maximumFoodAllowed)
                foodList.add(new Food());
        }
    }

    class HealthPotionFallingTask extends TimerTask {
        @Override
        public void run() {
            if (potionsList.size() < maximumPotionAllowed)
                potionsList.add(new Potion());
        }
    }

    //Konstruktor mängu objekti loomiseks
    public GameView() {
        this.setHeight(600);
        this.setWidth(800);
        this.currentLevel = startingLevel;

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
        //timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 600);
        //timer.scheduleAtFixedRate(new HealthPotionFallingTask(), 5000, 10000);

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

                //LEVELITE haldus

                if (currentLevel == 0 && usersCombinedScore > level1Threshold){
                    currentLevel++;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 1000);
                    fallingStuffSpeed = level1Speed;
                    nextLevelAt = level2Threshold;
                }
                else if (currentLevel == 1 && usersCombinedScore > level2Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level2Speed;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 1000);
                    nextLevelAt = level3Threshold;
                }
                else if (currentLevel == 2 && usersCombinedScore > level3Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level3Speed;
                    nextLevelAt = level4Threshold;
                }
                else if (currentLevel == 3 && usersCombinedScore > level4Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level4Speed;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 1000);
                    nextLevelAt = level5Threshold;
                }
                else if (currentLevel == 4 && usersCombinedScore > level5Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level5Speed;
                    nextLevelAt = level6Threshold;
                    timer.scheduleAtFixedRate(new HealthPotionFallingTask(), 0, 10000);
                }
                else if (currentLevel == 5 && usersCombinedScore > level6Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level6Speed;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 1000);
                    nextLevelAt = level7Threshold;
                }
                else if (currentLevel == 6 && usersCombinedScore > level7Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level7Speed;
                    nextLevelAt = level8Threshold;
                }
                else if (currentLevel == 7 && usersCombinedScore > level8Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level8Speed;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 1000);
                    nextLevelAt = level9Threshold;
                    timer.scheduleAtFixedRate(new HealthPotionFallingTask(), 0, 10000);
                }
                else if (currentLevel == 8 && usersCombinedScore > level9Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level9Speed;
                    nextLevelAt = level10Threshold;
                }
                else if (currentLevel == 9 && usersCombinedScore > level10Threshold){
                    currentLevel++;
                    fallingStuffSpeed = level10Speed;
                    timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 500);
                    timer.scheduleAtFixedRate(new HealthPotionFallingTask(), 0, 5000);
                    nextLevelAt = 9999;
                }

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Potion> potionIter = potionsList.iterator();
                while ( potionIter.hasNext() ) {
                    try {
                        Potion potionSprite = potionIter.next();
                        potionSprite.fallingStuffSpeed = fallingStuffSpeed;
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
                    catch (ConcurrentModificationException e) {
                        System.out.println("Mitu asja sai korraga otsa. Pole erilist probleemi.");
                        break;
                    }
                }

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    try {Food foodSprite = foodIter.next();
                        foodSprite.render(gc);
                        foodSprite.fallingStuffSpeed = fallingStuffSpeed;
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
                    catch (ConcurrentModificationException e) {
                        System.out.println("Mitu asja sai korraga otsa. Pole erilist probleemi.");
                        break;
                    }
                }

                // Näita elusid, mis järgi on
                gc.drawImage(healthoMeter,140, 8);

                //Näita levelit ja skoori mis vaja järgmiseks
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                String pointsText = "TASE: " + currentLevel + " (järgmine: " + nextLevelAt + " punkti)";
                gc.fillText( pointsText, 260, 36 );
                gc.strokeText( pointsText, 260, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "PUNKTE:" + (usersCombinedScore);
                gc.setFill( Color.PURPLE );
                gc.setStroke( Color.DARKBLUE );
                gc.fillText( goodPointsText, 400, 62 );
                gc.strokeText( goodPointsText, 400, 62 );

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
            input = new ArrayList<String>();
    }

    public void resetGame() {
        try {
            timer.cancel();
            }
        catch (IllegalStateException e){}
        timer = new Timer();
        timer.scheduleAtFixedRate(new FoodThrowingTask(), 0, 600);
        timer.scheduleAtFixedRate(new HealthPotionFallingTask(), 5000, 10000);
        foodList = new ArrayList<Food>();
        potionsList = new ArrayList<Potion>();
        usersCombinedScore = 0;
        fallingStuffSpeed = 90;
        currentLevel = startingLevel;
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
