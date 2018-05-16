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
    private VictoryPanel victory;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private Font buttonFont = new Font("Monospaced", Font.BOLD, 16);
    public String name;
    private JPanel controls = new JPanel();

    public void addBar()
    {
        gamePanel.bar.addBar();
    }

    public void addName(String n)
    {
        name = n;
        System.out.println("[View] added name: " + n);
    }

    public View()
    {
    }

    public void displayVictoryScreen()
    {
        victory = new VictoryPanel();
        Container contentPane = getContentPane();
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.add(victory, "Center");
        pack();
        controller.displayGame();
    }

    public void addModel(GameLevelModel m)
    {
        model = m;
    }

    public void addController(GameplayController c)
    {
        controller = c;
    }

    public void playGame()
    {
        Container contentPane = getContentPane();
        contentPane.remove(menu);
        contentPane.add(gamePanel, "Center");

        controls.removeAll();
        controls.revalidate();

        buttonSetup(controls, controller.getRestartButton(), "RESTART");
        buttonSetup(controls, controller.getUndoButton(), "UNDO");
        buttonSetup(controls, controller.getQuitButton(), "QUIT");

        JLabel nameLabel = new JLabel("Player: " + name);
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(nameLabel, "North");
        contentPane.setBackground(Color.WHITE);

        pack();
        controller.displayGame();
    }

    public void updatePanel()
    {
        String debug = "[View::updatePanel] ";
        System.out.println(debug + "Updating the GamePanel");
        gamePanel.setModel(model);
        pack();
        controller.displayGame();
        System.out.println(debug + "Complete.");
    }

    public void build()
    {
        String debug = "[View::build] ";
        System.out.println(debug + "Building the view.");

        menuBar.add(fileMenu);
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
              System.out.println("[View] File - Quit pressed.");
              System.exit(0);
            }
          });
        fileMenu.add(quitItem);
        setJMenuBar(menuBar);

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
        contentPane.add(menu, "Center");

        addKeyListener(controller.getGamePanelKeyListener());

        controls.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonSetup(controls, controller.getMenuButton(), "");
        contentPane.add(controls, "South");

        controls.setBackground(Color.WHITE);
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