import java.awt.*;

public class Hole extends GameSquare
{
    public Hole()
    {
    }

    public Hole(int r, int c)
    {
        type = SquareType.HOLE;
        row = r;
        column = c;
        strValue = "[]";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.BLACK);
    }

    public void fillHole()
    {
        shape.setColor(Color.WHITE);
    }
}