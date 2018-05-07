import java.awt.*;

public class Stairs extends GameSquare
{
    public Stairs()
    {
    }

    public Stairs(int r, int c)
    {
        type = SquareType.STAIRS;
        row = r;
        column = c;
        strValue = "$$";
        shape = new Right(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.ORANGE);
    }
}