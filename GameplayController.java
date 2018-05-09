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

  public GameplayController()
  {
    currentLevelModel = new GameLevelModel();
    currentLevelModelBackup = new GameLevelModel();
    levels = new LinkedList<GameLevelModel>();
    states = new Stack<GameLevelModel>();
  }

  public void move(String direction)
  {
    System.out.println("========================================================");
    if (currentLevelModel.ableToMove(direction)) {
      System.out.println("[debug] ableToMove() => true");
      currentLevelModel.move(direction);
    }
    else
    System.out.println("[debug] ableToMove() => false");
    view.repaint();
  }

  public GameLevelModel getNextLevel()
  {
    currentLevelModel = levels.remove();
    if (levels.size() == 0)
      hasMoreLevels = false;
    return currentLevelModel;
  }

  public void restartLevel()
  {
    currentLevelModel = currentLevelModelBackup;
  }

  public void undoMove()
  {
    currentLevelModel = states.pop();
  }

  public void addModel(GameLevelModel m)
  {
    currentLevelModel = m;
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
        }
      };
  }

  public void displayGame()
  {
    //System.out.println(currentLevelModel);
    view.repaint();
  }


  public static void main(String[] args)
  {
    LevelBuilder builder = new LevelBuilder();
    GameplayController controller = new GameplayController();
    for (int i = 0; i < args.length; i++) {
      GameLevelModel model = builder.buildOneLevel(args[i]);
      controller.addModel(model);
    }
    Scanner scanner = new Scanner(System.in);
    while (controller.hasMoreLevels) {
      GameLevelModel model = controller.getNextLevel();
      while (! model.isLevelComplete())
      {
        System.out.println(controller.currentLevelModel);
        String direction = scanner.nextLine();
        if (direction.equals("w"))
          controller.move("UP");
        else if (direction.equals("s"))
          controller.move("DOWN");
        else if (direction.equals("a"))
          controller.move("LEFT");
        else if (direction.equals("d"))
          controller.move("RIGHT");
        else
          System.out.println("[debug] Invalid move! use a w s d");
      }
      System.out.println("\nYou win!\n");
    }
    scanner.close();
  }
}