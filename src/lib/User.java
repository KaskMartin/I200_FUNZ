package lib;

import javafx.geometry.Rectangle2D;

/**
 * Created by martin on 8.12.15.
 * Kasutajate klass, kus redefineerime kasutajate piirjooned, kuna tahame, et ainult korvi puude loeks toidu püütuks
 */
public class User extends Sprite {
    private double collisonStartX = 0;
    private double collisonStartY = 0;
    private double collisonwidth = 0;
    private double collisonhight = 0;
    private String moveLeft = "";
    private String moveRight = "";

    //Default useri collision box asub otse tema kohal ja on sama lai, kui kasutaja aga ainult 30 px kõrge
    public User()
    {
        collisonStartX = this.positionX;
        collisonStartY = this.positionY;
        collisonwidth = this.width;
        collisonhight = 30;
    }

    //construktor juhuks, kui on vaja muuta collision boxi.
    public User(double collisionX, double collisonY, double collisonWidth, double collisonHight)
    {
        collisonStartX = collisionX;
        collisonStartY = collisonY;
        collisonwidth = collisonWidth;
        collisonhight = collisonHight;
    }

    //collision boxi setter, settingute jaoks
    public void setCollision (int colX, int colY, int colwidth, int colhight) {
        collisonStartX = colX;
        collisonStartY = colY;
        collisonwidth = colwidth;
        collisonhight = colhight;
    }

    public void setMoveLeft(String moveLeft) {
        this.moveLeft = moveLeft;
    }
    public void setMoveRight (String moveRight) {
        this.moveRight = moveRight;
    }

    public String getMoveLeft() {
        return moveLeft;
    }

    public String getMoveRight() {
        return moveRight;
    }

    @Override
    public Rectangle2D getBoundary()
        {
            return new Rectangle2D(this.positionX + collisonStartX,this.positionY + collisonStartY,collisonwidth,collisonhight);
        }
}
