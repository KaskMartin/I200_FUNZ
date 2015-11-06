import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javax.xml.stream.events.Attribute;


//Menyy kirjutamine


    public class menu extends Application {
        Stage window;
        Scene scene1, scene2;
        Canvas canvas = new Canvas(200, 200);

        public static void main(String[] args) { // avab javafx applicationi
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            window = primaryStage;

            Label label1 = new Label("Pyya ainult tervislikku toitu!"); //Tekst ekraanil
            Button button1 = new Button("START");
            Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
            button1.setFont(theFont);
            Button button3 = new Button("HIGHSCORES");
            button3.setFont(theFont);
            Button button4 = new Button("SETTINGS");
            button4.setFont(theFont);
            Button button5 = new Button("EXIT - GO BACK TO THE REAL WORLD:)");
            button5.setFont(theFont);
            button1.setOnAction(e -> window.setScene(scene2));
            button5.setOnAction(event -> window.setScene(scene2));

            //Layout 1- cildren are laid out in vertical column
            /**TilePane layout1 = new TilePane(20, 50);*/
            Pane layout1 = new Pane();
            button1.setTranslateY(100);
            button1.setTranslateX(300);
            button3.setTranslateY(200);
            button3.setTranslateX(300);
            button4.setTranslateY(300);
            button4.setTranslateX(300);
            button5.setTranslateY(400);
            button5.setTranslateX(300);
            layout1.getChildren().addAll(label1, button1, button3, button4, button5);
            scene1 = new Scene(layout1, 800, 600);

            GraphicsContext gc = canvas.getGraphicsContext2D();

            /**theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
            gc.setFont(theFont);
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);*/

            //Button 2 - vajutades viib tagasi scene1.
            Button button2 = new Button("Mine tagasi esimesele ekraanile");
            button2.setOnAction(e -> window.setScene(scene1));

            // Layout 2
            StackPane layout2 = new StackPane();
            layout2.getChildren().add(button2);
            scene2 = new Scene(layout2, 600, 600);

            //tekitab akna 1, sellest alustame näitamist
            window.setScene(scene1);
            window.setTitle("Pyya ainult tervislikku toitu!");
            label1.setFont(theFont);
            window.show();
        }
    }
