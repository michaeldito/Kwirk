import java.awt.*;

public class Pivot extends Turnstile
{
    private Shape background;

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
        background = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.BLUE);
    }

    public void paintComponent(Graphics2D g2)
    {
        background.paintComponent(g2);
        shape.paintComponent(g2);
    }
}