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

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setGridLinesVisible(true);

        Label mangija1Label = new Label("MÄNGIJA 1");
        mangija1Label.setContentDisplay(ContentDisplay.TOP);
        mangija1Label.setPadding(new Insets(5, 5, 5, 50));

        Label mangija2Label = new Label("MÄNGIJA 2");
        mangija2Label.setContentDisplay(ContentDisplay.TOP);
        mangija2Label.setPadding(new Insets(5, 5, 5, 50));

        //Button vali1Tegelane = new Button("KINNITAN TEGELASE");

        //-----Mängija 1
        ChoiceBox<String> mangija1Tegelane = new ChoiceBox<>();

        mangija1Tegelane.getItems().add("VALI TEGELANE 1");
        mangija1Tegelane.getItems().addAll("1", "2", "3", "4");
        mangija1Tegelane.setValue("VALI TEGELANE 1");

        //vali1Tegelane.setOnAction(e -> getChoice(mangija1Tegelane));

        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(20, 20, 20, 20));
        layout1.getChildren().addAll(mangija1Label, mangija1Tegelane);


        //------Mängija 2
        ChoiceBox<String> mangija2Tegelane = new ChoiceBox<>();

        //getItems returns the ObservableList object which you can add items to
        mangija2Tegelane.getItems().add("VALI TEGELANE 2");
        mangija2Tegelane.getItems().addAll("1", "2", "3", "4");
        mangija2Tegelane.setValue("VALI TEGELANE 2");

        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20, 20, 20, 20));
        layout2.getChildren().addAll(mangija2Label, mangija2Tegelane);

        ImageView mangija1Pilt = new ImageView("images/kasutaja01sv.png");
        ImageView mangija2Pilt = new ImageView("images/kasutaja01v.png");
        ImageView mangija3Pilt = new ImageView("images/kasutaja01v.png");
        ImageView mangija4Pilt = new ImageView("images/kasutaja01v.png");

        Label mangija1Text = new Label("1");
        Label mangija2Text = new Label("2");
        Label mangija3Text = new Label("3");
        Label mangija4Text = new Label("4");

        pane.add(layout1, 2, 2);
        pane.add(layout2, 5, 2);
        pane.add(mangija1Label, 2, 1);
        pane.add(mangija2Label, 5, 1);
        pane.add(mangija1Pilt, 4, 3);
        pane.add(mangija2Pilt, 4, 4);
        pane.add(mangija3Pilt, 4, 5);
        pane.add(mangija4Pilt, 4, 6);
        pane.add(mangija1Text, 3, 3);
        pane.add(mangija2Text, 3, 4);
        pane.add(mangija3Text, 3, 5);
        pane.add(mangija4Text, 3, 6);

        this.getChildren().addAll(pane);
    }

    //To get the value of the selected item
    private void getChoice(ChoiceBox<String> choiceBox) {
        String food = choiceBox.getValue();
        System.out.println(food);
    }
}

        /**
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

         */








