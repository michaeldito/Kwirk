import java.util.ArrayList;
import java.io.*;
import java.awt.*;

public class GameLevelModel
{
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
            c.add((Block) bc.get(i));
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
            System.out.println("ERROR IN GameLevelModel::getDestinationRow(dir) - invalid direction");
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
            System.out.println("ERROR IN GameLevelModel::getDestinationColumn(dir) - invalid direction");
            return -1;
        } 
    }
    public Boolean ableToMove(String direction)
    {
        int destinationRow = getDestinationRow(direction, playerRow);
        int destinationCol = getDestinationColumn(direction, playerColumn);
        
        System.out.println("[[debug]] Destination (" + destinationRow + ", " + destinationCol + ")");

        Boolean destinationIsInBounds = (destinationRow >= 0 && destinationRow < NUM_ROWS) && (destinationCol >= 0 && destinationCol < NUM_COLS);
        if (destinationIsInBounds)
        {
            System.out.println("[[debug]] Destination is in bounds");
            GameSquare destination = grid[destinationRow][destinationCol];
            GameSquare.SquareType destinationType = destination.getType();
            if (destinationType.equals(GameSquare.SquareType.WALL)) {
                System.out.println("[[debug]] Destination is a wall. Not able to move.");
                return false;
            }
            if (destinationType.equals(GameSquare.SquareType.BLOCK))
            {
                System.out.println("[[debug]] Destination is a block. Determining if able to shift blocks.");
                Blocks b = findBlocksContaining(destinationRow, destinationCol);
                boolean canShift = b.ableToShift(direction);
                System.out.println("[[debug]] ableToShift: " + canShift);
                return canShift;
            }
            if (destinationType.equals(GameSquare.SquareType.TURNSTILE))
            {
                System.out.println("[[debug]] Destination is a turnstile. Determining if able to rotate.");
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
                isLevelComplete = true;
                return true;
            }
            else if (destinationType.equals(GameSquare.SquareType.EMPTY))
            {
                return true;
            }
            else // hole, or player is pushing on a pivot
            {
                System.out.println("Able to rotate is returning false");
                return false;
            }
        }
        else
        {
            System.out.println("ERROR IN GameLevelModel::ableToMove(dir) - destination is out of bounds");
            return false;
        }
    }

    private void movePlayer(int destinationRow, int destinationCol)
    {
        System.out.println("[[debug]] Moving player to (" + destinationRow + ", " + destinationCol + ")");
        grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.PLAYER, destinationRow, destinationCol);
        grid[playerRow][playerColumn] = GameSquare.create(GameSquare.SquareType.EMPTY, playerRow, playerColumn);
        playerRow = destinationRow;
        playerColumn = destinationCol;
    }

    public void move(String direction)
    {
        int destinationRow = getDestinationRow(direction, playerRow);
        int destinationCol = getDestinationColumn(direction, playerColumn);
        GameSquare destination = grid[destinationRow][destinationCol];
        GameSquare.SquareType destinationType = destination.getType();

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
        // now move the player, it's a double move if they rotated a quadruple turnstile (4 TS + 1 PV = 5)
        if (turnstileSize == 5)
            if (direction.equals("UP"))
                destinationRow--;
            else if (direction.equals("DOWN"))
                destinationRow++;
            else if (direction.equals("LEFT"))
                destinationCol--;
            else // RIGHT
                destinationCol++;

        movePlayer(destinationRow, destinationCol);

    }

    public Turnstiles findTurnstilesContaining(int r, int c)
    {
        for (int i = 0; i < turnstiles.size(); i++)
            for (int j = 0; j < turnstiles.get(i).size(); j++)
                if (turnstiles.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.TURNSTILE, r, c)))
                    return turnstiles.get(i);
        System.out.println("Error in findTurnstilesContaining(r, c) - no turnstile found");
        return new Turnstiles();
    }

    public Blocks findBlocksContaining(int r, int c)
    {
        for (int i = 0; i < blocks.size(); i++)
            for (int j = 0; j < blocks.get(i).size(); j++)
                if (blocks.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.BLOCK, r, c)))
                    return blocks.get(i);
        System.out.println("Error in findBlocksContaining(r, c) - no block found");
        return new Blocks();
    }

    public Holes findHolesContaining(int r, int c)
    {
        for (int i = 0; i < holes.size(); i++)
            for (int j = 0; j < holes.get(i).size(); j++)
                if (holes.get(i).get(j).equals(GameSquare.create(GameSquare.SquareType.HOLE, r, c)))
                    return holes.get(i);
        System.out.println("Error in findHolesContaining(r, c) - no hole found");
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

        // holes, turnstiles, blocks - LONG
        String holesStr = "[Hole Collections]\n>> ";
        holesStr += "Size: " + holes.size() + "\n\n";
        for (int i = 0; i < holes.size(); i++)
            holesStr += ">>> " + holes.get(i).toString() + "\n";

        String turnstilesStr = "[Turnstile Collections]\n>> ";
        turnstilesStr += "Size: " + turnstiles.size() + "\n\n";
        for (int i = 0; i < turnstiles.size(); i++)
            turnstilesStr += ">>> " + turnstiles.get(i).toString() + "\n";

        String blocksStr = "[Blocks Collections]\n>> ";
        blocksStr += "Size: " + blocks.size() + "\n\n";
        for (int i = 0; i < blocks.size(); i++)
            blocksStr += ">>> " + blocks.get(i).toString() + "\n";

        // holes, turnstiles, blocks - SHORT
        String holesStr2 = "";
        String blocksStr2 = "";
        String turnstilesStr2 = "";
        for (int i = 0; i < holes.size(); i++) {
            holesStr2 += "[ ";
            for (int j = 0; j < holes.get(i).size(); j++) {
                holesStr2 += "(" + holes.get(i).get(j).getRow() + ", " + holes.get(i).get(j).getColumn() + "), ";
            }
            holesStr2 += "]";
        }
        for (int i = 0; i < blocks.size(); i++) {
            blocksStr2 += "[ ";
            for (int j = 0; j < blocks.get(i).size(); j++) {
                blocksStr2 += "(" + blocks.get(i).get(j).getRow() + ", " + blocks.get(i).get(j).getColumn() + "), ";
            }
            blocksStr2 += "]";
        }
        for (int i = 0; i < turnstiles.size(); i++) {
            turnstilesStr2 += "[ ";
            for (int j = 0; j < turnstiles.get(i).size(); j++) {
                if (turnstiles.get(i).get(j).getType().equals(GameSquare.SquareType.PIVOT))
                    turnstilesStr2 += "P(" + turnstiles.get(i).get(j).getRow() + ", " + turnstiles.get(i).get(j).getColumn() + "), ";
                else
                    turnstilesStr2 += "(" + turnstiles.get(i).get(j).getRow() + ", " + turnstiles.get(i).get(j).getColumn() + "), ";
            }
            turnstilesStr2 += "]";
        }

        return "============================\n[GameLevelModel]" + 
        // "\n\n> " + holesStr + 
        // "> " + turnstilesStr + 
        // "> " + blocksStr + 
        "> " + "Grid\n" + gridStr +
        "\nPlayer Location: (" + playerRow + ", " + playerColumn +")" +
        "\nHoles:\n" + holesStr2 + 
        "\nBlocks:\n" + blocksStr2 + 
        "\nTurnstiles:\n" + turnstilesStr2;   
    }

    public void display(Graphics2D g)
    {
        // TODO
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
    }

    public class Turnstiles extends GameSquareCollection
    {
        public Turnstiles()
        {
            super();
        }

        public Boolean ableToRotateClockwise()
        {
            System.out.println("[[debug]] Determining if able to rotate clockwise.");
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

                    Boolean destinationIsGood = (destinationType.equals(GameSquare.SquareType.EMPTY) || 
                                                 destinationType.equals(GameSquare.SquareType.HOLE)  ||
                                                 destinationType.equals(GameSquare.SquareType.TURNSTILE));

                    System.out.println("destinationIsGood:" + destinationIsGood);

                    Boolean passThroughIsGood = (passThroughType.equals(GameSquare.SquareType.EMPTY) || 
                                                 passThroughType.equals(GameSquare.SquareType.HOLE)  ||
                                                 passThroughType.equals(GameSquare.SquareType.PLAYER));

                    System.out.println("passThroughIsGood: " + passThroughIsGood);

                    Boolean ableToRotate = destinationIsGood && passThroughIsGood;

                    if (! ableToRotate)
                        return false;
                }
            }
            return true;
        }

        public Boolean ableToRotateCounterClockwise()
        {
            System.out.println("[[debug]] Determining if able to rotate counter clockwise.");

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

                    Boolean destinationIsGood = (destinationType.equals(GameSquare.SquareType.EMPTY) || 
                                                 destinationType.equals(GameSquare.SquareType.HOLE)  ||
                                                 destinationType.equals(GameSquare.SquareType.TURNSTILE));

                    System.out.println("destinationIsGood:" + destinationIsGood);

                    Boolean passThroughIsGood = (passThroughType.equals(GameSquare.SquareType.EMPTY) || 
                                                 passThroughType.equals(GameSquare.SquareType.HOLE)  ||
                                                 passThroughType.equals(GameSquare.SquareType.PLAYER));

                    System.out.println("passThroughIsGood: " + passThroughIsGood);

                    Boolean ableToRotate = destinationIsGood && passThroughIsGood;
                    if (! ableToRotate)
                        return false;
                }
            }
            return true;
        }

        public void rotateClockwise()
        {
            System.out.println("[[debug]] rotating clockwise");

            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn())
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else if (gs.getColumn() > p.getColumn())
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else if (gs.getRow() < p.getRow())
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow())
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.TURNSTILE, destinationRow, destinationCol);
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, gs.getRow(), gs.getColumn());
                    collection.set(i, grid[destinationRow][destinationCol]);
                }
            }
        }

        public void rotateCounterClockwise()
        {
            System.out.println("[[debug]] rotating counter clockwise");

            Pivot p = getPivotSquare();
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                GameSquare gs = collection.get(i);
                if (gs.getType().equals(GameSquare.SquareType.TURNSTILE))
                {
                    if (gs.getColumn() < p.getColumn())
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    else if (gs.getColumn() > p.getColumn())
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else if (gs.getRow() < p.getRow())
                    {
                        destinationRow = getDestinationRow("DOWN", gs.getRow());
                        destinationCol = getDestinationColumn("LEFT", gs.getColumn());
                    }
                    else // (gs.getRow() > p.getRow())
                    {
                        destinationRow = getDestinationRow("UP", gs.getRow());
                        destinationCol = getDestinationColumn("RIGHT", gs.getColumn());
                    }
                    grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.TURNSTILE, destinationRow, destinationCol);
                    grid[gs.getRow()][gs.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, gs.getRow(), gs.getColumn());
                    collection.set(i, grid[destinationRow][destinationCol]);
                }
            }
        }

        public Pivot getPivotSquare()
        {
            for (int i = 0; i < collection.size(); i++)
                if (collection.get(i).getType().equals(GameSquare.SquareType.PIVOT))
                    return (Pivot) collection.get(i);
            System.out.println("Error in GameLevelModel::getPivotSquare() - no pivot found.");
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
            for (int i = 0; i < collection.size(); i++)
                if (collection.get(i).equals(hole))
                    collection.remove(i);
            System.out.println("[[debug]] filling hole at (" + hole.getRow() + ", " + hole.getColumn() + ")");
            grid[hole.getRow()][hole.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, hole.getRow(), hole.getColumn());
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
            System.out.println("[[debug]] Shifting blocks " + direction);
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                Block b = (Block) collection.get(i);
                destinationRow = getDestinationRow(direction, b.getRow());
                destinationCol = getDestinationColumn(direction, b.getColumn());
                if (grid[destinationRow][destinationCol].getType().equals(GameSquare.SquareType.HOLE))
                {
                    Holes h = findHolesContaining(destinationRow, destinationCol);
                    h.fillHole((Hole) grid[destinationRow][destinationCol]);
                    //collection.remove(i);
                } 
                else
                {
                    grid[destinationRow][destinationCol] = GameSquare.create(GameSquare.SquareType.BLOCK, destinationRow, destinationCol);
                }
                grid[b.getRow()][b.getColumn()] = GameSquare.create(GameSquare.SquareType.EMPTY, b.getRow(), b.getColumn());
                collection.set(i, grid[destinationRow][destinationCol]);
            }
        }

        public Boolean ableToShift(String direction)
        {
            int destinationRow, destinationCol;
            for (int i = 0; i < collection.size(); i++)
            {
                Block b = (Block) collection.get(i);
                destinationRow = getDestinationRow(direction, b.getRow());
                destinationCol = getDestinationColumn(direction, b.getColumn());
                GameSquare destination = grid[destinationRow][destinationCol];
                Boolean validShift = destination.getType().equals(GameSquare.SquareType.EMPTY) || 
                                     destination.getType().equals(GameSquare.SquareType.HOLE)  ||
                                     destination.getType().equals(GameSquare.SquareType.BLOCK);
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