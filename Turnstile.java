import java.awt.*;

public class Turnstile extends GameSquare
{
    public Turnstile()
    {
    }

    public Turnstile(int r, int c)
    {
        type = SquareType.TURNSTILE;
        row = r;
        column = c;
        strValue = "TS";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.BLUE);
    }
}