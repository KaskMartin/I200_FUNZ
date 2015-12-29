package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.gms.AlertBox;

/**
 * Created by martin on 13.12.15.
 */
public class SettingsView extends Pane {
    public RadioButton mangijaPilt1 = new RadioButton("");
    public RadioButton mangijaPilt2 = new RadioButton("");
    public RadioButton mangijaPilt3 = new RadioButton("");
    public RadioButton mangijaPilt4 = new RadioButton("");
    public RadioButton mangijaPilt5 = new RadioButton("");
    public RadioButton mangijaPilt6 = new RadioButton("");
    public RadioButton mangijaPilt7 = new RadioButton("");
    public RadioButton mangijaPilt8 = new RadioButton("");
    public ToggleGroup mangija1Valikud = new ToggleGroup();
    public final ToggleGroup mangija2Valikud = new ToggleGroup();
    public Button kinnitaTegelased = new Button("KINNITA TEGELASED");

    public SettingsView() {
        this.setHeight(600);
        this.setWidth(800);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

        //GridPane nuppude jaoks
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));

        Label mangija1Label = new Label("MÄNGIJA 1");
        mangija1Label.setContentDisplay(ContentDisplay.TOP);
        mangija1Label.setPadding(new Insets(5, 5, 5, 250));
        mangija1Label.setFont(theFontSmall);

        //---mängija1 tegelased
        RadioButton mangija1Tegelane1 = new RadioButton("");
        mangija1Tegelane1.setUserData(new ImageView("images/kasutaja01v.png"));
        mangija1Tegelane1.setGraphic(new ImageView("images/kasutaja01v.png"));
        mangija1Tegelane1.setSelected(true);
        mangija1Tegelane1.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangija1Tegelane2 = new RadioButton("");
        mangija1Tegelane2.setUserData(new ImageView("images/kasutaja03v.png"));
        mangija1Tegelane2.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangija1Tegelane2.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangija1Tegelane3 = new RadioButton("");
        mangija1Tegelane3.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangija1Tegelane3.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangija1Tegelane4 = new RadioButton("");
        mangija1Tegelane4.setGraphic(new ImageView("images/kasutaja04v.png"));
        mangija1Tegelane4.setPadding(new Insets(5, 5, 5, 200));

        ToggleGroup mangija1Valikud = new ToggleGroup();
        mangija1Tegelane1.setToggleGroup(mangija1Valikud);
        mangija1Tegelane2.setToggleGroup(mangija1Valikud);
        mangija1Tegelane3.setToggleGroup(mangija1Valikud);
        mangija1Tegelane4.setToggleGroup(mangija1Valikud);

        VBox paneForRadioButtons1 = new VBox(20);
        paneForRadioButtons1.setPadding(new Insets(5, 5, 5, 5));
        pane.add(mangija1Label, 2, 1);
        pane.add(mangija1Tegelane1, 2, 2);
        pane.add(mangija1Tegelane2, 2, 3);
        pane.add(mangija1Tegelane3, 2, 4);
        pane.add(mangija1Tegelane4, 2, 5);

        Label mangija2Label = new Label("MÄNGIJA 2");
        mangija2Label.setContentDisplay(ContentDisplay.TOP);
        mangija2Label.setPadding(new Insets(5, 5, 5, 100));
        mangija2Label.setFont(theFontSmall);

        /**
        //---mängija2 tegelased
        RadioButton mangijaPilt5 = new RadioButton("");
        mangijaPilt5.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangijaPilt5.setSelected(true);
        mangijaPilt5.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt6 = new RadioButton("");
        mangijaPilt6.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangijaPilt6.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt7 = new RadioButton("");
        mangijaPilt7.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangijaPilt7.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt8 = new RadioButton("");
        mangijaPilt8.setGraphic(new ImageView("images/kasutaja01sv.png"));
        mangijaPilt8.setPadding(new Insets(5, 5, 5, 70));

        ToggleGroup mangija2Valikud = new ToggleGroup();

        VBox paneForRadioButtons2 = new VBox(20);
        paneForRadioButtons2.setPadding(new Insets(5, 5, 5, 5));
        pane.add(mangijaPilt5, 3, 2);
        pane.add(mangijaPilt6, 3, 3);
        pane.add(mangijaPilt7, 3, 4);
        pane.add(mangijaPilt8, 3, 5);
        pane.add(mangija2Label, 3, 1);
         */

        //---mängija2 tegelased
        RadioButton mangijaPilt5 = new RadioButton("");
        mangijaPilt5.setUserData(new ImageView("images/kasutaja01v.png"));
        mangijaPilt5.setSelected(true);
        mangijaPilt5.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt6 = new RadioButton("");
        mangijaPilt6.setUserData(new ImageView("images/kasutaja01v.png"));
        mangijaPilt6.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt7 = new RadioButton("");
        mangijaPilt7.setUserData(new ImageView("images/kasutaja03v.png"));
        mangijaPilt7.setPadding(new Insets(5, 5, 5, 70));

        RadioButton mangijaPilt8 = new RadioButton("");
        mangijaPilt8.setUserData(new ImageView("images/kasutaja03v.png"));
        mangijaPilt8.setPadding(new Insets(5, 5, 5, 70));

        ToggleGroup mangija2Valikud = new ToggleGroup();

        VBox paneForRadioButtons2 = new VBox(20);
        paneForRadioButtons2.setPadding(new Insets(5, 5, 5, 5));
        pane.add(mangijaPilt5, 3, 2);
        pane.add(mangijaPilt6, 3, 3);
        pane.add(mangijaPilt7, 3, 4);
        pane.add(mangijaPilt8, 3, 5);
        pane.add(mangija2Label, 3, 1);

        //Tekita valikute kontroll. Kui on samad pildid valitud, siis viskab vea. Kui ei ole, siis ei viska.
        //AlertBox selleks, kui kasutajad valivad samad tegelased. EI TÖÖTA!
        kinnitaTegelased.setFont(theFontSmall);
        pane.add(kinnitaTegelased, 3, 7);
            if (mangija1Valikud.getSelectedToggle().equals(mangija2Valikud.getSelectedToggle()));
            kinnitaTegelased.setOnAction(e -> AlertBox.display("VIGA", "PALUN VALIGE ERINEVAD TEGELASED!"));

        this.getChildren().addAll(pane);

        mangijaPilt5.setToggleGroup(mangija2Valikud);
        mangijaPilt6.setToggleGroup(mangija2Valikud);
        mangijaPilt7.setToggleGroup(mangija2Valikud);
        mangijaPilt8.setToggleGroup(mangija2Valikud);


    }
}








