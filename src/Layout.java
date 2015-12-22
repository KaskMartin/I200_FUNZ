import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by martin on 13.12.15.
 */
public class Layout extends Group{
    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);
    public Button backMenuButton = new Button("Back to Menu");

    //Layouti default constructor, koos back nupuga
    public Layout (Pane pane) {
        setContent(pane);
        showBackButton();
    }


    public void showBackButton() {
        this.backMenuButton.setVisible(true);
    }

    public void hideBackButton() {
        this.backMenuButton.setVisible(false);
    }

    //lisab Layout Groupile Panei
    public void setContent(Pane pane) {
        this.getChildren().clear();
        this.backMenuButton.setFont(theFontSmall);
        this.backMenuButton.setTranslateY(0);
        this.backMenuButton.setTranslateX(0);
        this.getChildren().addAll(pane, backMenuButton);
        this.showBackButton();
    }
}
