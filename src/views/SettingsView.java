package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by martin on 13.12.15.
 */
public class SettingsView extends Pane {

    public SettingsView() {
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

        Label settingsInfo1 = new Label("Vaheta mänguklahvide kombinatsiooni \n\nKasutaja 1");
        settingsInfo1.setTranslateX(100);
        settingsInfo1.setTranslateY(60);
        settingsInfo1.setFont(theFontSmall);
        Label settingsInfo2 = new Label("Vaheta mänguklahvide kombinatsiooni \n\nKasutaja 2");
        settingsInfo2.setTranslateX(475);
        settingsInfo2.setTranslateY(60);
        settingsInfo2.setFont(theFontSmall);
        this.getChildren().addAll(settingsInfo1, settingsInfo2);

        final ToggleGroup valikunupp = new ToggleGroup();

        RadioButton var1 = new RadioButton("<- ->");
        var1.setToggleGroup(valikunupp);
        var1.setUserData("<- ->");
        var1.setFont(theFontSmall);

        RadioButton var2 = new RadioButton("Q W");
        var2.setToggleGroup(valikunupp);
        var2.setUserData("Q W");
        var2.setFont(theFontSmall);

        RadioButton var3 = new RadioButton("<- ->");
        var3.setToggleGroup(valikunupp);
        var3.setUserData("<- ->");
        var3.setFont(theFontSmall);

        RadioButton var4 = new RadioButton("Q W");
        var4.setToggleGroup(valikunupp);
        var4.setUserData("Q W");
        var4.setFont(theFontSmall);

        valikunupp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {


        });

        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        VBox vbox = new VBox();
        VBox vbox2 = new VBox();

        vbox.getChildren().add(var1);
        vbox.getChildren().add(var2);
        vbox.setSpacing(20);
        vbox.setTranslateX(70);
        vbox.setTranslateY(0);

        vbox2.getChildren().add(var3);
        vbox2.getChildren().add(var4);
        vbox2.setSpacing(20);
        vbox2.setTranslateX(70);
        vbox2.setTranslateY(50);

        hbox.getChildren().add(vbox);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox.setTranslateX(50);
        hbox.setTranslateY(100);

        hbox2.getChildren().add(vbox2);
        hbox.setSpacing(150);
        hbox.setPadding(new Insets(50, 10, 10, 20));
        hbox2.setTranslateX(450);
        hbox2.setTranslateY(100);

        this.getChildren().addAll(hbox, hbox2);
    }
}
