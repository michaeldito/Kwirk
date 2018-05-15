import javax.swing.*;

import java.awt.*;

class GamePanel extends JPanel
{
    private GameLevelModel model;
    public ProgressBar bar;

    public GamePanel(GameLevelModel m)
    {
        model = m;
        bar = new ProgressBar();
        setPreferredSize(new Dimension(695, 700));
    }

    public void setModel(GameLevelModel m)
    {
        model = m;
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        setBackground(Color.WHITE);
        model.display((Graphics2D) g);
        bar.paintComponent((Graphics2D) g);
    }
}
