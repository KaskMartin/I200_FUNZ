package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        //RadioButton nupud mängija1 kuvapiltide jaoks
        RadioButton mangijaPilt1 = new RadioButton("");
        mangijaPilt1.setGraphic(new ImageView("images/kasutaja01v.png"));
        mangijaPilt1.setSelected(true);
        mangijaPilt1.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangijaPilt2 = new RadioButton("");
        mangijaPilt2.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangijaPilt2.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangijaPilt3 = new RadioButton("");
        mangijaPilt3.setGraphic(new ImageView("images/kasutaja03v.png"));
        mangijaPilt3.setPadding(new Insets(5, 5, 5, 200));

        RadioButton mangijaPilt4 = new RadioButton("");
        mangijaPilt4.setGraphic(new ImageView("images/kasutaja04v.png"));
        mangijaPilt4.setPadding(new Insets(5, 5, 5, 200));

        ToggleGroup mangija1Valikud = new ToggleGroup();
        mangijaPilt1.setToggleGroup(mangija1Valikud);
        mangijaPilt2.setToggleGroup(mangija1Valikud);
        mangijaPilt3.setToggleGroup(mangija1Valikud);
        mangijaPilt4.setToggleGroup(mangija1Valikud);

        VBox paneForRadioButtons1 = new VBox(20);
        paneForRadioButtons1.setPadding(new Insets(5, 5, 5, 5));
        pane.add(mangija1Label, 2, 1);
        pane.add(mangijaPilt1, 2, 2);
        pane.add(mangijaPilt2, 2, 3);
        pane.add(mangijaPilt3, 2, 4);
        pane.add(mangijaPilt4, 2, 5);

        Label mangija2Label = new Label("MÄNGIJA 2");
        mangija2Label.setContentDisplay(ContentDisplay.TOP);
        mangija2Label.setPadding(new Insets(5, 5, 5, 100));

        //RadioButton nupud mängija2 kuvapiltide jaoks
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

        this.getChildren().addAll(pane);

        mangijaPilt5.setToggleGroup(mangija2Valikud);
        mangijaPilt6.setToggleGroup(mangija2Valikud);
        mangijaPilt7.setToggleGroup(mangija2Valikud);
        mangijaPilt8.setToggleGroup(mangija2Valikud);

        mangija2Valikud.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (mangija2Valikud.getSelectedToggle() != null) {
                    final Image image = new Image(
                            getClass().getResourceAsStream(
                                    mangija2Valikud.getSelectedToggle().getUserData().toString()
                                            //+ "kasutaja03sv.png"
                            )
                    );
                }
            }
        });
    }
}

