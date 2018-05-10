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

    public void updatePanel()
    {
        String debug = "[debug] [View::updatePanel] ";
        System.out.println(debug + "Updating the GamePanel");

        Container contentPane = getContentPane();
        contentPane.removeAll();
        gamePanel = new GamePanel(model);
        contentPane.add(gamePanel);
        pack();
        controller.displayGame();
        
        System.out.println(debug + "Complete.");
    }

    public void build()
    {
        String debug = "[debug] [View::build] ";
        System.out.println(debug + "Building the view.");
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
        System.out.println(debug + "View building complete.");
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