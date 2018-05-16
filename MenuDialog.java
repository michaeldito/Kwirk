import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuDialog extends JDialog implements ActionListener
{
    private GamePanel gamePanel = null;
    private JPanel myPanel = null;
    private JPanel buttonPanel = null;
    private String name = "";
    private JTextField nameText;
    private JButton startButton = null, cancelButton = null;
    private JFrame myFrame = null;
    private View view;

    public MenuDialog(View v, boolean modal)
    {
        super(v, modal);
        myPanel = new JPanel();
        view = v;

        getContentPane().add(myPanel);

        myPanel.setLayout(new GridLayout(3, 1));

        setTitle("Main Menu");

        myPanel.add(new JLabel("                  Enter your name:"));
        nameText = new JTextField();

        myPanel.add(nameText);
        addButtons();

        setLocation(200, 225);
        setSize(250, 150);
        setVisible(true);
    }

    public String getName() { return name; }

    private void addButtons ()
    {
        buttonPanel = new JPanel();
        startButton = new JButton("START");
        startButton.setFont(new Font("Monospaced", Font.BOLD, 13));
        startButton.addActionListener(this);
        buttonPanel.add(startButton); 
        cancelButton = new JButton("CANCEL");
        cancelButton.setFont(new Font("Monospaced", Font.BOLD, 13));
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton); 
        myPanel.add(buttonPanel);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (startButton == e.getSource())
        {
            name = nameText.getText();
            view.addName(name);
            view.playGame();
            getContentPane().remove(myPanel);
            setVisible(false);
        }
        if (cancelButton == e.getSource())
        {
            setVisible(false);
        }
    }
}