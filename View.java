import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame
{
    private static final long serialVersionUID = 1L;
	private GameLevelModel model;
    private GameplayController controller;
    private GamePanel gamePanel;
    private MenuPanel menu;
    private Font buttonFont = new Font("Monospaced", Font.BOLD, 13);

    public void addBar()
    {
        gamePanel.bar.addBar();
    }

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
        String debug = "[View::updatePanel] ";
        System.out.println(debug + "Updating the GamePanel");

        Container contentPane = getContentPane();
        //contentPane.remove(gamePanel);
        gamePanel.setModel(model);
        //contentPane.add(gamePanel);
        pack();
        controller.displayGame();

        System.out.println(debug + "Complete.");
    }

    public void build()
    {
        String debug = "[View::build] ";
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
        menu = new MenuPanel();
        contentPane.add(menu);
        //contentPane.add(gamePanel, "Center");

        addKeyListener(controller.getGamePanelKeyListener());

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonSetup(controls, controller.getRestartButton(), "RESTART");
        buttonSetup(controls, controller.getUndoButton(), "UNDO");
        buttonSetup(controls, controller.getQuitButton(), "QUIT");
        contentPane.add(controls, "South");

        pack();
        controller.displayGame();
        System.out.println(debug + "View building complete.");
    }

    private void buttonSetup(JPanel panel, JButton b, String label)
    {
        b.setText(label);
        b.setFont(buttonFont);
        b.setFocusable(false);
        panel.add(b);
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