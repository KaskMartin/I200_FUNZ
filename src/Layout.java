import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lib.Sprite;

/**
 * Created by martin on 13.12.15.
 */
public class Layout extends Group {

    public Button backMenuButton = new Button("_TAGASI");
    public Button soundOffOnButton = new Button("");
    public ImageView soundONImage = new ImageView("images/soundON.png");
    public ImageView soundOFFImage = new ImageView("images/soundOFF.png");
    public GraphicsContext gc;
    public Sprite BackgroundSprite;
    public Font theFont = Font.font("Consolas", FontWeight.LIGHT, 24);
    public Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);



    //Layouti default constructor, koos back nupuga
    public Layout(Pane pane) {
        soundOffOnButton.setGraphic(soundONImage);
        setContent(pane);
        showBackButton();
    }

    public void showBackButton() {
        this.backMenuButton.setVisible(true);
        backMenuButton.setFont(theFontSmall);
    }

    public void hideBackButton() {
        this.backMenuButton.setVisible(false);
    }

    //lisab Layout Groupile Panei
    public void setContent(Pane pane) {
        // Taustapilt
        Canvas backCanvas = new Canvas(800, 600);
        BackgroundSprite.setImage("images/backgroundLight.png");
        BackgroundSprite.setPosition(0, 0);
        BackgroundSprite.render(gc);

        this.getChildren().clear();
        this.getChildren().add(backCanvas);
        this.backMenuButton.setFont(theFontSmall);
        this.backMenuButton.setTranslateY(0);
        this.backMenuButton.setTranslateX(0);
        this.soundOffOnButton.setTranslateX(752);
        this.soundOffOnButton.setTranslateY(0);
        this.getChildren().addAll(pane, backMenuButton, soundOffOnButton);
        this.showBackButton();

    }
}

