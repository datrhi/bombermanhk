package act;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BombBang extends Actor{

    private int x,y,size,timeLine;
    private Image bombBang_left, bombBang_right, bombBang_up, bombBang_down;

    public BombBang(int x, int y, int size,int timeLine, ArrayList<Box> arrBox) {
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
        for(int i=1;i<size;i++){
            int tmp_left=0, tmp_right=0 ,tmp_up=0 ,tmp_dow=0 ;
            for(int j=0;j<arrBox.size();j++){
                if(isImpactBox(x-(i)*45, y, (i+1)*45, 45, arrBox.get(j))){
                    tmp_left=1;
                }

                if(isImpactBox(x, y, (i+1)*45, 45, arrBox.get(j))){
                    tmp_right=1;
                }

                if(isImpactBox(x, y-(i*45), 45, (i+1)*45, arrBox.get(j))){
                    tmp_up=1;
                }
                if(isImpactBox(x, y, 45, (i+1)*45, arrBox.get(j))){
                    tmp_dow=1;
                }
            }
            if(tmp_left==0){
                setImage(Bomber.LEFT, i+1);
            }
            if(tmp_right==0){
                setImage(Bomber.RIGHT, i+1);
            }
            if(tmp_up==0){
                setImage(Bomber.UP, i+1);
            }
            if(tmp_dow==0){
                setImage(Bomber.DOWN, i+1);
            }
        }
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

    private boolean isImpactBox(int x, int y, int width, int height, Box box){
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        return rec1.intersects(rec2);
    }

    public boolean isImpactBombBangVsBox(Box box) {
        if(box.getType()==box.DISALLOW_BANG)
            return false;
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

    public boolean isImpactBombBangvsItem(Item item){
        Rectangle rec1 = new Rectangle(x+45-bombBang_left.getWidth(null), y, bombBang_left.getWidth(null), bombBang_left.getHeight(null));
        Rectangle rec2 = new Rectangle(x, y, bombBang_right.getWidth(null), bombBang_right.getHeight(null));
        Rectangle rec3 = new Rectangle(x, y+45-bombBang_up.getHeight(null), bombBang_up.getWidth(null), bombBang_up.getHeight(null));
        Rectangle rec4 = new Rectangle(x, y, bombBang_down.getWidth(null),bombBang_down.getHeight(null));
        Rectangle rec5 = new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
            if(item.getTimeLine()>0){
                item.setTimeLine(item.getTimeLine()-1);
                return false;
            }else{
                return true;
            }
        }
        return false;
    }

    public boolean isImpactBombBangvsBomb(Bomb bomb){
        Rectangle rec1 = new Rectangle(x+45-bombBang_left.getWidth(null), y, bombBang_left.getWidth(null), bombBang_left.getHeight(null));
        Rectangle rec2 = new Rectangle(x, y,bombBang_right.getWidth(null), bombBang_right.getHeight(null));
        Rectangle rec3 = new Rectangle(x, y+45-bombBang_up.getHeight(null), bombBang_up.getWidth(null), bombBang_up.getHeight(null));
        Rectangle rec4 = new Rectangle(x, y, bombBang_down.getWidth(null), bombBang_down.getHeight(null));
        Rectangle rec5 = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
        if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
            return true;
        }
        return false;
    }

    public void setImage(int orient, int size){
        switch (orient) {
            case Bomber.LEFT:
                if(size==2){
                    bombBang_left = new ImageIcon(getClass().getResource("/images/bombbang_left_2.png")).getImage();
                }
                break;
            case Bomber.RIGHT:
                if(size==2){
                    bombBang_right = new ImageIcon(getClass().getResource("/images/bombbang_right_2.png")).getImage();
                }
                break;
            case Bomber.UP:
                if(size==2){
                    bombBang_up = new ImageIcon(getClass().getResource("/images/bombbang_up_2.png")).getImage();
                }
                break;
            case Bomber.DOWN:
                if(size==2){
                    bombBang_down = new ImageIcon(getClass().getResource("/images/bombbang_down_2.png")).getImage();
                }
                break;

            default:
                break;
        }
    }

    public void deadLineBombBang() {
        timeLine--;
    }

    /**---------------------------Setter & Getter ---------------------*/
    public int getTimeLine() {
        return timeLine;
    }

}
