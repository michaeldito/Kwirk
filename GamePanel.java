import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel
{
    private GameLevelModel model;
    
    public GamePanel(GameLevelModel m)
    {
        model = m;
        //setPreferredSize(Dimension(500, 500));
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        setBackground(Color.RED);
        model.display((Graphics2D) g);
    }
}
