import java.awt.Color;
import javax.swing.JPanel;


public class PlayGame extends JPanel{
    private MyContainer mContainer;

    public PlayGame(MyContainer mContainer) {
        this.mContainer = mContainer;
        setBackground(Color.WHITE);
        setLayout(null);
    }

}

