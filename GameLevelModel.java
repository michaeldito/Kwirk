import java.util.ArrayList;

import java.io.*;
import java.awt.*;

public class GameLevelModel
{
    private View view;

    public final static int NUM_ROWS = 16;
    public final static int NUM_COLS = 18;

    private GameSquare[][] grid;
    private int playerRow;
    private int playerColumn;
    private ArrayList<Holes> holes;
    private ArrayList<Turnstiles> turnstiles;
    private ArrayList<Blocks> blocks;

    private Boolean isLevelComplete;
    public Boolean isLevelComplete() { return isLevelComplete; }

    public Player getPlayer() { return (Player) grid[playerRow][playerColumn]; }
    public void setPlayerCharacter(String character)
    {
        getPlayer().setShape(character);
    }

    public GameLevelModel()
    { 
        grid = new GameSquare[NUM_ROWS][NUM_COLS];
        playerRow = 0;
        playerColumn = 0;
        holes = new ArrayList<Holes>();
        turnstiles = new ArrayList<Turnstiles>();
        blocks = new ArrayList<Blocks>();
        isLevelComplete = false;
    }

    public GameLevelModel(GameLevelModel model)
    {
        grid = new GameSquare[NUM_ROWS][NUM_COLS];
        playerRow = 0;
        playerColumn = 0;
        holes = new ArrayList<Holes>();
        turnstiles = new ArrayList<Turnstiles>();
        blocks = new ArrayList<Blocks>();
        isLevelComplete = false;
        init(model.grid, model.playerRow, model.playerColumn, model.holes, model.turnstiles, model.blocks);
    }

    public void init(GameSquare[][] g, int pR, int pC, 
        ArrayList<Holes> hc, 
        ArrayList<Turnstiles> tc, 
        ArrayList<Blocks> bc)
    {
        setGrid(g);
        setPlayerPosition(pR, pC);
        setHoles(hc);
        setTurnstiles(tc);
        setBlocks(bc);
    }

    public void addView(View v)
    {
        view = v;
    }

    public void setGrid(GameSquare[][] g)
    {
        for (int i = 0; i < NUM_ROWS; i++)
            for (int j = 0; j < NUM_COLS; j++)
                grid[i][j] = g[i][j];
    }

    public void setPlayerPosition(int pR, int pC)
    {
        playerRow = pR;
        playerColumn = pC;
    }

    // public void addToGameSquareCollection(String type, ArrayList<GameSquare> gameSquares)
    // {
    //     GameSquareCollection collection;
    //     if (type.equals("HOLES"))
    //         collection = new Holes();
    //     else if (type.equals("TURNSTILES"))
    //         collection = new Turnstiles();
    //     else if (type.equals("BLOCKS"))
    //         collection = new Blocks();
    //     else {
    //         System.out.println("[debug]  Error in GameLevelModel::addGameSquareCollection(type, squares) - Invalid GameSquareCollection type");
    //         System.out.println("[debug]  addGameSquareCollection(type, squares) failed");
    //         return;
    //     }

    //     for (int j = 0; j < gameSquares.size(); j++) {
    //         GameSquare gs = gameSquares.get(i);
    //         if (type.equals("TURNSTILES")) 
    //         {
    //             if (gs.getType().equals(GameSquare.SquareType.TURNSTILE)) {
    //                 collection.add((Turnstile) gs);
    //             }
    //             else {
    //                 collection.add((Pivot) gs);
    //             }
    //         }
    //         else if (type.equals("HOLES"))
    //             collection.add((Hole) gs);
    //         else if (type.equals("BLOCKS"))
    //             collection.add((Block) gs);
    //     }
    // }

    // public void setGameSquareCollection(String type, ArrayList<GameSquareCollection> gameSquareCollection)
    // {
    //     ArrayList<GameSquareCollection> collection;
    //     if (type.equals("HOLES"))
    //         collection = holes;
    //     else if (type.equals("TURNSTILES"))
    //         collection = turnstiles;
    //     else if (type.equals("BLOCKS"))
    //         collection = blocks;
    //     else 
    //     {
    //         System.out.println("[debug]  Error in GameLevelModel::setGameSquareCollection(type, squares) - Invalid GameSquareCollection type");
    //         System.out.println("[debug]  setGameSquareCollection(type, squares) failed");
    //         return;
    //     }

    //     for (int i = 0; j < gameSquareCollection.size(); i++) 
    //     {
    //         if (type.equals("HOLES"))
    //             collection.add(new Holes());
    //         else if (type.equals("TURNSTILES"))
    //             collection.add(new Turnstiles());
    //         else if (type.equals("BLOCKS"))
    //             collection.add(new Blocks());
    //         for (int j = 0; j < gameSquareCollection.get(i).size(); j++)
    //             collection.get(i).add(gameSquareCollection.get(i).get(j));
    //     }
    // }

    public void setHoles(ArrayList<Holes> hc)
    {
        for (int i = 0; i < hc.size(); i++) {
            holes.add(new Holes());
            for (int j = 0; j < hc.get(i).size(); j++) {
                holes.get(i).add(hc.get(i).get(j));
            }
        }
    }

    public void addHoles(ArrayList<GameSquare> hc)
    {
        Holes c = new Holes();
        for (int i = 0; i < hc.size(); i++)
            c.add((Hole) hc.get(i));
        holes.add(c);
    }

    public void setTurnstiles(ArrayList<Turnstiles> tc)
    {
        for (int i = 0; i < tc.size(); i++) {
            turnstiles.add(new Turnstiles());
            for (int j = 0; j < tc.get(i).size(); j++) {
                turnstiles.get(i).add(tc.get(i).get(j));
            }
        }
    }

    public void addTurnstiles(ArrayList<GameSquare> tc)
    {
        Turnstiles c = new Turnstiles();
        for (int i = 0; i < tc.size(); i++) {
            if (tc.get(i).getType().equals(GameSquare.SquareType.TURNSTILE)) {
                c.add((Turnstile) tc.get(i));
            }
            else {
                c.add((Pivot) tc.get(i));
            }
        }
        turnstiles.add(c);
    }

    public void setBlocks(ArrayList<Blocks> bc)
    {
        blocks = new ArrayList<Blocks>();
        for (int i = 0; i < bc.size(); i++) {
            blocks.add(new Blocks());
            for (int j = 0; j < bc.get(i).size(); j++) {
                blocks.get(i).add(bc.get(i).get(j));
            }
        }
    }

    public void addBlocks(ArrayList<GameSquare> bc)
    {
        Blocks c = new Blocks();
        for (int i = 0; i < bc.size(); i++)
            c.add(bc.get(i));
        blocks.add(c);
    }

    private int getDestinationRow(String direction, int row)
    {
        if (direction.equals("UP"))
            return row - 1;
        else if (direction.equals("DOWN"))
            return row + 1;
        else if (direction.equals("LEFT"))
            return row;
        else if (direction.equals("RIGHT"))
            return row;
        else 
        {
            System.out.println("[ERROR] IN GameLevelModel::getDestinationRow - invalid direction");
            return -1;
        }
    }

    private int getDestinationColumn(String direction, int column)
    {
        if (direction.equals("UP"))
            return column;
        else if (direction.equals("DOWN"))
            return column;
        else if (direction.equals("LEFT"))
            return column - 1;
        else if (direction.equals("RIGHT"))
            return column + 1;
        else 
        {
            System.out.println("[ERROR] in GameLevelModel::getDestinationColumn - invalid direction");
            return -1;
        } 
    }public Boolean ableToMove(String direction)
    {
        String debug = "[debug] [GameLevelModel::ableToMove] ";
        System.out.println(debug + "Checking if able to move " + direction);
        System.out.println(debug + "Player is at (" + playerRow + ", " + playerColumn + ")");

        int destinationRow = getDestinationRow(direction, playerRow);
        int destinationCol = getDestinationColumn(direction, playerColumn);
        
        System.out.println(debug + "Destination (" + destinationRow + ", " + destinationCol + ")");

        Boolean destinationIsInBounds = (destinationRow >= 0 && destinationRow < NUM_ROWS) && (destinationCol >= 0 && destinationCol < NUM_COLS);
        if (destinationIsInBounds)
        {
            System.out.println(debug + "Destination is in bounds.");
            GameSquare destination = grid[destinationRow][destinationCol];
            GameSquare.SquareType destinationType = destination.getType();
            if (destinationType.equals(GameSquare.SquareType.WALL)) {
                System.out.println(debug + "Destination is a WALL");
                return false;
            }
            if (destinationType.equals(GameSquare.SquareType.BLOCK))
            {
                System.out.println(debug + "Destination is a BLOCK.\n" + debug + "Determining if able to shift blocks.");
                Blocks b = findBlocksContaining(destinationRow, destinationCol);
                boolean canShift = b.ableToShift(direction);
                System.out.println(debug + "ableToShift: " + canShift);
                return canShift;
            }
            if (destinationType.equals(GameSquare.SquareType.TURNSTILE))
            {
                System.out.println(debug + "Destination is a TURNSTILE.\n" + debug + "Determining if able to rotate.");
                Turnstiles t = findTurnstilesContaining(destinationRow, destinationCol);
                Pivot p = t.getPivotSquare();
                // if pushing up and pivot is to the right -> clockwise
                // if pushing up and pivot is to the left -> counter-clockwise
                if (direction.equals("UP"))
                {
                    if ((p.getColumn() > destinationCol && t.ableToRotateClockwise()) || (p.getColumn() < destinationCol && t.ableToRotateCounterClockwise()))
                        return true;
                }
                // if pushing down and pivot is to the right -> counter-clockwise
                // if pushing down and pivot is to the left -> clockwise
                else if (direction.equals("DOWN"))
                {
                    if ((p.getColumn() > destinationCol && t.ableToRotateCounterClockwise()) || (p.getColumn() < destinationCol && t.ableToRotateClockwise()))
                        return true;
                }
                // if pushing right and pivot is below -> clockwise
                // if pushing right and pivot is above -> counter-clockwise
                else if (direction.equals("RIGHT"))
                {
                    if ((p.getRow() > destinationRow && t.ableToRotateClockwise()) || (p.getRow() < destinationRow && t.ableToRotateCounterClockwise()))
                        return true;
                }
                // if pushing left and pivot is below -> counter-clockwise
                // if pushing right and pivot is above -> clockwise
                else if (direction.equals("LEFT"))
                {
                    if ((p.getRow() > destinationRow && t.ableToRotateCounterClockwise()) || (p.getRow() < destinationRow && t.ableToRotateClockwise()))
                        return true;
                }
                return false; // not able to rotate turnstiles
            }
            else if (destinationType.equals(GameSquare.SquareType.STAIRS))
            {
                System.out.println(debug + "Destination is the STAIRS!");
                return true;
            }
            else if (destinationType.equals(GameSquare.SquareType.EMPTY))
            {
                System.out.println(debug + "Destination is an EMPTY.");
                return true;
            }
            else // hole, or player is pushing on a pivot
            {
                System.out.println(debug + "Destination is a " + GameSquare.TypeStrings[destinationType.getValue()] + ".");
                return false;
            }
        }
        else
        {
            System.out.println("[ERROR] in GameLevelModel::ableToMove - destination is out of bounds");
            return false;
        }
    }

    private void movePlayer(int destinationRow, int destinationCol)
    {
        String debug = "[debug] [GameLevelModel::movePlayer] ";
        System.out.println(debug + "Moving player to (" + destinationRow + ", " + destinationCol + ")");
        grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.PLAYER, destinationRow, destinationCol);
        grid[playerRow][playerColumn] = GameSquare.create(GameSquare.SquareType.EMPTY, playerRow, playerColumn);
        playerRow = destinationRow;
        playerColumn = destinationCol;
    }

    public void move(String direction)
    {
        int destinationRow = getDestinationRow(direction, playerRow);
        int destinationCol = getDestinationColumn(direction, playerColumn);
        GameSquare.SquareType destinationType = grid[destinationRow][destinationCol].getType();
        int turnstileSize = 0;

        if (destinationType.equals(GameSquare.SquareType.BLOCK))
        {
            Blocks b = findBlocksContaining(destinationRow, destinationCol);
            b.shiftBlocks(direction);
        }
        else if (destinationType.equals(GameSquare.SquareType.TURNSTILE))
        {
            Turnstiles t = findTurnstilesContaining(destinationRow, destinationCol);
            turnstileSize = t.size();
            if (turnstileSize < 5) {
                Pivot p = t.getPivotSquare();
                // if pushing up and pivot is to the right -> clockwise
                // if pushing up and pivot is to the left -> counter-clockwise
                if (direction.equals("UP"))
                {
                    if (p.getColumn() > destinationCol) 
                        t.rotateClockwise();
                    else
                        t.rotateCounterClockwise();
                }
                // if pushing down and pivot is to the right -> counter-clockwise
                // if pushing down and pivot is to the left -> clockwise
                else if (direction.equals("DOWN"))
                {
                    if (p.getColumn() > destinationCol)
                        t.rotateCounterClockwise();
                    else
                        t.rotateClockwise();
                }
                // if pushing right and pivot is below -> clockwise
                // if pushing right and pivot is above -> counter-clockwise
                else if (direction.equals("RIGHT"))
                {
                    if (p.getRow() > destinationRow)
                        t.rotateClockwise();
                    else
                        t.rotateCounterClockwise();
                }
                // if pushing left and pivot is below -> counter-clockwise
                // if pushing right and pivot is above -> clockwise
                else if (direction.equals("LEFT"))
                {
                    if (p.getRow() > destinationRow)
                        t.rotateCounterClockwise();
                    else 
                        t.rotateClockwise();                
                }
            }
        }
        else if (destinationType.equals(GameSquare.SquareType.STAIRS))
        {
            isLevelComplete = true;
        }
        // Now move the player, it's a double move if they rotated a quadruple turnstile (4 TS + 1 PV = 5)
        if (turnstileSize == 5)
            if (direction.equals("UP"))
                destinationRow--;
            else if (direction.equals("DOWN"))
                destinationRow++;
            else if (direction.equals("LEFT"))
                destinationCol--;
            else // RIGHT
                destinationCol++;

        // If it was a triple turnstile and the players destination is currently occupied by a turnstile
        // then it is also a double move.
        destinationType = grid[destinationRow][destinationCol].getType();
        if (turnstileSize == 3 || turnstileSize == 4)
            if (direction.equals("UP") && destinationType.equals(GameSquare.SquareType.TURNSTILE))
                destinationRow--;
            else if (direction.equals("DOWN") && destinationType.equals(GameSquare.SquareType.TURNSTILE))
                destinationRow++;
            else if (direction.equals("LEFT") && destinationType.equals(GameSquare.SquareType.TURNSTILE))
                destinationCol--;
            else if (direction.equals("RIGHT") && destinationType.equals(GameSquare.SquareType.TURNSTILE))// RIGHT
                destinationCol++;

        movePlayer(destinationRow, destinationCol);

    }

    public Turnstiles findTurnstilesContaining(int r, int c)
    {
        for (int i = 0; i < turnstiles.size(); i++)
            for (int j = 0; j < turnstiles.get(i).size(); j++)
                if (turnstiles.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.TURNSTILE, r, c)))
                    return turnstiles.get(i);
        System.out.println("[ERROR] in GameLevelModel::findTurnstilesContaining - no turnstile found.");
        return new Turnstiles();
    }

    public Blocks findBlocksContaining(int r, int c)
    {
        for (int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(i).size(); j++)
                if (blocks.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.BLOCK, r, c)))
                    return blocks.get(i);
        System.out.println("[ERROR] in GameLevelModel::findBlocksContaining - no block found.");
        return new Blocks();
    }

    public Holes findHolesContaining(int r, int c)
    {
        for (int i = 0; i < holes.size(); i++)
            for (int j = 0; j < holes.get(i).size(); j++)
                if (holes.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.HOLE, r, c)))
                    return holes.get(i);
        System.out.println("Error in findHolesContaining(r, c) - no hole found.");
        return new Holes();
    }

    public String toString()
    {
        // grid
        String gridStr = "   ";
        for (int i = 0; i < NUM_COLS; i++)
            if (i < 10)
                gridStr += i + "  ";
            else
                gridStr += i + " ";
        gridStr += "\n";
        for (int i = 0; i < NUM_ROWS; i++) {
            if (i < 10)
                gridStr += i + "  ";
            else
                gridStr += i + " ";            
            for (int j = 0; j < NUM_COLS; j++) {
                gridStr += grid[i][j].getStrValue() + " ";
            }
            gridStr += i + "\n";
        }
        gridStr += "   ";
        for (int i = 0; i < NUM_COLS; i++)
            if (i < 10)
                gridStr += i + "  ";
            else
                gridStr += i + " ";
        gridStr += "\n";

        // holes, turnstiles, blocks
        String holesStr2 = "";
        String blocksStr2 = "";
        String turnstilesStr2 = "";
        holesStr2 += "[ ";
        for (int i = 0; i < holes.size(); i++) {
            holesStr2 += "\n  [ ";
            for (int j = 0; j < holes.get(i).size(); j++) {
                holesStr2 += "(" + holes.get(i).get(j).getRow() + ", " + holes.get(i).get(j).getColumn() + ") ";
            }
            holesStr2 += "]";
        }
        holesStr2 += "\n]";
        blocksStr2 += "[ ";
        for (int i = 0; i < blocks.size(); i++) {
            blocksStr2 += "\n  [ ";
            for (int j = 0; j < blocks.get(i).size(); j++) {
                blocksStr2 += "(" + blocks.get(i).get(j).getRow() + ", " + blocks.get(i).get(j).getColumn() + ") ";
            }
            blocksStr2 += "]";
        }
        blocksStr2 += "\n]";
        turnstilesStr2 += "[ ";
        for (int i = 0; i < turnstiles.size(); i++) {
            turnstilesStr2 += "\n  [ ";
            for (int j = 0; j < turnstiles.get(i).size(); j++) {
                if (turnstiles.get(i).get(j).getType().equals(GameSquare.SquareType.PIVOT))
                    turnstilesStr2 += "P(" + turnstiles.get(i).get(j).getRow() + ", " + turnstiles.get(i).get(j).getColumn() + ") ";
                else
                    turnstilesStr2 += "(" + turnstiles.get(i).get(j).getRow() + ", " + turnstiles.get(i).get(j).getColumn() + ") ";
            }
            turnstilesStr2 += "]";
        }
        turnstilesStr2 += "\n]";

        return "========================================================\n[GameLevelModel]\n" + 
        "\n> " + "Grid\n" + gridStr +
        "\nPlayer Location: (" + playerRow + ", " + playerColumn +")" +
        "\nHoles:\n" + holesStr2 + 
        "\nBlocks:\n" + blocksStr2 + 
        "\nTurnstiles:\n" + turnstilesStr2;   
    }

    public void display(Graphics2D g2)
    {
        for (int i = 0; i < NUM_ROWS; i++)
            for (int j = 0; j < NUM_COLS; j++)
                grid[i][j].paintComponent(g2);

        for (int i = 0; i < holes.size(); i++)        
            holes.get(i).paintBorders(g2);
        for (int i = 0; i < turnstiles.size(); i++)        
            turnstiles.get(i).paintBorders(g2);
        for (int i = 0; i < blocks.size(); i++)        
            blocks.get(i).paintBorders(g2);

    }

    public abstract class GameSquareCollection
    {
        protected ArrayList<GameSquare> collection;

        public GameSquareCollection()
        {
            collection = new ArrayList<GameSquare>();
        }

        protected void add(GameSquare g)
        {
            collection.add(g);
        }

        protected void clear()
        {
            collection = new ArrayList<GameSquare>();
        }

        protected int size()
        {
            return collection.size();
        }

        protected GameSquare get(int i)
        {
            return collection.get(i);
        }

        protected void set(int i, GameSquare gs)
        {
            collection.set(i, gs);
        }

        protected Boolean contains(GameSquare gs)
        {
            return collection.contains(gs);
        }

        public String toString()
        {
            String collectionStr = "";
            for (int i = 0; i < collection.size(); i++)
                collectionStr += collection.get(i) + "\n";
            return ">>>> Size: " + collection.size() + "\n" + collectionStr;
        }

        protected void paintBorders(Graphics2D g2)
        {
            //String debug = "[debug] [GameSquareCollection::paintBorders] ";
            for (int i = 0; i < collection.size(); i++) 
            {
                GameSquare gs = collection.get(i);

                boolean noGSAbove = true;
                boolean noGSBelow = true;
                boolean noGSLeft = true;
                boolean noGSRight = true;

                for (int j = 0; j < collection.size(); j++) 
                {
                    GameSquare gs2 = collection.get(j);
                    if (i != j)
                        if (gs.getColumn() == gs2.getColumn() && gs2.getRow() < gs.getRow())
                            noGSAbove = false;
                        if (gs.getColumn() == gs2.getColumn() && gs2.getRow() > gs.getRow())
                            noGSBelow = false;
                        if (gs.getRow() == gs2.getRow() && gs2.getColumn() < gs.getColumn())
                            noGSLeft = false;
                        if (gs.getRow() == gs2.getRow() && gs2.getColumn() > gs.getColumn())
                            noGSRight = false;
                }
                Stroke previousSroke = g2.getStroke();
                g2.setStroke(new BasicStroke(4.0f));
                if (noGSAbove) 
                {
                 //   System.out.println(debug + "Painting top border on gs at (" + gs.getRow() + ", " + gs.getColumn() + ")");
                    gs.paintTopBorder(g2);
                }
                if (noGSBelow) 
                {
                 //   System.out.println(debug + "Painting bottom border on gs at (" + gs.getRow() + ", " + gs.getColumn() + ")");
                    gs.paintBottomBorder(g2);
                }
                if (noGSLeft) 
                {
                 //   System.out.println(debug + "Painting left border on gs at (" + gs.getRow() + ", " + gs.getColumn() + ")");
                    gs.paintLeftBorder(g2);
                }
                if (noGSRight) {
                 //   System.out.println(debug + "Painting right border on gs at (" + gs.getRow() + ", " + gs.getColumn() + ")");
                    gs.paintRightBorder(g2);
                }
                g2.setStroke(previousSroke);
            }
        }
    }

    public class Turnstiles extends GameSquareCollection
    {
        public Turnstiles()
        {
            super();
        }

        public Boolean ableToRotateClockwise()
        {
            String debug = "[debug] [Turnstiles::ableToRotateClockwise] ";
            System.out.println(debug + "Determining if able to rotate clockwise.");
            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            int passThroughRow, passThroughCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn()) // Turnstile is to the left of the pivot
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                        passThroughRow = getDestinationRow("UP", gs.getRow());
                        passThroughCol = gs.getColumn();  
                    }
                    else if (gs.getColumn() > p.getColumn()) // Turnstile is to the right of the pivot
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                        passThroughRow = getDestinationRow("DOWN", gs.getRow());
                        passThroughCol = gs.getColumn();  
                    }
                    else if (gs.getRow() < p.getRow()) // Turnstile is above the pivot
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                        passThroughRow = gs.getRow();
                        passThroughCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow()) // Turnstile is below the pivot
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                        passThroughRow = gs.getRow();
                        passThroughCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    GameSquare destination = grid[destinationRow][destinationCol];
                    GameSquare passThrough = grid[passThroughRow][passThroughCol];
                    GameSquare.SquareType destinationType = destination.getType();
                    GameSquare.SquareType passThroughType = passThrough.getType();

                    // Is the destination a turnstile in another turnstile collection?
                    if (destinationType.equals(GameSquare.SquareType.TURNSTILE))
                    {
                        Boolean collectionContainsTurnstileDestination = false;
                        for (int j = 0; j < collection.size(); j++)
                        {
                            if (collection.get(j).equalTo(destinationRow, destinationCol, destinationType))
                                collectionContainsTurnstileDestination = true;
                        }
                        System.out.println(debug + "Collection contains turnstile destination: " + collectionContainsTurnstileDestination);
                        if (! collectionContainsTurnstileDestination)
                            return false;
                    }
    
                    Boolean destinationIsGood = (destinationType.equals(GameSquare.SquareType.EMPTY) || 
                                                 destinationType.equals(GameSquare.SquareType.HOLE)  ||
                                                 destinationType.equals(GameSquare.SquareType.TURNSTILE));
                                                 // at this point we've vertified the turnstile is in this collection

                    System.out.println(debug + "destinationIsGood: " + destinationIsGood);

                    Boolean passThroughIsGood = (passThroughType.equals(GameSquare.SquareType.EMPTY) || 
                                                 passThroughType.equals(GameSquare.SquareType.HOLE)  ||
                                                 passThroughType.equals(GameSquare.SquareType.PLAYER) &&
                                                 ! passThroughType.equals(GameSquare.SquareType.TURNSTILE));

                    System.out.println(debug + "passThroughIsGood: " + passThroughIsGood);

                    Boolean ableToRotate = destinationIsGood && passThroughIsGood;

                    System.out.println(debug + "ableToRotateClockwise() => " + ableToRotate);

                    if (! ableToRotate)
                        return false;
                }
            }
            return true;
        }

        public Boolean ableToRotateCounterClockwise()
        {
            String debug = "[debug] [Turnstiles::ableToRotateCounterClockwise] ";
            System.out.println(debug + "Determining if able to rotate counter clockwise.");

            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            int passThroughRow, passThroughCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn()) // TS to the left of P
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                        passThroughRow = getDestinationRow("DOWN", gs.getRow());
                        passThroughCol = gs.getColumn(); 
                    }
                    else if (gs.getColumn() > p.getColumn()) // TS to the right of P
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                        passThroughRow = getDestinationRow("UP", gs.getRow());
                        passThroughCol = gs.getColumn(); 
                    }
                    else if (gs.getRow() < p.getRow()) // TS above P
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                        passThroughRow = gs.getRow();
                        passThroughCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow()) // TS below P
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                        passThroughRow = gs.getRow();
                        passThroughCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }

                    GameSquare destination = grid[destinationRow][destinationCol];
                    GameSquare passThrough = grid[passThroughRow][passThroughCol];
                    
                    GameSquare.SquareType destinationType = destination.getType();
                    GameSquare.SquareType passThroughType = passThrough.getType();

                    // Is the destination a turnstile in another turnstile collection?
                    if (destinationType.equals(GameSquare.SquareType.TURNSTILE))
                    {
                        Boolean collectionContainsTurnstileDestination = false;
                        for (int j = 0; j < collection.size(); j++)
                        {
                            if (collection.get(j).equalTo(destinationRow, destinationCol, destinationType))
                                collectionContainsTurnstileDestination = true;
                        }
                        System.out.println(debug + "Collection contains turnstile destination: " + collectionContainsTurnstileDestination);
                        if (! collectionContainsTurnstileDestination)
                            return false;
                    }

                    Boolean destinationIsGood = (destinationType.equals(GameSquare.SquareType.EMPTY) || 
                                                 destinationType.equals(GameSquare.SquareType.HOLE)  ||
                                                 destinationType.equals(GameSquare.SquareType.TURNSTILE));

                    System.out.println(debug + "destinationIsGood: " + destinationIsGood);

                    Boolean passThroughIsGood = (passThroughType.equals(GameSquare.SquareType.EMPTY) || 
                                                 passThroughType.equals(GameSquare.SquareType.HOLE)  ||
                                                 passThroughType.equals(GameSquare.SquareType.PLAYER) &&
                                                 ! passThroughType.equals(GameSquare.SquareType.TURNSTILE));

                    System.out.println(debug + "passThroughIsGood: " + passThroughIsGood);

                    Boolean ableToRotate = destinationIsGood && passThroughIsGood;

                    System.out.println(debug + "ableToRotateCounterClockwise() => " + ableToRotate);

                    if (! ableToRotate)
                        return false;
                }
            }
            return true;
        }

        public void rotateClockwise()
        {
            String debug = "[debug] [Turnstiles::rotateClockwise] ";
            System.out.println(debug + "Rotating clockwise.");

            Turnstiles rotatedTurnstiles = new Turnstiles();
            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn()) // TS is left of PV
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else if (gs.getColumn() > p.getColumn()) // TS is right of PV
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else if (gs.getRow() < p.getRow()) // TS is above PV
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow()) // TS is below PV
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    rotatedTurnstiles.add(GameSquare.create(GameSquare.SquareType.TURNSTILE, destinationRow, destinationCol));
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, gs.getRow(), gs.getColumn());
                }
                else 
                {
                    Pivot p2 = (Pivot) gs;
                    rotatedTurnstiles.add(p2);
                }
            }

            collection.clear();

            for (int i = 0; i < rotatedTurnstiles.size(); i++)
            {
                GameSquare gs = rotatedTurnstiles.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE)) 
                {
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.TURNSTILE, gs.getRow(), gs.getColumn());
                    collection.add((Turnstile) gs);
                }
                else 
                {
                    collection.add((Pivot) gs);
                }
            }
        }

        public void rotateCounterClockwise()
        {
            String debug = "[debug] [Turnstiles::rotateClockwise] ";
            System.out.println(debug + "Rotating counter clockwise");

            Turnstiles rotatedTurnstiles = new Turnstiles();
            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn()) // TS is left of PV
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else if (gs.getColumn() > p.getColumn()) // TS is right of PV
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else if (gs.getRow() < p.getRow()) // TS is above PV
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow()) // TS is below PV
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    rotatedTurnstiles.add(GameSquare.create(GameSquare.SquareType.TURNSTILE, destinationRow, destinationCol));
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, gs.getRow(), gs.getColumn());
                }
                else 
                {
                    Pivot p2 = (Pivot) gs;
                    rotatedTurnstiles.add(p2);
                }
            }
            
            collection.clear();

            for (int i = 0; i < rotatedTurnstiles.size(); i++)
            {
                GameSquare gs = rotatedTurnstiles.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE)) 
                {
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.TURNSTILE, gs.getRow(), gs.getColumn());
                    collection.add((Turnstile) gs);
                }
                else 
                {
                    collection.add((Pivot) gs);
                }
            }
        }

        public Pivot getPivotSquare()
        {
            for (int i = 0; i < collection.size(); i++)
                if (collection.get(i).getType().equals(GameSquare.SquareType.PIVOT))
                    return (Pivot) collection.get(i);
            System.out.println("[ERROR] in GameLevelModel::getPivotSquare - no pivot found.");
            return new Pivot();
        }

        public String toString()
        {
            return "[Turnstile Collection]\n" + super.toString();
        }

    }

    public class Holes extends GameSquareCollection
    {
        public Holes()
        {
            super();
        }

        public void fillHole(Hole hole)
        {
            for (int i = 0; i < collection.size(); i++) {
                Hole h = (Hole) collection.get(i);
                if (h.equals(hole)) {
                    System.out.println("Filling hole at (" + h.getRow() + ", " + h.getColumn() + ")");
                    collection.remove(i);
                    grid[h.getRow()][h.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, h.getRow(), h.getColumn());
                }
            }
        }

        public String toString()
        {
            return "[Hole Collection]\n" + super.toString();
        }
    }

    public class Blocks extends GameSquareCollection
    {
        public Blocks()
        {
            super();
        }

        public void shiftBlocks(String direction)
        {
            String debug = "[debug] [Blocks::shiftBlocks] ";

            System.out.println(debug + "Shifting blocks: " + direction);
            System.out.println(debug + "Block size: " + collection.size());
            int destinationRow, destinationCol;
            ArrayList<GameSquare> blocksToRemove = new ArrayList<GameSquare>();
            ArrayList<GameSquare> newBlocks = new ArrayList<GameSquare>();
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare b = collection.get(i);
                System.out.println(debug + "Block location (" + b.getRow() + ", " + b.getColumn() + ")");
                destinationRow = getDestinationRow(direction, b.getRow());
                destinationCol = getDestinationColumn(direction, b.getColumn());
                System.out.println(debug + "Block destination (" + destinationRow + ", " + destinationCol + ")");
                if (grid[destinationRow][destinationCol].getType().equals(GameSquare.SquareType.HOLE))
                {
                    System.out.println(debug + "Destination is a HOLE.");
                    Holes h = findHolesContaining(destinationRow, destinationCol);
                    h.fillHole((Hole) grid[destinationRow][destinationCol]);
                    blocksToRemove.add(b);
                    grid[b.getRow()][b.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, b.getRow(), b.getColumn());
                } 
                else
                {
                    System.out.println(debug + "Destination is a " + grid[destinationRow][destinationCol].getClass());
                    System.out.println(debug + "Creating a block at (" + destinationRow + ", " + destinationCol + ")");
                    //grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.BLOCK, destinationRow, destinationCol);
                    newBlocks.add(GameSquare.create(GameSquare.SquareType.BLOCK, destinationRow, destinationCol));
                    //System.out.println(debug + "Setting collection[i] to the destination block.");
                    //collection.set(i, grid[destinationRow][destinationCol]);
                    //System.out.println(debug + "Collection[i] is now a " + collection.get(i).getClass());
                    //System.out.println(debug + "Setting block location (" + b.getRow() + ", " + b.getColumn() + ") to empty");
                    //grid[b.getRow()][b.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, b.getRow(), b.getColumn());
                    //System.out.println(debug + "Grid at block location is now " + grid[b.getRow()][b.getColumn()].getClass());
                }
            }
            for (int i = 0; i < collection.size(); i++) {
                GameSquare b = collection.get(i);
                grid[b.getRow()][b.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, b.getRow(), b.getColumn());
            }
                
            for (int i = 0; i < blocksToRemove.size(); i++) {
//                int j = collection.indexOf((Block) blocksToRemove.get(i));
                int j = collection.indexOf(blocksToRemove.get(i));

                collection.remove(j);
            }
            collection.clear();
            for (int i = 0; i < newBlocks.size(); i++) {
                GameSquare b = newBlocks.get(i);
                collection.add(GameSquare.create(GameSquare.SquareType.BLOCK, b.getRow(), b.getColumn()));
                grid[b.getRow()][b.getColumn()] = GameSquare.create(GameSquare.SquareType.BLOCK, b.getRow(), b.getColumn());
            }
        }

        public Boolean ableToShift(String direction)
        {
            String debug = "[debug] [GameLevelModel::ableToShift] ";
            System.out.println(debug + "Checking if able to shift blocks.");
            int destinationRow, destinationCol;
            Boolean validShift = true;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare b = collection.get(i);
                System.out.println(debug + "Checking " + b.getRow() + ", " + b.getColumn());
                destinationRow = getDestinationRow(direction, b.getRow());
                destinationCol = getDestinationColumn(direction, b.getColumn());
                GameSquare destination = grid[destinationRow][destinationCol];

                if (destination.getType().equals(GameSquare.SquareType.BLOCK)) {
                    boolean collectionContainsBlockDestination = false;
                    for (int j = 0; j < collection.size(); j++)  {
                        if (collection.get(j).equalTo(destinationRow, destinationCol, destination.getType()))
                            collectionContainsBlockDestination = true;
                    }
                    System.out.println(debug + "Collection contains block destination: " + collectionContainsBlockDestination);
                    if (! collectionContainsBlockDestination)
                        return false;
                }

                validShift = destination.getType().equals(GameSquare.SquareType.EMPTY) || 
                             destination.getType().equals(GameSquare.SquareType.HOLE)  ||
                             destination.getType().equals(GameSquare.SquareType.BLOCK);
                            // we've already determined whether the block is in our collection or not

                System.out.println(debug + "VALID SHIFT:" + validShift);
                if (! validShift)
                    return false;
            }
            return true;
        }

        public String toString()
        {
            return "[Block Collection]\n" + super.toString();
        }
    }
}