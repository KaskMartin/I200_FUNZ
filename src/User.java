import javafx.geometry.Rectangle2D;

/**
 * Created by martin on 8.12.15.
 * Kasutajate klass, kus redefineerime kasutajate piirjooned, kuna tahame, et ainult korvi puude loeks toidu püütuks
 */
public class User extends Sprite {
    double collisonStartX = 0;
    double collisonStartY = 0;
    double collisonwidth = 0;
    double collisonhight = 0;


    public User()
    {
        collisonStartX = this.positionX;
        collisonStartY = this.positionY;
        collisonwidth = this.width;
        collisonhight = 30;
    }

    public User(double collisionX, double collisonY, double collisonWidth, double collisonHight)
    {
        collisonStartX = collisionX;
        collisonStartY = collisonY;
        collisonwidth = collisonWidth;
        collisonhight = collisonHight;
    }

    @Override
    public Rectangle2D getBoundary()
        {
            return new Rectangle2D(this.positionX + collisonStartX,this.positionY + collisonStartY,collisonwidth,collisonhight);
        }
}
