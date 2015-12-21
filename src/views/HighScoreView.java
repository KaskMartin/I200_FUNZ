package views;

import javafx.scene.Group;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sun.tools.jar.Main;
import views.gms.HighScores;

/**
 * Created by martin on 13.12.15.
 */
public class HighScoreView extends Pane {

    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);
    public HighScores edetabel = new HighScores(); //Edetabel - väljastab tulemused

    public HighScoreView() {
        this.setHeight(600);
        this.setWidth(800);

        Label highScooriTabeliP2is = new Label("Edetabel");

        highScooriTabeliP2is.setFont(theFontSmall);
        highScooriTabeliP2is.setTranslateX(50);
        highScooriTabeliP2is.setTranslateY(100);
        Text tekst = new Text();
        tekst.setTranslateX(50);
        tekst.setTranslateY(130);
        tekst.setText(edetabel.printOutHighScores());
        this.getChildren().addAll(highScooriTabeliP2is, tekst);
    }

    public String getName() {
        Dialog<Main> dialog = new Dialog<>();
        Label label = new Label("Siseta oma nimi: ");
        TextField nimeSisestamiseV2li = new TextField();
        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(nimeSisestamiseV2li, 2, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType nimeSisestamisNupp = new ButtonType("OK");

        dialog.getDialogPane().getButtonTypes().add(nimeSisestamisNupp);
        dialog.show();

        return nimeSisestamiseV2li.getText();}
}
