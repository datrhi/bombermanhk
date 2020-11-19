package act;

import gui.GUI;
import java.awt.*;
import java.util.ArrayList;

public class Actor {

    /**----------- Orient. --------------*/
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    /**------------ Status. --------------*/
    public static final int ALIVE= 1;
    public static final int DEAD = 0;

    /**------------ Type. -----------------*/
    public static final int BOMBER = 1;
    public static final int MONSTER = 2;
    public static final int BOSS = 3;
    public static final int BOMB = 4;

    protected int x,y,orient,speed,width,height,runBomb; //
    protected int type;    // 1-BOMBER. 2-MONSTER. 3-BOSS. 4-BOMB
    protected Image image;


    /**---------------- Draw Actor. -----------*/
    public void drawActor(Graphics2D g2d){
        switch (type) {
            case BOMBER:
                g2d.drawImage(image, x, y-20, null);
                break;
            case MONSTER:
                g2d.drawImage(image, x, y-20, null);
                break;
            case BOMB:
                g2d.drawImage(image, x, y, null);
                break;

            default:
                break;
        }
    }

    /** ---------------------- Move. ---------------------*/
    public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox){
        if(0 != count % speed){
            return true;
        }
        switch (orient) {
            case LEFT:
                if(x<=0){
                    return false;
                }
                x=x-1;
                for(int i=0;i<arrBomb.size();i++){
                    if(arrBomb.get(i).isImpactBombvsActor(this)==1){
                        x=x+1;
                        return false;
                    }
                }
                for(int i=0;i<arrBox.size();i++){
                    int kq = arrBox.get(i).isImpactBoxvsActor(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                y=y+1;
                            }else{
                                y=y-1;
                            }
                        }
                        x=x+1;
                        return false;
                    }
                }
                break;
            case RIGHT:
                if(x>(905-width)){
                    return false;
                }
                x=x+1;
                for(int i=0;i<arrBomb.size();i++){
                    if(arrBomb.get(i).isImpactBombvsActor(this)==1){
                        x=x-1;
                        return false;
                    }
                }
                for(int i=0;i<arrBox.size();i++){
                    int kq = arrBox.get(i).isImpactBoxvsActor(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                y=y+1;
                            }else{
                                y=y-1;
                            }
                        }
                        x=x-1;
                        return false;
                    }
                }
                break;
            case UP:
                if(y<=0){
                    return false;
                }
                y=y-1;
                for(int i=0;i<arrBomb.size();i++){
                    if(arrBomb.get(i).isImpactBombvsActor(this)==1){
                        y=y+1;
                        return false;
                    }
                }
                for(int i=0;i<arrBox.size();i++){
                    int kq = arrBox.get(i).isImpactBoxvsActor(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                x=x+1;
                            }else{
                                x=x-1;
                            }
                        }
                        y=y+1;
                        return false;
                    }
                }
                break;
            case DOWN:
                if(y>=(610-height)){
                    return false;
                }
                y=y+1;
                for(int i=0;i<arrBomb.size();i++){
                    if(arrBomb.get(i).isImpactBombvsActor(this)==1){
                        y=y-1;
                        return false;
                    }
                }
                for(int i=0;i<arrBox.size();i++){
                    int kq = arrBox.get(i).isImpactBoxvsActor(this);
                    if(kq!=0){
                        if(kq>=-20 && kq<=20){
                            if(kq>0){
                                x=x+1;
                            }else{
                                x=x-1;
                            }
                        }
                        y=y-1;
                        return false;
                    }
                }
                break;

            default:
                break;
        }
        return true;
    }

    /**----------------- Setter & Getter. ------------------*/

    // Orient
    public void changeOrient(int orient){
        this.orient = orient;
    }

    public int getOrient() {
        return orient;
    }

    // Coordinate
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Width
    public int getWidth() {
        return width;
    }

    // Height
    public int getHeight() {
        return height;
    }

    // Run Bomb
    public void setRunBomb(int runBomb) {
        this.runBomb = runBomb;
    }

    public int getRunBomb() {
        return runBomb;
    }

    // Speed
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) { this.speed = speed; }

    // Type
    public int getType() {
        return type;
    }




}
