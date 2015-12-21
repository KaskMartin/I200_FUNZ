package views;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by martin on 13.12.15.
 */
public class HelpView extends Pane {

    public HelpView() {
        this.setHeight(600);
        this.setWidth(800);
        Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);

        Text juhend = new Text("1. Püüa toitu kasutades nooleklahve liikumiseks paremale või vasakule.\n2. Kasutaja " +
                "kaks saab kasutada klahve Q ja W.\n3. Püüa ainult tervislikku toitu, see annab sulle plusspunkte.\n4." +
                " Halva toidu püüdmine vähendab elusid. Mäng lõppeb, kui elud otsa saavad.\n\n\nMängu autorid: " +
                "Martin Kask, Kersti Miller, Aet Udusaar 2015");
        juhend.setTranslateX(50);
        juhend.setTranslateY(150);
        juhend.setFont(theFontSmall);

        Label settingsHelp = new Label("Mängujuhis");
        settingsHelp.setFont(theFontSmall);
        settingsHelp.setTranslateX(50);
        settingsHelp.setTranslateY(100);

        this.getChildren().addAll(settingsHelp, juhend);
    }

}
