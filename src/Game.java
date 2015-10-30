import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    //Loo uus lava
    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "Püüa ainult tervislikku toitu!" );
        Group root = new Group();
        // Scene theMenu = new Scene (root);
        // theStage.setScene( theMenu )  <- v6ta komment 2ra kui menyy valmis
        // SIIA TULEB MENYY jama. Nupp "Start Game" = theStage.setScene( theGame )

        Scene theGame = new Scene( root );

        theStage.setScene( theGame ); // kustuta see siit ära kui menyy valmis

        Canvas canvas = new Canvas( 800, 600);
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<String>();

        // implementeerime nupuvajutuste ära tundmiseks EventHandleri.
        theGame.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        theGame.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite basket = new Sprite();
        basket.setImage("basket.png");
        basket.setPosition(50, 400);

        ArrayList<Sprite> carrotList = new ArrayList<Sprite>();
        ArrayList<Sprite> burgerList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++)
        {
            Sprite carrot = new Sprite();
            carrot.setImage("carrot.png");
            double px = (800 / 15) * i;
            double py = 0;
            carrot.setPosition(px,py);
            carrotList.add( carrot );
        }
        for (int i = 0; i < 15; i++)
        {
            Sprite burger = new Sprite();
            burger.setImage("burger.png");
            double px = (800 / 15) * i;
            double py = -10;
            burger.setPosition(px,py);
            burgerList.add( burger );
        }

        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue badScore = new IntValue(0);
        IntValue goodScore = new IntValue(0);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //püüdmis korvi liigutamine
                basket.setVelocity(0,0);
                if (input.contains("LEFT"))
                    basket.addVelocity(-130,0);
                if (input.contains("RIGHT"))
                    basket.addVelocity(130,0);

                basket.update(elapsedTime);

                // kokkupõrgete avastamine
                Iterator<Sprite> carrotIter = carrotList.iterator();
                while ( carrotIter.hasNext() )
                {
                    Sprite carrot = carrotIter.next();
                    if ( basket.intersects(carrot) )
                    {
                        carrotIter.remove(); //viska toit minema
                        goodScore.value++; //suurenda head skoori
                    }
                }

                Iterator<Sprite> burgerIter = burgerList.iterator();
                while ( burgerIter.hasNext() )
                {
                    Sprite burger = burgerIter.next();
                    if ( basket.intersects(burger) )
                    {
                        burgerIter.remove(); //viska toit minema
                        badScore.value++; //suurenda halba skoori
                    }
                }

                // render
                for (Sprite carrot : carrotList ) {
                    carrot.addVelocity(0, -110);
                }

                gc.clearRect(0, 0, 800,600);
                basket.render( gc );

                for (Sprite carrot : carrotList )
                    carrot.render(gc);
                for (Sprite burger : burgerList )
                    burger.render(gc);

                // Näita halva skoori suurust
                gc.setFill( Color.RED );
                gc.setStroke( Color.DARKBLUE );
                String pointsText = "BadScore:" + (100 * badScore.value);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );

                // Näita hea skoori suurust
                String goodPointsText = "GoodScore:" + (100 * goodScore.value);
                gc.setFill( Color.GREEN );
                gc.setStroke( Color.BLACK );
                gc.fillText( goodPointsText, 360, 72 );
                gc.strokeText( goodPointsText, 360, 72 );

            }
        }.start();

        theStage.show(); //Näita lava
    }
}