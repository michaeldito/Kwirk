import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameplayController //implements KeyListener
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

  public void move(String direction)
  {
    String debug = "[debug] [GameplayController::move] ";
    System.out.println("========================================================");
    if (currentLevelModel.ableToMove(direction)) {
      System.out.println(debug + "ableToMove() => true");
      states.add(new GameLevelModel(this.currentLevelModel));
      currentLevelModel.move(direction);
      if (currentLevelModel.isLevelComplete()) {
        nextLevel();
        currentLevel++;
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
    states.clear();
    currentLevelModel = levels.remove();
    currentLevelModelBackup = new GameLevelModel(currentLevelModel);
    if (levels.size() == 0)
      hasMoreLevels = false;

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

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[debug] [GameplayController] Reset button pressed.");
        restartLevel();
      }
    });

    return b;
  }

  JButton getUndoButton()
  {
    JButton b = new JButton();

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[debug] [GameplayController] Undo button pressed.");
        undoMove();
      }
    });

    return b;
  }

  JButton getQuitButton()
  {
    JButton b = new JButton();

    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("[debug] [GameplayController] Quit button pressed.");
        // quit and return to main menu
      }
    });

    return b;
  }

  public void displayGame()
  {
    //System.out.println(currentLevelModel);
    System.out.println("[debug] [GameplayController::displayGame] Repainting the view.");
    view.repaint();
  }


  // public static void main(String[] args)
  // {
  //   LevelBuilder builder = new LevelBuilder();
  //   GameplayController controller = new GameplayController();
  //   for (int i = 0; i < args.length; i++) {
  //     GameLevelModel model = builder.buildOneLevel(args[i]);
  //     controller.addModel(model);
  //   }
  //   Scanner scanner = new Scanner(System.in);
  //   while (controller.hasMoreLevels) {
  //     GameLevelModel model = controller.getNextLevel();
  //     while (! model.isLevelComplete())
  //     {
  //       System.out.println(controller.currentLevelModel);
  //       String direction = scanner.nextLine();
  //       if (direction.equals("w"))
  //         controller.move("UP");
  //       else if (direction.equals("s"))
  //         controller.move("DOWN");
  //       else if (direction.equals("a"))
  //         controller.move("LEFT");
  //       else if (direction.equals("d"))
  //         controller.move("RIGHT");
  //       else
  //         System.out.println("[debug]  Invalid move! use a w s d");
  //     }
  //     System.out.println("\nYou win!\n");
  //   }
  //   scanner.close();
  // }
}