package gui;

import act.Manager;
import act.Bomber;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PlayGame extends JPanel implements Runnable,ActionListener{
    private MyContainer mContainer;
    public static boolean IS_RUNNING=true;
    private BitSet traceKey = new BitSet();
    private Manager mMagager = new Manager();
    private int count=0;
    private int move;
    int timeDead = 0; // delay dead;

    public PlayGame(MyContainer mContainer) {
        this.mContainer = mContainer;
        setBackground(Color.WHITE);
        setLayout(null);
        addKeyListener(keyAdapter);
        Thread mytheard = new Thread(this);
        mytheard.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        mMagager.draWBackground(g2d);
        mMagager.drawAllItem(g2d);
        mMagager.drawAllBox(g2d);
        mMagager.drawAllEnemy(g2d);
        mMagager.drawAllBomb(g2d);
        mMagager.getmBomber().drawActor(g2d);
    }

    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };

    @Override
    public void run() {

        while(IS_RUNNING){

            /** Dieu chinh toc do */
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /** Dieu huong theo du lieu nhap vao tu ban phim */
            if(traceKey.get(KeyEvent.VK_LEFT)){
                mMagager.getmBomber().changeOrient(Bomber.LEFT);
                mMagager.getmBomber().move(count,mMagager.getArrBomb(),mMagager.getArrBox());

            }
            if(traceKey.get(KeyEvent.VK_RIGHT)){
                mMagager.getmBomber().changeOrient(Bomber.RIGHT);
                mMagager.getmBomber().move(count,mMagager.getArrBomb(),mMagager.getArrBox());
            }
            if(traceKey.get(KeyEvent.VK_UP)){
                mMagager.getmBomber().changeOrient(Bomber.UP);
                mMagager.getmBomber().move(count,mMagager.getArrBomb(),mMagager.getArrBox());;
            }
            if(traceKey.get(KeyEvent.VK_DOWN)){
                mMagager.getmBomber().changeOrient(Bomber.DOWN);
                mMagager.getmBomber().move(count,mMagager.getArrBomb(),mMagager.getArrBox());
            }


            if(traceKey.get(KeyEvent.VK_SPACE)){
                mMagager.innitBomb();
                mMagager.getmBomber().setRunBomb(Bomber.ALLOW_RUN);
            }

            // Cho phep bomber di qua bomb
            mMagager.setRunBomber();
            // Dem nguoc thoi gian bomb no


            mMagager.deadLineAllBomb();
            mMagager.checkDead();

            if(mMagager.getmBomber().getStatus() == 0) {
                timeDead++;  //Delay 2000 loop
                System.out.println(timeDead);
                if(timeDead == 2000){
                    mMagager.initManager();
                    mContainer.setShowMenu();
                    timeDead = 0;
                }
            }

            // Kiem tra nhat vat pham
            mMagager.checkImpactItem();
            /*
            // Kiem tra thang hay thua
            mMagager.checkWinAndLose();


            if(mMagager.getStatus()==1){
                timeLose++;
                if(timeLose == 3000){
                    mMagager.innitManager();
                    mContainer.setShowMenu();
                    timeLose=0;
                }
            }

            if(mMagager.getStatus()==2){
                timeNext++;
                if(timeNext==3000){
                    mMagager.innitManager();
                    timeNext=0;
                }
            }

            if(mMagager.getStatus()==3){
                timeNext++;
                if(timeNext==3000){
                    mMagager.innitManager();
                    mContainer.setShowMenu();
                    timeNext=0;
                }
            }


            if(mMagager.getmBomber().getStatus()==Bomber.DEAD){
                timeDead++;
                if(timeDead==3000){
                    mMagager.setNewBomber();
                    timeDead=0;
                }
            }
            */

            // move = 5000 la khoan thoi gian enemy se thay doi huong (hàm run chay dc 5000 lan se doi huong cua cac enemy
            // va thay doi huong ntn phu thuoc vao ham changeOrientALL()
            if(move==0){
                mMagager.changeOrientEnemy();
                move=5000;
            }
            if(move>0){
                move--;
            }

            mMagager.moveAllEnemy(count);
            repaint();
            count++;

            if(count==1000000) {
                count=0;
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

