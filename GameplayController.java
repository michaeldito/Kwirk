import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.imageio.*;

public class GameplayController
{
  public GameLevelModel currentLevelModel;
  private GameLevelModel currentLevelModelBackup;
  private Queue<GameLevelModel> levels;
  private Stack<GameLevelModel> states;

  private View view;

  public Boolean hasMoreLevels = true;
  public int currentLevel = 1;

  public GameplayController()
  {
    currentLevelModel = new GameLevelModel();
    currentLevelModelBackup = new GameLevelModel();
    levels = new LinkedList<GameLevelModel>();
    states = new Stack<GameLevelModel>();
  }

  public void playAs(String character)
  {
    currentLevelModel.setPlayerCharacter(character);
  }

  public void move(String direction)
  {
    String debug = "[GameplayController::move] ";
    System.out.println("========================================================");

    if (currentLevelModel.ableToMove(direction)) {
      System.out.println(debug + "ableToMove() => true");
      states.add(new GameLevelModel(this.currentLevelModel));
      currentLevelModel.move(direction);
      if (currentLevelModel.isLevelComplete()) {
        nextLevel();
        currentLevel++;
        view.addBar();
      }
      view.repaint();
    }
    else
      System.out.println(debug + "ableToMove() => false");

    System.out.println("======================= Level " + currentLevel + " ========================");
    System.out.println(currentLevelModel);
  }

  public void nextLevel()
  {
    if (levels.size() == 0) {
      hasMoreLevels = false;
      view.displayVictoryScreen();
      return;
    }

    states.clear();
    currentLevelModel = levels.remove();
    currentLevelModelBackup = new GameLevelModel(currentLevelModel);

    view.addModel(currentLevelModel);
    view.updatePanel();
  }

  public void restartLevel()
  {
    states.clear();
    currentLevelModel = new GameLevelModel(currentLevelModelBackup);
    view.addModel(currentLevelModel);
    view.updatePanel();
  }

  public void undoMove()
  {
    if (! states.isEmpty())
    {
      currentLevelModel = states.pop();
      view.addModel(currentLevelModel);
      view.updatePanel();
    }
  }

  public void addModels(Queue<GameLevelModel> models)
  {
    while (! models.isEmpty())
      levels.add(models.remove());
    currentLevelModel = levels.remove();
    currentLevelModelBackup = new GameLevelModel(currentLevelModel);
  }

  public GameLevelModel getCurrentLevelModel()
  {
    return currentLevelModel;
  }

  public void addView(View v)
  {
    view = v;
  }

  public KeyListener getGamePanelKeyListener()
  {
    return
      new KeyAdapter() {
        public void keyPressed(KeyEvent e)
        {
          System.out.println("========================================================");
          int keyCode = e.getKeyCode();
          if (keyCode == KeyEvent.VK_UP) {
            System.out.println("Pressed: UP");
            move("UP");
          }
          else if (keyCode == KeyEvent.VK_DOWN) {
            System.out.println("Pressed: DOWN");
            move("DOWN");
          }
          else if (keyCode == KeyEvent.VK_LEFT) {
            System.out.println("Pressed: LEFT");
            move("LEFT");
          }
          else if (keyCode == KeyEvent.VK_RIGHT) {
            System.out.println("Pressed: RIGHT");
            move("RIGHT");
          }
          else if (keyCode == KeyEvent.VK_R) {
            System.out.println("Pressed: R");
            restartLevel();
          }
          else if (keyCode == KeyEvent.VK_U) {
            System.out.println("Pressed: R");
            undoMove();
          }        
        }
      };
  }

  JButton getRestartButton()
  {
    JButton b = new JButton();
    b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    b.setPreferredSize(new Dimension(120, 40));

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[GameplayController] Reset button pressed.");
        restartLevel();
      }
    });

    return b;
  }

  JButton getUndoButton()
  {
    JButton b = new JButton();
    b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    b.setPreferredSize(new Dimension(120, 40));
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[GameplayController] Undo button pressed.");
        undoMove();
      }
    });

    return b;
  }

  JButton getQuitButton()
  {
    JButton b = new JButton();
    b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    b.setPreferredSize(new Dimension(120, 40));

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[GameplayController] Quit button pressed.");
        System.exit(0);
      }
    });

    return b;
  }

  JButton getMenuButton()
  {
    JButton b = new JButton();
    b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

    try {
      Image img = ImageIO.read(getClass().getResource("startbutton.png"));
      b.setIcon(new ImageIcon(img));
      b.setPreferredSize(new Dimension(img.getWidth(null)+50, 70));

    } catch(Exception e) {
      System.out.println("[ERROR] unable to assign startbutton.png to startbutton");
    }
    
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[GameplayController] Menu Button pressed.");
        MenuDialog menuDialog = new MenuDialog(view, true);
      }
    });

    return b;
  }

  public void displayGame()
  {
    System.out.println("[GameplayController::displayGame] Repainting the view.");
    view.repaint();
  }
}