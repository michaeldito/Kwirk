import java.util.*;
import java.io.*;
import java.awt.*;

public class LevelBuilder
{
  public LevelBuilder()
  {
  }

  public static GameLevelModel buildOneLevel(String arg)
  {
    try
    {
        String debug = "[LevelBuilder::buildOneLevel] ";
        System.out.println(debug + "Beginning to build " + arg);
        GameLevelModel model = new GameLevelModel();
        GameSquare[][] grid = new GameSquare[GameLevelModel.NUM_ROWS][GameLevelModel.NUM_COLS];
        BufferedReader inFile = new BufferedReader (new FileReader (arg));
        for (int i = 0; i < GameLevelModel.NUM_ROWS; i++) 
        {
          String string = inFile.readLine();
          System.out.println(debug + "Current Line: " + string);
          String[] parts = string.split(",", 0);
          int count = parts.length;
          for (int j = 0; j < count; j++)
          {
            String squareCode = parts[j];
            if (squareCode.equals("WW"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.WALL, i, j);
            else if (squareCode.equals("BL"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.BLOCK, i, j);
            else if (squareCode.equals("HL"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.HOLE, i, j);	
            else if (squareCode.equals("TS"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.TURNSTILE, i, j);
            else if (squareCode.equals("PV"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.PIVOT, i, j);	
            else if (squareCode.equals("[]"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.EMPTY, i, j);
            else if (squareCode.equals("KW")) 
            {
                grid[i][j] = GameSquare.create(GameSquare.SquareType.PLAYER, i, j);
                System.out.println(debug + "Setting player position at (" + i + ", " + j + ")");
                model.setPlayerPosition(i, j);
            }
            else if (squareCode.equals("SR"))
                grid[i][j] = GameSquare.create(GameSquare.SquareType.STAIRS, i, j);
          }
        }
        model.setGrid(grid);

        String s1 = inFile.readLine();
        int count = Integer.parseInt(s1);
        System.out.println(debug + "There are " + count + " GameSquareCollections to construct.");
        for (int i = 0; i < count; i++)
        {
          String s2 = inFile.readLine();
          System.out.println(debug + "Current Line: " + s2);
          String[] parts = s2.split(" ", 0);
          String collectionCode = parts[0];
          if (collectionCode.equals("TURNSTILE")) 
          {
            ArrayList<GameSquare> tc = new ArrayList<GameSquare>();
            for (int j = 1; j < parts.length && ! parts[j].equals("P"); j+=2) 
            {
              int r = Integer.parseInt(parts[j]);
              int c = Integer.parseInt(parts[j+1]);
              tc.add(GameSquare.create(GameSquare.SquareType.TURNSTILE, r, c));
            }              
            int r = Integer.parseInt(parts[parts.length - 2]);
            int c = Integer.parseInt(parts[parts.length - 1]);
            tc.add(GameSquare.create(GameSquare.SquareType.PIVOT, r, c));
            model.addTurnstiles(tc);
          } 
          else if (collectionCode.equals("BLOCK")) 
          {
            ArrayList<GameSquare> bc = new ArrayList<GameSquare>();
            for (int j = 1; j < parts.length; j+=2) 
            {
              int r = Integer.parseInt(parts[j]);
              int c = Integer.parseInt(parts[j+1]);
              bc.add(GameSquare.create(GameSquare.SquareType.BLOCK, r, c));
            }
            model.addBlocks(bc);
          } 
          else if (collectionCode.equals("HOLE")) 
          {
            ArrayList<GameSquare> hc = new ArrayList<GameSquare>();
            for (int j = 1; j < parts.length; j+=2) 
            {
              int r = Integer.parseInt(parts[j]);
              int c = Integer.parseInt(parts[j+1]);
              hc.add(GameSquare.create(GameSquare.SquareType.HOLE, r, c));
            }
            model.addHoles(hc);
          } 
          else 
          {
            System.out.println("[ERROR] in LevelBuilder::buildOneLevel - error parsing collection type.");
          }
        }
      inFile.close();
      System.out.println(debug + "Complete.");
      return model;
    }
    catch (IOException e)
    {
      System.out.println ("Error reading file " + arg);
      return new GameLevelModel();
    }
    catch (NumberFormatException e)
    {
      System.out.println ("Numeric input error in " + arg);
      return new GameLevelModel();
    }
  }

  public static Queue<GameLevelModel> buildAllLevels()
  {
    //String[] fileNames = { "L11.csv", "L1.csv", "L2.csv", "L3.csv", "L4.csv", "L6.csv", "L7.csv", "L5.csv", "L8.csv", 
    //  "L9.csv", "L10.csv", "L13.csv", "L14.csv", "L12.csv" };
    String[] fileNames = { "L1.csv" };
    Queue<GameLevelModel> levels = new LinkedList<GameLevelModel>();
    for (int i = 0; i < fileNames.length; i++)
      levels.add(buildOneLevel(fileNames[i]));
    return levels;
  }
}