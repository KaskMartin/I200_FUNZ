import java.util.Random;

/**
 * Created by martin on 27.11.15.
 */
public class Food extends Sprite {
    String[] goodFood = {"images/carrot.png", "images/apple.png", "images/grape.png", "images/orange.png", "images/stawberry.png", "images/watermelon.png" };
    String[] badFood = {"images/burger.png", "images/fries.jpg", "images/hamburger.png", "images/pizza.png", "images/hotdog.jpg"};
    public Boolean good;
    Random random = new Random();

    public Food() {
        good = random.nextBoolean();
        this.setPosition( 32+random.nextInt(736), 0 ); //positioon 32-768
        this.setVelocity(0, 90+random.nextInt(25));
        if (good)
            this.setImage(goodFood[random.nextInt(goodFood.length)]);
        else
            this.setImage(badFood[random.nextInt(badFood.length)]);
    }
}
