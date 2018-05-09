import java.awt.*;

public class Pivot extends Turnstile
{
    public Pivot()
    {
    }

    public Pivot(int r, int c)
    {
        type = SquareType.PIVOT;
        row = r;
        column = c;
        strValue = "PV";
        shape = new Circle(SHAPE_SIDE / 2, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.CYAN);
    }

    public void paintComponent()
    {
        // TODO
    }
}