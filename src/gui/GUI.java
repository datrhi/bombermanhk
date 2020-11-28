package gui;

import sound.GameSound;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class GUI extends JFrame {

    private MyContainer mContainer;

    public GUI() {
        setTitle("BoomHK");
        setSize(1395, 620);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);  // hiện thị ra giữa màn hình
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  // Dừng chương trinh khi tắt
        mContainer = new MyContainer(this);
        add(mContainer);
        addWindowListener(mwindow);
    }

    private WindowAdapter mwindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            GameSound.getIstance().stop();
            PlayGame.IS_RUNNING = false;
        }
    };

}
