/* GameSquare.java
 *
 */

public class GameSquare 
{
    public enum SquareType { PLAYER, WALL, BLOCK, HOLE, TURNSTILE, PIVOT, STAIRS };
    public String TypeStrings[] =  { "PLAYER", "WALL", "BLOCK", "HOLE", "TURNSTILE", "PIVOT", "STAIRS" };

    public final int CENTER_X = 25;
    public final int CENTER_Y = 25;
    public final int SHAPE_SIDE = 35;

    protected int row;
    protected int column;
    protected SquareType type;
    protected String strValue;
    protected Shape shape;

    public SquareType()
    {
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

    protected void setType(Squaretype t)
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
        return "[" + TypeStrings[type] + "] : row(" + row + "), column(" + column + "), " + shape.getName() + ", " + strValue;
    }

    public static GameSquare create(SquareType t, int r, int c)
    {
        if (t == PLAYER)
            return new Player(t, r, c);
        else if (t == WALL)
            return new Wall(t, r, c);
        else if (t == BLOCK)
            return new Block(t, r, c);
        else if (t == HOLE)
            return new Hole(t, r, c);
        else if (t == TURNSTILE)
            return new Turnstile(t, r, c);
        else if (t == PIVOT)
            return new Pivot(t, r, c);
        else if (t == STAIRS)
            return new Stairs(t, r, c);
        else
            return null;
    }
}