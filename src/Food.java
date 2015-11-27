import java.util.Random;

/**
 * Created by martin on 27.11.15.
 */
public class Food extends Sprite {
    String[] goodFood = {"images/carrot.png"};
    String[] badFood = {"images/burger.png"};
    public Boolean good;

    public Food() {
        Random random = new Random();

        good = random.nextBoolean();
        this.setPosition((800 / 16) * random.nextInt(16), 0); //positioon 0-750
        this.setVelocity(0, 110);
        if (good)
            this.setImage(goodFood[random.nextInt(goodFood.length)]);
        else
            this.setImage(badFood[random.nextInt(badFood.length)]);
    }
}
