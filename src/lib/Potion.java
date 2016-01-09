package lib;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.util.Random;

/**
 * Created by martin on 8.01.16.
 * Rotatsiooni kohta käivat koodi on laenatud osaliselt siit: http://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
 */
public class Potion extends Sprite {
    private Random random = new Random();
    private Image image = new Image("images/heart-bottle.png");
    private double startingX;
    private double angle = 0;
    public double rotation = 35;
    public double fallingStuffSpeed = 90;

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

    public void render(GraphicsContext gc)
    {
        drawRotatedImage(gc, image, angle, this.positionX, this.positionY);
    }

    public void update(double time)
    {
        positionY += fallingStuffSpeed * time;
        positionX = startingX + 100*Math.sin(positionY/40);
        angle += rotation * time;
    }

    public Potion() {
        this.setImage(image);
        startingX = 100+random.nextInt(600); //positsioon 100-700
        this.setPosition( startingX, 0 );
        this.setVelocity(0, fallingStuffSpeed+10);
    }
}
