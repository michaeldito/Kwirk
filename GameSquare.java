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

    public static String TypeStrings[] =  { "PLAYER", "WALL", "BLOCK", "HOLE", "TURNSTILE", "PIVOT", "STAIRS", "EMPTY" };

    protected final int CENTER_X = 15;
    protected final int CENTER_Y = 25;
    protected final int SHAPE_SIDE = 35;

    protected int row;
    protected int column;
    protected SquareType type;
    protected String strValue;
    protected Shape shape;

    public GameSquare() {}

    public GameSquare(int r, int c)
    {
        row = r;
        column = c;
        type = SquareType.EMPTY;
        strValue = "  ";
        shape = new Square(SHAPE_SIDE - 4, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.WHITE);
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
        String s = ">>>>> [" + TypeStrings[type.getValue()] + "] :\n>>>>>> row = " + row + "; column = " + column;
        s += ";\n>>>>>> (shape) => " + (shape == null ? "no shape" : shape) + "\n>>>>>> strValue = " + strValue;
        return s; 
    }

    public boolean equals(GameSquare gs)
    {
        if (row != gs.getRow()) 
            return false;
        if (column != gs.getColumn()) 
            return false;
        if (type != gs.getType()) 
            return false;
        return true;
    }

    public void paintTopBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1);
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        endX = CENTER_X + SHAPE_SIDE * (column + 1) + SHAPE_SIDE/2;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);
    }

    public void paintBottomBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1);
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        endX = CENTER_X + SHAPE_SIDE * (column + 1) + SHAPE_SIDE/2;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);
    }


    public void paintLeftBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1);
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        endX = SHAPE_SIDE * (column + 1);
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY); 
    }

    public void paintRightBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1)+ SHAPE_SIDE-3;
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        endX = SHAPE_SIDE * (column + 1)+ SHAPE_SIDE-3;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);  
    }
    
    public static GameSquare create(SquareType squareType, int r, int c)
    {
        if (squareType.equals(SquareType.PLAYER))
            return new Player(r, c);
        else if (squareType.equals(SquareType.WALL))
            return new Wall(r, c);
        else if (squareType.equals(SquareType.BLOCK))
            return new Block(r, c);
        else if (squareType.equals(SquareType.HOLE))
            return new Hole(r, c);
        else if (squareType.equals(SquareType.TURNSTILE))
            return new Turnstile(r, c);
        else if (squareType.equals(SquareType.PIVOT))
            return new Pivot(r, c);
        else if (squareType.equals(SquareType.STAIRS))
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
