package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by martin on 13.12.15.
 */
public class SettingsView extends Pane {

    public SettingsView() {
        this.setHeight(600);
        this.setWidth(800);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

        //GridPane nuppude jaoks
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20,20,20,20));

        Label mangija1Label = new Label("MÄNGIJA 1");
        mangija1Label.setContentDisplay(ContentDisplay.TOP);
        mangija1Label.setPadding(new Insets(5,5,5,250));

        //RadioButton nupud mängija1 kuvapiltide jaoks
        RadioButton mangija1 = new RadioButton("");
        mangija1.setGraphic(new ImageView("images/kasutaja01v.png"));
        mangija1.setSelected(true);
        mangija1.setPadding(new Insets(5,5,5,200));

        final RadioButton mangija2 = new RadioButton("");
        mangija2.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangija2.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangija3 = new RadioButton("");
        mangija3.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangija3.setPadding(new Insets(5,5,5,200));

        RadioButton mangija4 = new RadioButton("");
        mangija4.setGraphic(new ImageView("images/kasutaja04v.png"));
        mangija4.setPadding(new Insets(5,5,5,200));

        ToggleGroup mangija1Valikud = new ToggleGroup();
        mangija1.setToggleGroup(mangija1Valikud);
        mangija2.setToggleGroup(mangija1Valikud);
        mangija3.setToggleGroup(mangija1Valikud);
        mangija4.setToggleGroup(mangija1Valikud);

        VBox paneForRadioButtons1 = new VBox(20);
        paneForRadioButtons1.setPadding(new Insets(5,5,5,5));
        pane.add(mangija1Label, 2, 1);
        pane.add(mangija1, 2, 2);
        pane.add(mangija2, 2, 3);
        pane.add(mangija3, 2, 4);
        pane.add(mangija4, 2, 5);

        Label mangija2Label = new Label("MÄNGIJA 2");
        mangija2Label.setContentDisplay(ContentDisplay.TOP);
        mangija2Label.setPadding(new Insets(5,5,5,100));

        //RadioButton nupud mängija2 kuvapiltide jaoks
        RadioButton mangija5 = new RadioButton("");
        mangija5.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangija5.setSelected(true);
        mangija5.setPadding(new Insets(5,5,5,70));

        RadioButton mangija6 = new RadioButton("");
        mangija6.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangija6.setPadding(new Insets(5,5,5,70));

        RadioButton mangija7 = new RadioButton("");
        mangija7.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangija7.setPadding(new Insets(5,5,5,70));

        RadioButton mangija8 = new RadioButton("");
        mangija8.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangija8.setPadding(new Insets(5,5,5,70));

        ToggleGroup mangija2Valikud = new ToggleGroup();
        mangija5.setToggleGroup(mangija2Valikud);
        mangija6.setToggleGroup(mangija2Valikud);
        mangija7.setToggleGroup(mangija2Valikud);
        mangija8.setToggleGroup(mangija2Valikud);

        VBox paneForRadioButtons2 = new VBox(20);
        paneForRadioButtons2.setPadding(new Insets(5,5,5,5));
        pane.add(mangija5, 3, 2);
        pane.add(mangija6, 3, 3);
        pane.add(mangija7, 3, 4);
        pane.add(mangija8, 3, 5);
        pane.add(mangija2Label, 3, 1);

        this.getChildren().addAll(pane);


    }
}
