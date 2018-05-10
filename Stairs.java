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
        shape = new Right(SHAPE_SIDE - 7, CENTER_X + SHAPE_SIDE * (column + 1) - 7, CENTER_Y + 7 + SHAPE_SIDE * (row + 1), Color.ORANGE);
        //((Right) shape).rotate(90);
    }
}