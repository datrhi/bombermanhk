package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Howtoplay extends JPanel implements ActionListener {
    private MyContainer mContainer;
    private JLabel lbbackground;
    private ImageIcon backgroundIcon;
    private JButton btn_ok;

    public Howtoplay(MyContainer mContainer) {
        this.mContainer = mContainer;
        setBackground(Color.YELLOW);
        setLayout(null);
        initCompts();
    }

    public void initCompts() {
        lbbackground = new JLabel();
        lbbackground.setBounds(95, -40, 905, 610);
        backgroundIcon = new ImageIcon(getClass().getResource("/images/Howtoplay.png"));
        lbbackground.setIcon(backgroundIcon);
        add(lbbackground);

        btn_ok = new JButton();
        btn_ok.setText("OK");
        btn_ok.setBounds(400, 520, 100, 50);
        btn_ok.addActionListener(this);
        add(btn_ok);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_ok) {
            mContainer.setShowMenu();
        }
    }

}
