import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuDialog extends JDialog
{
    private JPanel myPanel = null;
    private JPanel buttonPanel = null;
    private String name = "";
    private JTextField nameText;
    private JButton startButton = null, cancelButton = null;
    private View view = null;
    private GameplayController controller = null;
    private String character = "";

    public void addController(GameplayController c)
    {
        controller = c;
    }
    public MenuDialog(View v, boolean modal)
    {
        super(v, modal);
        view = v;
        myPanel = new JPanel();
        nameText = new JTextField();
        nameText.setHorizontalAlignment(JTextField.CENTER);
        nameText.setSize(new Dimension(120, 40));
        nameText.setFont(new Font("Monospaced", Font.BOLD, 32));
        getContentPane().add(myPanel);
        JLabel label = new JLabel("Enter your name:");
        label.setFont(new Font("Monospaced", Font.BOLD, 15));
        label.setHorizontalAlignment(JLabel.CENTER);
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(label);
        myPanel.add(nameText, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        JLabel label2 = new JLabel("Choose your character:");
        label2.setFont(new Font("Monospaced", Font.BOLD, 15));
        label2.setHorizontalAlignment(JLabel.CENTER);
       // myPanel.add(label2);
       // addRadios(); // Doesn't work, theirs null pointer exception - not sure why
        addButtons();
        int h = 250;
        int w = 280;
        setSize(w, h);
        Dimension size = view.getBounds().getSize();
        setLocation(size.width/2 - w/2, size.height/2 - h/2);
        setVisible(true);
    }

    public String getName() { return name; }

    private void addButtons ()
    {
        startButton = new JButton("START");
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        startButton.setFont(new Font("Monospaced", Font.BOLD, 15));
        startButton.setPreferredSize(new Dimension(75, 40));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = nameText.getText().equals("") ? "?" : nameText.getText();
                System.out.println("[MenuDialog] character: " + character);
                if (character.equals("Kwirk"))
                    controller.playAs("Kwirk");
                if (character.equals("Spud"))
                    controller.playAs("Spud");
                if (character.equals("Pickle Rick"))
                    controller.playAs("Pickle Rick");
                view.addName(name);
                view.playGame();
                getContentPane().remove(myPanel);
                setVisible(false);
            }
        });
        buttonPanel.add(startButton); 

        cancelButton = new JButton("CANCEL");
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        cancelButton.setFont(new Font("Monospaced", Font.BOLD, 15));
        cancelButton.setPreferredSize(new Dimension(75, 40));
        cancelButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton); 

        myPanel.add(buttonPanel);
    }

    public void addRadios ()
    {
        final JRadioButton kwirk = new JRadioButton("Kwirk");
        final JRadioButton spud = new JRadioButton("Spud");
        final JRadioButton pickleRick = new JRadioButton("Pickle Rick");
        kwirk.addItemListener(new ItemListener(){
        
            @Override
            public void itemStateChanged(ItemEvent e) {
                character =  e.getStateChange() == 1 ? "Kwirk" : "unchecked";
            }
        });

        spud.addItemListener(new ItemListener(){
        
            @Override
            public void itemStateChanged(ItemEvent e) {
                character =  e.getStateChange() == 1 ? "Spud" : "unchecked";
            }
        });

        pickleRick.addItemListener(new ItemListener(){
        
            @Override
            public void itemStateChanged(ItemEvent e) {
                character = e.getStateChange() == 1 ? "Pickle Rick" : "unchecked";
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(kwirk);
        group.add(spud);
        group.add(pickleRick);

        buttonPanel.add(kwirk);
        buttonPanel.add(spud);
        buttonPanel.add(pickleRick);
    }
}