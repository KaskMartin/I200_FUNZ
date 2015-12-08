import javafx.geometry.Rectangle2D;

/**
 * Created by martin on 8.12.15.
 * Kasutajate klass, kus redefineerime kasutajate piirjooned, kuna tahame, et ainult korvi puude loeks toidu püütuks
 */
public class User extends Sprite {
    @Override
    public Rectangle2D getBoundary()
        {
            return new Rectangle2D(this.positionX,this.positionY,this.width,30);
        }
}
