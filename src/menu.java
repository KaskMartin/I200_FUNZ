import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

//Menyy kirjutamine.lol

    public class menu extends Application {
        Stage window;
        Scene theMenu, theGame, theSettings;
        //private Object ActionEvent;
        //Canvas canvas = new Canvas(200, 200);

        public static void main(String[] args) { // avab javafx applicationi
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            window = primaryStage;

            //theMenu ekraanil kuvatavad nupud
            Label label1 = new Label("Pyya ainult tervislikku toitu!"); //Tekst ekraanil
            Button Start = new Button("Start");
            Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
            Start.setOnAction(event -> window.setScene(theGame)); //viib theGame scenele.
            Start.setFont(theFont);
            Button Highscores = new Button("Highscores");
            Highscores.setFont(theFont);
            Button Settings = new Button("Settings");
            Settings.setFont(theFont);
            Settings.setOnAction(event -> window.setScene(theSettings));  //viib theSettings scenele.
            Button Exit = new Button("Exit");
            Exit.setFont(theFont);
            //Exit.setOnAction(event -> window.setScene(theGame)); //paneb ekraani kinni


            //theMenu ekraanil kuvatavate nuppude asukohad
            Pane layout1 = new Pane();
            Start.setTranslateY(100);
            Start.setTranslateX(300);
            Highscores.setTranslateY(200);
            Highscores.setTranslateX(300);
            Settings.setTranslateY(300);
            Settings.setTranslateX(300);
            Exit.setTranslateY(400);
            Exit.setTranslateX(300);
            layout1.getChildren().addAll(label1, Start, Highscores, Settings, Exit);
            theMenu = new Scene(layout1, 800, 600);

            //GraphicsContext gc = canvas.getGraphicsContext2D();

            //theSettings scene nupp
            Label label3 = new Label("Pyya ainult tervislikku toitu!"); //Tekst ekraanil
            Button Tagasi = new Button("Tagasi");
            Tagasi.setFont(theFont);
            Start.setOnAction(event -> window.setScene(theMenu)); //viib theMenu scenele.

            //theSettings scene nupu asukoht ja toimimine
            Pane layout3 = new Pane();
            Button mineTagasi = new Button("Go back to Menu");
            mineTagasi.setTranslateY(300);
            mineTagasi.setTranslateX(300);
            mineTagasi.setFont(theFont);
            mineTagasi.setOnAction(event -> window.setScene(theMenu));  //viib theMenu scenele.
            TextField textField = new TextField (); //tekstiväli kasutajanime sisestamiseks
            HBox hb = new HBox();
            hb.getChildren().addAll(label3, textField);
            hb.setSpacing(10);
            layout3.getChildren().addAll(label3, mineTagasi, textField);
            theSettings = new Scene(layout3, 800, 600);

            // Layout 2
            StackPane layout2 = new StackPane();
            theGame = new Scene(layout2, 800, 600);

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

            Canvas canvas = new Canvas(800, 600);
            GraphicsContext gc = canvas.getGraphicsContext2D();
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

                    //püüdmiskorvi liigutamine
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

            //tekitab akna 1, sellest alustame näitamist
            window.setScene(theMenu);
            window.setTitle("Pyya ainult tervislikku toitu!");
            label1.setFont(theFont);
            window.show();

        }

        //public static void theGameScene(Stage primaryStage) {


        }

