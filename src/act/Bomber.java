package act;

import javax.swing.*;
import java.awt.*;

public class Bomber extends Actor {

    public static int ALLOW_RUN = 0;
    public static int DISALLOW_RUN = 1;
    protected int sizeBomb, quantityBomb, status, score, heart;

    /** ------------------- Constructor. ----------------------*/
    public Bomber(int x, int y, int type, int orient, int speed, int sizeBomb, int quantityBomb) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.orient = orient;
        this.speed = speed;
        this.sizeBomb = sizeBomb;
        this.quantityBomb = quantityBomb;
        this.heart = 3;
        this.score = 0;
        this.status = Bomber.ALIVE;
        this.image =new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage();
        width = image.getWidth(null);    // ko co 1 doi tuong nao dang doi anh
        height = image.getHeight(null); //-20
    }

    /** ---------------- Set new boomber in new map or after dead. ---------------*/
    public void setNew(int x,int y) {
        this.x = x;
        this.y = y;
        this.status = ALIVE;
        this.image = new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage();
    }

    /** ----------------- Check whether Bomber impact vs other Actor. ------------*/
    public boolean isImpactBomberVsActor(Actor actor){
        if(status==DEAD){
            return false;
        }
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        return rec1.intersects(rec2);
    }

    /**----------------------- Change Orient of Bomber. -------------------*/
    @Override
    public void changeOrient(int orient) {
        if(this.status==DEAD){
            return;
        }
        super.changeOrient(orient);
        switch (orient) {
            case LEFT:
                image = new ImageIcon(getClass().getResource("/images/bomber_left.png")).getImage();
                break;
            case RIGHT:
                image = new ImageIcon(getClass().getResource("/images/bomber_right.png")).getImage();
                break;
            case UP:
                image = new ImageIcon(getClass().getResource("/images/bomber_up.png")).getImage();
                break;
            case DOWN:
                image = new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage();
                break;
            default:
                break;
        }
    }


    /** ------------------ Setter & Getter ----------------------*/

    // Score
    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    // Image
    public void setImage(Image img) { this.image = img; }

    // Satus
    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    // Quantity Bomb
    public int getQuantityBomb() { return quantityBomb; }

    public void setQuantityBomb(int quantityBomb) { this.quantityBomb = quantityBomb; }

    // Size Bomb
    public void setSizeBomb(int sizeBomb) { this.sizeBomb = sizeBomb; }

    public int getSizeBomb() { return sizeBomb; }

    // Type
    public int getType() { return type; }

    // Heart
    public int getHeart() { return heart; }

    public void setHeart(int heart) { this.heart = heart; }
}
