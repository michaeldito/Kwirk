/* GameSquare.java
 *
 */
import java.awt.*;

public class GameSquare 
{
    public enum SquareType { 
        PLAYER(0), WALL(1), BLOCK(2), HOLE(3), TURNSTILE(4), PIVOT(5), STAIRS(6), EMPTY(7);
        private final int value;
        private SquareType(int value)
        {
            this.value = value;
        }
        public int getValue()
        {
            return value;
        }
    };

    public String TypeStrings[] =  { "PLAYER", "WALL", "BLOCK", "HOLE", "TURNSTILE", "PIVOT", "STAIRS", "EMPTY" };

    protected final int CENTER_X = 25;
    protected final int CENTER_Y = 25;
    protected final int SHAPE_SIDE = 35;

    protected int row;
    protected int column;
    protected SquareType type;
    protected String strValue;
    protected Shape shape;

    public GameSquare()
    {
    }

    public GameSquare(int r, int c)
    {
        row = r;
        column = c;
        type = SquareType.EMPTY;
        strValue = "  ";
        shape = null;
    }

    protected void setRow(int r) 
    { 
        row = r;
    }

    protected int getRow()
    {
        return row;
    }

    protected void setColumn(int c)
    {
        column = c;
    }

    protected int getColumn()
    {
        return column;
    }

    protected void setType(SquareType t)
    {
        type = t;
    }

    protected SquareType getType()
    {
        return type;
    }

    protected String getStrValue()
    {
        return strValue;
    }

    protected void setShapeCenterXY(int cX, int cY)
    {
        shape.setCenterX(cX);
        shape.setCenterY(cY);
    }

    protected void paintComponent(Graphics2D g2)
    {
        shape.paintComponent(g2);
    }

    public String toString()
    {
        return ">>>>> [" + TypeStrings[type.getValue()] + "] :\n>>>>>> row = " + row + "; column = " + column + ";\n>>>>>> (shape) => " + (shape == null ? "no shape" : shape) + "\n>>>>>> strValue = " + strValue;
    }

    public boolean equals(GameSquare gs)
    {
        if (row != gs.getRow()) return false;
        if (column != gs.getColumn()) return false;
        if (type != gs.getType()) return false;
        return true;
    }

    public static GameSquare create(SquareType t, int r, int c)
    {
        if (t == SquareType.PLAYER)
            return new Player(r, c);
        else if (t == SquareType.WALL)
            return new Wall(r, c);
        else if (t == SquareType.BLOCK)
            return new Block(r, c);
        else if (t == SquareType.HOLE)
            return new Hole(r, c);
        else if (t == SquareType.TURNSTILE)
            return new Turnstile(r, c);
        else if (t == SquareType.PIVOT)
            return new Pivot(r, c);
        else if (t == SquareType.STAIRS)
            return new Stairs(r, c);
        else
            return new GameSquare(r, c);
    }

    public static void main(String[] args)
    {
        GameSquare player = GameSquare.create(SquareType.PLAYER, 0, 0);
        GameSquare wall = GameSquare.create(SquareType.WALL, 0, 0);
        GameSquare block = GameSquare.create(SquareType.BLOCK, 0, 0);
        GameSquare hole = GameSquare.create(SquareType.HOLE, 0, 0);
        GameSquare turnstile = GameSquare.create(SquareType.TURNSTILE, 0, 0);
        GameSquare pivot = GameSquare.create(SquareType.PIVOT, 0, 0);
        GameSquare stairs = GameSquare.create(SquareType.STAIRS, 0, 0);
        GameSquare empty = GameSquare.create(SquareType.EMPTY, 0, 0);
        
        System.out.println(player);
        System.out.println();
        System.out.println(wall);
        System.out.println();
        System.out.println(block);
        System.out.println();
        System.out.println(hole);
        System.out.println();
        System.out.println(turnstile);
        System.out.println();
        System.out.println(pivot);
        System.out.println();
        System.out.println(stairs);
        System.out.println();
        System.out.println(empty);
    }
}
