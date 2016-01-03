package views;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by martin on 13.12.15.
 */
public class HelpView extends Pane {
    public Font theFontSmall = Font.font("Tahoma", FontWeight.LIGHT, 14);

    public HelpView() {
        this.setHeight(600);
        this.setWidth(800);

        Label settingsHelp = new Label("Mängujuhis");
        settingsHelp.setFont(theFontSmall);
        settingsHelp.setTranslateX(50);
        settingsHelp.setTranslateY(100);

        Text instructions = new Text(150, 150 , "Mängujuhised");
        instructions.setWrappingWidth(500);
        instructions.setFont(theFontSmall);
        instructions.setTextAlignment(TextAlignment.JUSTIFY);
        instructions.setText("1. Vali endale tegelaskuju Seadete menüüst.\n2. Vaikimisi kasutab Mängija 1 klahve Q ja W ning Mängija 2 <- ja ->" +
                "\n3. Püüa toitu kasutades klahve liikumiseks paremale või vasakule.\n4. Püüa ainult tervislikku toitu, see annab sulle plusspunkte.\n5." +
                " Halva toidu püüdmine vähendab elusid. Mäng lõppeb, kui elud otsa saavad."+
                "\n6. Menüüs lühikäskluste aktiveerimiseks vajuta klahvi Alt\n\n\nMängu autorid: Martin Kask, Kersti Miller, Aet Udusaar 2015");

        this.getChildren().addAll(settingsHelp, instructions);
    }

}
