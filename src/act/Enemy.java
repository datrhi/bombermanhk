package act;

import javax.swing.*;

public class Enemy extends Actor {
    private int heart;
    public Enemy(int x, int y, int type, int orient, int speed, int heart, String images) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.runBomb = Bomber.DISALLOW_RUN;
        this.orient = orient;
        this.speed = speed;
        this.heart=heart;
        this.image = new ImageIcon(getClass().getResource(images)).getImage();
        width = image.getWidth(null);
        if(type==Actor.MONSTER){
            height = image.getHeight(null)-23;
        }else{
            height = image.getHeight(null)-38;
        }
    }

    @Override
    public void changeOrient(int orient) {
        super.changeOrient(orient);
        switch (orient) {
            case LEFT:
                image = new ImageIcon(getClass().getResource("/images/monster_left.png")).getImage();
                break;
            case RIGHT:
                image = new ImageIcon(getClass().getResource("/images/monster_right.png")).getImage();
                break;
            case UP:
                image = new ImageIcon(getClass().getResource("/images/monster_up.png")).getImage();
                break;
            case DOWN:
                image = new ImageIcon(getClass().getResource("/images/monster_down.png")).getImage();
                break;
            default:
                break;

        }
    }

}
