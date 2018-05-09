import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame
{
    private static final long serialVersionUID = 1L;
	private GameLevelModel model;
    private GameplayController controller;
    private GamePanel gamePanel;

    public View()
    {
    }

    public void addModel(GameLevelModel m)
    {
        model = m;
    }

    public void addController(GameplayController c)
    {
        controller = c;
    }

    public void build()
    {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }
        });
        setTitle("Kwirk's Puzzle Rooms");
        gamePanel = new GamePanel(model);
        gamePanel.addKeyListener(controller.getGamePanelKeyListener());
        Container contentPane = getContentPane();
        contentPane.add(gamePanel);

        addKeyListener(controller.getGamePanelKeyListener());

        pack();
        controller.displayGame();

    }

    public void paint(Graphics g)
    {
        super.paint(g);
    }

    public void display()
    {
        setVisible(true);
    }
}