import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel
{
    private GameLevelModel model;
    
    public GamePanel(GameLevelModel m)
    {
        model = m;
        setPreferredSize(new Dimension(715, 650));
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        setBackground(Color.WHITE);
        model.display((Graphics2D) g);
    }
}
