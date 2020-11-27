package act;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Actor {
    private int heart;

    public Enemy(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.runBomb = Bomber.DISALLOW_RUN;
        //creep = 2 && boss == 3
        if (this.type == Actor.MONSTER) {
            this.orient = 3;
            this.speed = 10;
            this.heart = 1;
            this.image = new ImageIcon(getClass().getResource("/images/monster_down.png")).getImage();
            width = image.getWidth(null);
            height = image.getHeight(null) - 23;
        } else if (this.type == Actor.BOSS) {
            this.orient = 4;
            this.speed = 10;
            this.heart = 2500;
            this.image = new ImageIcon(getClass().getResource("/images/boss_down.png")).getImage();
            width = image.getWidth(null);
            height = image.getHeight(null) - 38;
        }
    }

    public Enemy(int x, int y, int type, int orient, int speed, int heart, String images) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.runBomb = Bomber.DISALLOW_RUN;
        this.orient = orient;
        this.speed = speed;
        this.heart = heart;
        this.image = new ImageIcon(getClass().getResource(images)).getImage();
        width = image.getWidth(null);
        if (type == Actor.MONSTER) {
            height = image.getHeight(null) - 23;
        } else {
            height = image.getHeight(null) - 38;
        }
    }

    public void drawBoss(Graphics2D g2d){
        if(type==Actor.BOSS){
            g2d.drawImage(image, x, y-38, null);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x+13, y-54, width-26, 12);
            Image imgHeartBoss = new ImageIcon(getClass().getResource("/images/heart_boss.png")).getImage();
            int a=0;
            for(int i=0;i<(heart-1)/250+1 ;i++){
                g2d.drawImage(imgHeartBoss, x+18+a, y-52, null);
                a=a+10;
            }
        }
    }
    @Override
    public void changeOrient(int orient) {
        super.changeOrient(orient);
        if (type == Actor.MONSTER) {
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
        } else {
            switch (orient) {
                case LEFT:
                    image = new ImageIcon(getClass().getResource("/images/boss_left.png")).getImage();
                    break;
                case RIGHT:
                    image = new ImageIcon(getClass().getResource("/images/boss_right.png")).getImage();
                    break;
                case UP:
                    image = new ImageIcon(getClass().getResource("/images/boss_up.png")).getImage();
                    break;
                case DOWN:
                    image = new ImageIcon(getClass().getResource("/images/boss_down.png")).getImage();
                    break;
                default:
                    break;
            }
        }

    }


    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

}
