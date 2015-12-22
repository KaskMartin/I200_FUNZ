package views;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.gms.Food;
import views.gms.LongValue;
import views.gms.Sprite;
import views.gms.User;
import java.util.*;

/**
 * Created by martin on 13.12.15.
 */
public class GameView extends Pane {
    private int goodScore;
    private int healthRemaining = 4; //elude hulk mis alguses kaasa antakse, kui see =0, siis mäng läbi!!
    private int maksimumFoodAllowed = 25;
    public AnimationTimer animationTimer;
    User kasutaja1, kasutaja2;

    public SimpleBooleanProperty m2ngL2bi = new SimpleBooleanProperty();
    ArrayList<Food> foodList = new ArrayList<Food>();
    public ArrayList<String> input = new ArrayList<String>();

    public int getGoodScore () {
        return goodScore;
    }

    public GameView() {
        this.setHeight(600);
        this.setWidth(800);

        //kasutajad, loome kasutades collision box muutujate modifitseerimisega konstruktorit
        kasutaja1 = new User(3, 21, 75, 21);
        kasutaja2 = new User(79, 18, 81, 26);
        kasutaja1.setImage("images/kasutaja01.png");
        kasutaja1.setPosition(200, 365);

        kasutaja1.setMoveLeft("LEFT");
        kasutaja1.setMoveRight("RIGHT");

        kasutaja2.setImage("images/kasutaja03.png");
        kasutaja2.setPosition(600, 365);

        kasutaja2.setMoveLeft("Q");
        kasutaja2.setMoveRight("W");
        this.requestFocus();

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

        Font theFont = Font.font( "Tahoma", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setLineWidth(1);


        // Mänguekraanil olevad kujutised ja pildid
        Sprite taevas =  new Sprite();
        taevas.setImage("images/taevas.png");
        taevas.setPosition(0, 0);

        Sprite maapindSprite =  new Sprite();
        maapindSprite.setImage("images/grass2.png");
        maapindSprite.setPosition(0, 540);
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
                taevas.render( gc );

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //anname kasutajatele kiirust vastavalt vajutatud klahvidele
                kasutaja1.setVelocity(0,0);
                if (input.contains(kasutaja1.getMoveLeft()) && kasutaja1.getPositionX()>-80 )
                    kasutaja1.addVelocity(-150,0);
                if (input.contains(kasutaja1.getMoveRight()) && kasutaja1.getPositionX()<750)
                    kasutaja1.addVelocity(150,0);

                kasutaja2.setVelocity(0,0);
                if (input.contains(kasutaja2.getMoveLeft()) && kasutaja2.getPositionX()>-80)
                    kasutaja2.addVelocity(-150,0);
                if (input.contains(kasutaja2.getMoveRight())&& kasutaja2.getPositionX()<720)
                    kasutaja2.addVelocity(150,0);

                //liigutame kasutajaid
                kasutaja2.update(elapsedTime);
                kasutaja1.update(elapsedTime);

                // kokkupõrgete avastamine ja nendele vastavad tegevused
                Iterator<Food> foodIter = foodList.iterator();
                while ( foodIter.hasNext() )
                {
                    Food foodSprite = foodIter.next();
                    foodSprite.render(gc);
                    if ( kasutaja1.intersects(foodSprite)||kasutaja2.intersects(foodSprite) )
                    {
                        if (foodSprite.good)
                            goodScore++; //kui toit oli tervislik suurendame skoori
                        else
                            healthRemaining--; //kui toit oli paha, vähendame elusid
                        foodIter.remove(); //viska toit minema
                    }
                    else if ( foodSprite.intersects(maapindSprite)) {
                        foodIter.remove();
                    }
                    foodSprite.update(elapsedTime);
                }

                // render

                maapindSprite.render(gc);
                kasutaja1.render(gc);
                kasutaja2.render(gc);



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
                    m2ngL2bi.set(true);
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
        healthRemaining = 4;
        kasutaja1.setPosition(200, 365);
        kasutaja2.setPosition(600, 365);
        animationTimer.start();
        m2ngL2bi.set(false);
    }
}
