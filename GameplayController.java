import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameplayController //implements KeyListener
{
  public GameLevelModel currentLevel;
  private GameLevelModel currentLevelBackup;
  private Queue<GameLevelModel> levels;
  private Stack<GameLevelModel> states;

  public GameplayController()
  {
    currentLevel = new GameLevelModel();
    currentLevelBackup = new GameLevelModel();
    levels = new LinkedList<GameLevelModel>();
    states = new Stack<GameLevelModel>();
   // addKeyListener(this);
  }

  // public void keyPressed(KeyEvent e) 
  // {
  //   int keyCode = e.getKeyCode();
  //   if (keyCode == KeyEvent.VK_DOWN)
  //     move("DOWN");
  //   if (keyCode == KeyEvent.VK_UP)
  //     move("UP");
  //   if (keyCode == KeyEvent.VK_LEFT)
  //     move("LEFT");
  //   if (keyCode == KeyEvent.VK_RIGHT)
  //     move("RIGHT");
  // }

  // public void keyReleased(KeyEvent e) {}
  // public void keyTyped(KeyEvent e) {}

  public void move(String direction)
  {
    if (currentLevel.ableToMove(direction)) {
      System.out.println("[[debug]] Valid move");
      currentLevel.move(direction);
    }
    else
      System.out.println("[[debug]] Invalid move");
  }

  public void getNextLevel()
  {
    currentLevel = levels.remove();
  }

  public void restartLevel()
  {
    currentLevel = currentLevelBackup;
  }

  public void undoMove()
  {
    currentLevel = states.pop();
  }

  public void addModel(GameLevelModel model)
  {
    currentLevel = model;
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
      System.out.println(controller.currentLevel);
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