package lib;

import java.util.Random;

/**
 * Created by martin on 27.11.15.
 */
public class Food extends Sprite {
    String[] goodFood = {"images/banana.png", "images/redpepper.png", "images/tomato.png", "images/cucumber.png", "images/Lettuce.png", "images/apple.png","images/carrot.png","images/cherry.png","images/grape.png","images/orange.png","images/strawberry.png","images/watermelon.png" };
    String[] badFood = {"images/burger.png","images/frfries.png","images/hotdog.png","images/pizza.png", "images/coca.png"};
    public Boolean good;
    Random random = new Random();
    public double fallingStuffSpeed = 90;

    public Food() {
        good = random.nextBoolean();
        this.setPosition( 32+random.nextInt(736), 0 ); //positioon 32-768
        this.setVelocity(0, fallingStuffSpeed+random.nextInt(25));
        if (good)
            this.setImage(goodFood[random.nextInt(goodFood.length)]);
        else
            this.setImage(badFood[random.nextInt(badFood.length)]);
    }
}
