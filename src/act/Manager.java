package act;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Manager {

    private Random random = new Random();
    private Bomber mBomber;
    private ArrayList<Box> arrBox;
    //private ArrayList<Box> arrShawDow;
    private ArrayList<Bomb> arrBomb;
    private ArrayList<BombBang> arrBombBang;
    private ArrayList<Enemy> arrEnemy;
    //private ArrayList<Item> arrItem;
    private String Background;
    private int round=1;
    private int nextRound = 0;
    private int status = 0;

    public Manager() { initManager(); }

    public void initManager() {
                mBomber = new Bomber(0, 540, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
                init("src/map1/BOX.txt", "src/map1/ENEMY.txt" );
                nextRound = 0;
                status = 0;
    }

    public void init(String pathBox, String pathEnemy) {
        arrBox = new ArrayList<Box>();
        arrBomb = new ArrayList<Bomb>();
        arrEnemy = new ArrayList<Enemy>();
        //arrBombBang = new ArrayList<BombBang>();
        //arrItem = new ArrayList<Item>();

        initArrBox(pathBox);
        initArrMonster(pathEnemy);
        //innitArrItem(pathItem);
    }

    /**----------------------------- Box Handle. -------------------------------*/
    public void initArrBox(String pathBox) {
        try {
            FileReader file = new FileReader(pathBox);
            BufferedReader input = new BufferedReader(file);
            Background = input.readLine();
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                String images = str[3];
                Box box = new Box(x, y, type, images);
                arrBox.add(box);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAllBox(Graphics2D g2d) {
        for (int i = 0; i < arrBox.size(); i++) {
            arrBox.get(i).drawBox(g2d);
        }
    }

    public void draWBackground(Graphics2D g2d) {
        Image imgBackground = new ImageIcon(getClass().getResource(Background))
                .getImage();
        g2d.drawImage(imgBackground, 0, 0, null);
    }
    //--------------------------- Box Handle End. --------------------------------//


    /**----------------------------- Enemy Handle. -------------------------------*/
    public void initArrMonster(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                int orient = Integer.parseInt(str[3]);
                int speed = Integer.parseInt(str[4]);
                int heart = Integer.parseInt(str[5]);
                String images = str[6];
                Enemy enemy = new Enemy(x, y, type, orient, speed, heart, images);
                arrEnemy.add(enemy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAllEnemy(Graphics2D g2d) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            arrEnemy.get(i).drawActor(g2d);
        }
    }

    public void changeOrientEnemy() {
        for (int i = 0; i < arrEnemy.size(); i++) {
            int orient = random.nextInt(4) + 1;
            arrEnemy.get(i).changeOrient(orient);
        }
    }

    public void moveAllEnemy(int count) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            if (arrEnemy.get(i).move(count, arrBomb, arrBox) == false) {
                int orient = random.nextInt(4) + 1;
                arrEnemy.get(i).changeOrient(orient);
            }
        }
    }
    //--------------------------- Enemy Handle End. --------------------------------//


    /**----------------------------- Bomb Handle. -------------------------------*/
    public void innitBomb() {
        if (mBomber.getStatus() == Bomber.DEAD) {
            return;
        }
        int x = mBomber.getX();// + mBomber.getWidth() / 2;
        int y = mBomber.getY();// + mBomber.getHeart() / 2;
        for (int i = 0; i < arrBomb.size(); i++) {
            if (arrBomb.get(i).isImpact(x, y)) {
                return;
            }
        }

        if (arrBomb.size() >= mBomber.getQuantityBomb()) {
            return;
        }
        //GameSound.getIstance().getAudio(GameSound.BOMB).play();
        Bomb mBomb = new Bomb(x, y, mBomber.getSizeBomb(), 2500);
        arrBomb.add(mBomb);
    }

    public void drawAllBomb(Graphics2D g2d) {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).drawActor(g2d);
        }
        /*
        for (int i = 0; i < arrBombBang.size(); i++) {
            arrBombBang.get(i).drawBongBang(g2d);
        }*/
    }

    public void deadLineAllBomb() {
        for(int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).deadlineBomb();
            if(arrBomb.get(i).getTimeline() == 0) {
                arrBomb.remove(i);
            }
        }
    }
    //------------------------------ Bomb Handle End. ----------------------------------//


    /**--------------------------- Setter & Getter. ----------------------------------*/
    public ArrayList<Box> getArrBox() {
        return arrBox;
    }

    public ArrayList<Bomb> getArrBomb() {
        return arrBomb;
    }
    public Bomber getmBomber() {
        return mBomber;
    }
}
