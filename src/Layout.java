import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.Sprite;

/**
 * Created by martin on 13.12.15.
 */
public class Layout extends Group{
    Font theFontSmall = Font.font("Consolas", FontWeight.LIGHT, 14);
    public Button backMenuButton = new Button("_TAGASI");
    public Button soundOffOnButton = new Button("");
    public ImageView soundOnImage = new ImageView("images/soundON.png");
    public ImageView soundOffImage = new ImageView("images/soundOFF.png");

    //Layouti default constructor, koos back nupuga
    public Layout (Pane pane) {
        soundOffOnButton.setGraphic(soundOnImage);
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
        Sprite BackgroundSprite =  new Sprite();
        BackgroundSprite.setImage("images/backgroundLight.png");
        BackgroundSprite.setPosition(0, 0);
        
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        this.getChildren().clear();
        BackgroundSprite.render(gc);
        this.backMenuButton.setFont(theFontSmall);
        this.backMenuButton.setTranslateY(0);
        this.backMenuButton.setTranslateX(0);
        this.soundOffOnButton.setTranslateX(735);
        this.soundOffOnButton.setTranslateY(0);
        this.getChildren().addAll(pane, backMenuButton, soundOffOnButton);
        this.showBackButton();
    }
}
