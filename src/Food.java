import java.util.Random;

/**
 * Created by martin on 27.11.15.
 */
public class Food extends Sprite {
    String[] goodFood = {"images/carrot.png", "images/apple.png", "images/cherry.png", };
    String[] badFood = {"images/burger.png", "images/fries.png", "images/hotdog.png", "images/hamburger.png", "images/pizza.png"};
    public Boolean good;
    Random random = new Random();

    public Food() {
        good = random.nextBoolean();
        this.setPosition( 5+random.nextInt(790), 0 ); //positioon 5-790
        this.setVelocity(0, 20);
        if (good)
            this.setImage(goodFood[random.nextInt(goodFood.length)]);
        else
            this.setImage(badFood[random.nextInt(badFood.length)]);
    }
}
