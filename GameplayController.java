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


  public GameplayController()
  {
    currentLevelModel = new GameLevelModel();
    currentLevelModelBackup = new GameLevelModel();
    levels = new LinkedList<GameLevelModel>();
    states = new Stack<GameLevelModel>();
  }

  public void move(String direction)
  {
    if (currentLevelModel.ableToMove(direction)) {
      System.out.println("[[debug]] Valid move");
      currentLevelModel.move(direction);
    }
    else
      System.out.println("[[debug]] Invalid move");
  }

  public void getNextLevel()
  {
    currentLevelModel = levels.remove();
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
          if (keyCode == KeyEvent.VK_UP)
            move("UP");
          else if (keyCode == KeyEvent.VK_DOWN)
            move("DOWN");
          else if (keyCode == KeyEvent.VK_LEFT)
            move("LEFT");
          else if (keyCode == KeyEvent.VK_RIGHT)
            move("RIGHT");
        }
      };
  }

  public void displayGame()
  {
  }


  public static void main(String[] args)
  {
    LevelBuilder builder = new LevelBuilder();
    GameLevelModel model = builder.buildOneLevel(args[0]);
    GameplayController controller = new GameplayController();
    controller.addModel(model);
    Scanner scanner = new Scanner(System.in);
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
        System.out.println("[[debug]] Invalid move! use a w s d");
    }
    System.out.println("\nYou win!\n");
    scanner.close();
  }

}