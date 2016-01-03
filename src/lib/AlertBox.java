package lib;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * Created by KeM on 28.12.2015.
 * kood: https://github.com/buckyroberts/Source-Code-from-Tutorials/blob/master/JavaFX/005_creatingAlertBoxes/AlertBox.java
 */
public class AlertBox {

        /**public static void display(String title, String message) {
            Stage window = new Stage();

            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);

            Label label = new Label();
            label.setText(message);
            Button closeButton = new Button("SULE AKEN");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        }
         */

    }
