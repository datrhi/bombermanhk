package act;

import javax.swing.*;
import java.awt.*;

public class Box {

    public static int ALLOW_BANG = 0;
    public static int DISALLOW_BANG = 1;
    private int x,y,width,height;
    private int type;  // 1-ALLOW_BANG. 2-DISALLOW_BANG. Check xem co bi pha khi bom no hay k.
    private Image img;

    /**------------------ Constructor. ---------------------*/
    public Box(int x, int y, int type,String images) {
        //super();
        this.x = x;
        this.y = y;
        this.type = type;
        this.img = new ImageIcon(getClass().getResource(images)).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    /**-------------------- Draw Box. ---------------------*/
    public void drawBox(Graphics2D g2d){
        g2d.drawImage(img, x, y, null);

    }

    /**---------------- Check whether Box impact vs other Actor. --------------*/
    public int isImpactBoxvsActor(Actor actor){
        if(actor.getType()==Actor.BOSS){
            return 0;
        }
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        Rectangle rec3 = new Rectangle();
        if(rec1.intersects(rec2)){
            rec1.intersect(rec1, rec2, rec3);  // Tra ve 1 Rectangle rec3 co dien tich la khoang trung cua rec2 va rec2
            if(rec3.getHeight()==1 && (actor.getOrient()==Actor.UP || actor.getOrient()==Actor.DOWN)){
                if(actor.getX()==rec3.getX()){
                    return (int)rec3.getWidth();
                }else{
                    return (int)-rec3.getWidth();
                }
            }else{
                if(actor.getY()==rec3.getY()){
                    return (int)rec3.getHeight();
                }else{
                    return (int)-rec3.getHeight();
                }
            }
        }
        return 0;
    }

    /**---------------- Setter & Getter. ---------------*/

    // Type
    public int getType() { return type; }

    // Coordinate
    public int getX() { return x; }

    public int getY() { return y; }

    // Width
    public int getWidth() { return width; }

    // Height
    public int getHeight() { return height; }
}
