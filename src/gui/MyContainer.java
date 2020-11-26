package gui;

import sound.GameSound;

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
        GameSound.getIstance().stop();
        GameSound.getIstance().getAudio(GameSound.MENU).loop();
    }

    public void setShowPlay() {
        mCardLayout.show(this, "play");
        mPlayGame.requestFocus();
        GameSound.getIstance().getAudio(GameSound.MENU).stop();
        GameSound.getIstance().getAudio(GameSound.PLAYGAME).loop();
    }

    public void setShowHowtoplay() {
        mCardLayout.show(this, "howtoplay");
        mHowtoplay.requestFocus();
    }

}
