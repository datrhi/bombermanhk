package act;

import javax.swing.*;
import java.awt.*;

public class BombBang extends Actor{

    private int x,y,size,timeLine;
    private Image bombBang_left, bombBang_right, bombBang_up, bombBang_down;

    public BombBang(int x, int y, int size, int timeLine) {
        x = (x/45) * 45;  // chuan hoa ve nguyen lan cua 45
        y = (y/45) * 45;
        this.x = x;
        this.y = y;
        this.size = size;
        this.timeLine = timeLine;
        bombBang_left = new ImageIcon(getClass().getResource("/images/bombbang_left_1.png")).getImage();
        bombBang_right = new ImageIcon(getClass().getResource("/images/bombbang_right_1.png")).getImage();
        bombBang_up = new ImageIcon(getClass().getResource("/images/bombbang_up_1.png")).getImage();
        bombBang_down = new ImageIcon(getClass().getResource("/images/bombbang_down_1.png")).getImage();
    }

    public void drawBombBang(Graphics2D gd) {
        gd.drawImage(bombBang_left,x + 45 - bombBang_left.getWidth(null), y, null);
        gd.drawImage(bombBang_right, x , y, null);
        gd.drawImage(bombBang_up, x, y + 45 - bombBang_up.getHeight(null), null);
        gd.drawImage(bombBang_down, x, y, null);
    }
    public boolean isImpact(int x, int y, int width, int height,  Box box) {
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        return rec1.intersects(rec2);
    }

    public boolean isImpactBombBangVsBox(Box box) {
        if(box.getType()==box.DISALLOW_BANG) return false;
        Rectangle rec1 = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        Rectangle rec2 = new Rectangle(x + 45 -bombBang_left.getWidth(null), y,
                bombBang_left.getWidth(null), bombBang_left.getHeight(null));

        Rectangle rec3 = new Rectangle(x , y, bombBang_right.getWidth(null), bombBang_right.getHeight(null));

        Rectangle rec4 = new Rectangle(x, y + 45 - bombBang_up.getHeight(null),
                bombBang_up.getWidth(null), bombBang_up.getHeight(null));

        Rectangle rec5 = new Rectangle(x, y, bombBang_down.getWidth(null), bombBang_down.getHeight(null));

        if( rec2.intersects(rec1) || rec3.intersects(rec1) || rec4.intersects(rec1) || rec5.intersects(rec1) ) {
            return true;
        }
        return false;
    }

    public boolean isImpactBombBangVsActor(Actor actor) {
        //if(box.getType()==box.DISALLOW_BANG) return false;
        Rectangle rec1 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        Rectangle rec2 = new Rectangle(x + 45 -bombBang_left.getWidth(null), y,
                bombBang_left.getWidth(null), bombBang_left.getHeight(null));

        Rectangle rec3 = new Rectangle(x , y, bombBang_right.getWidth(null), bombBang_right.getHeight(null));

        Rectangle rec4 = new Rectangle(x, y + 45 - bombBang_up.getHeight(null),
                bombBang_up.getWidth(null), bombBang_up.getHeight(null));

        Rectangle rec5 = new Rectangle(x, y, bombBang_down.getWidth(null), bombBang_down.getHeight(null));

        if( rec2.intersects(rec1) || rec3.intersects(rec1) || rec4.intersects(rec1) || rec5.intersects(rec1) ) {
            return true;
        }
        return false;
    }

    public void deadLineBombBang() {
        timeLine--;
    }

    /**---------------------------Setter & Getter ---------------------*/
    public int getTimeLine() {
        return timeLine;
    }

}
