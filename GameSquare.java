/* GameSquare.java
 *
 */

public class GameSquare 
{
    public enum SquareType { PLAYER, WALL, BLOCK, HOLE, TURNSTILE, PIVOT, STAIRS };
    public String TypeStrings[] =  { "PLAYER", "WALL", "BLOCK", "HOLE", "TURNSTILE", "PIVOT", "STAIRS" };

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

    protected void setColumn(int c)
    {
        column = c;
    }

    protected int getRow()
    {
        return row;
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

    protected void display(Graphics g)
    {
        // TODO
    }

    protected String toString()
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