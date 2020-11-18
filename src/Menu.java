import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel {
    private int padding = 15;
    ;
    private GUI mGui;
    private MyContainer mContainer;
    private JLabel lbbackground;
    private JLabel btnPlay;
    private JLabel btnHowtoplay;
    private ImageIcon backgroundIcon;

    public Menu(MyContainer mContainer) {
        this.mContainer = mContainer;
        this.mGui = mContainer.getGui();
        setBackground(Color.YELLOW);
        setLayout(null);
        initComps(mGui);
        initbackground();
    }

    public void initComps(GUI mGui) {
        btnPlay = setLabel(30, 500, "/resources/Play.png");

        btnHowtoplay = setLabel(btnPlay.getX() + btnPlay.getWidth() + padding, btnPlay.getY(), "/resources/btnHtp.png");

        btnPlay.addMouseListener(mMouseAdapter);
        btnHowtoplay.addMouseListener(mMouseAdapter);

        add(btnPlay);
        add(btnHowtoplay);
    }

    public void initbackground() {
        lbbackground = new JLabel();
        lbbackground.setBounds(0, -10, mGui.getWidth(), mGui.getHeight());
        lbbackground.setBackground(Color.BLACK);
        backgroundIcon = new ImageIcon(getClass().getResource("/resources/banner2.png"));
        lbbackground.setIcon(backgroundIcon);
        add(lbbackground);
    }

    public JLabel setLabel(int x, int y, String ImageIcon) {
        JLabel label = new JLabel();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
        label.setIcon(Icon);
        return label;
    }

    private MouseAdapter mMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == btnPlay) {
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/resources/Play2.png"));
                btnPlay.setIcon(playIcon);
            }
            if (e.getSource() == btnHowtoplay) {
                ImageIcon HowtoplayIcon = new ImageIcon(getClass().getResource("/resources/btnHtp2.png"));
                btnHowtoplay.setIcon(HowtoplayIcon);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == btnPlay) {
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/resources/Play.png"));
                btnPlay.setIcon(playIcon);
            }
            if (e.getSource() == btnHowtoplay) {
                ImageIcon HowtoplayIcon = new ImageIcon(getClass().getResource("/resources/btnHtp.png"));
                btnHowtoplay.setIcon(HowtoplayIcon);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnPlay) {
                mContainer.setShowPlay();
            }
            if (e.getSource() == btnHowtoplay) {
                mContainer.setShowHowtoplay();
            }
        }
    };

}
