import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class MyContainer extends JPanel {
    private CardLayout mCardLayout;
    private GUI gui;
    private Menu mMenu;
    private PlayGame mPlayGame;
    private Howtoplay mHowtoplay;

    public MyContainer(GUI mGui) {
        this.gui = mGui;
        setBackground(Color.WHITE);
        mCardLayout = new CardLayout();
        setLayout(mCardLayout);
        mMenu = new Menu(this);
        add(mMenu, "menu");
        mPlayGame = new PlayGame(this);
        add(mPlayGame, "play");
        mHowtoplay = new Howtoplay(this);
        add(mHowtoplay, "howtoplay");
        setShowMenu();
    }

    public GUI getGui() {
        return gui;
    }

    public void setShowMenu() {
        mCardLayout.show(this, "menu");
        mMenu.requestFocus();
    }

    public void setShowPlay() {
        mCardLayout.show(this, "play");
        mPlayGame.requestFocus();
    }

    public void setShowHowtoplay() {
        mCardLayout.show(this, "howtoplay");
        mHowtoplay.requestFocus();
    }

}
