import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import views.MenuView;

/**
 * Created by martin on 13.12.15.
 */
public class Layout extends Group{
    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);
    public Button backMenuButton = new Button("Back to Menu");

    //Layouti default constructor, koos back nupuga
    public Layout (Pane pane) {
        setContent(pane);
        addBackButton();
        showBackButton();
    }

    //Layouti construktor, mis võimaldab ka ilma nuputa asja teha (kui add backbutton valik on false)
    public Layout (Pane pane, boolean showBackButton) {
        setContent(pane);
        addBackButton();
        if (!showBackButton) {
            hideBackButton();
        }
        else showBackButton();
    }

    public void showBackButton() {
        this.backMenuButton.setVisible(true);
    }

    public void hideBackButton() {
        this.backMenuButton.setVisible(false);
    }

    // lisab menyyse tagasi minemise nupu
    private void addBackButton() {
        this.backMenuButton.setFont(theFontSmall);
        this.backMenuButton.setTranslateY(0);
        this.backMenuButton.setTranslateX(0);
        this.getChildren().add(backMenuButton);
        backMenuButton.setOnAction(e -> this.setContent(new MenuView()));
    }

    //lisab Layout-is asetsevale rootLayout Pane-ile Group-i mis tuli kaasa konstruktoriga
    public void setContent(Pane pane) {
        this.getChildren().clear();
        this.getChildren().addAll(pane); //siia meetod gruppi sättimise kohta
    }
}
