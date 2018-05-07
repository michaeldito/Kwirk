import java.awt.*;

public class Wall extends GameSquare
{    
    public Wall()
    {
    }

    public Wall(int r, int c)
    {
        type = SquareType.WALL;
        row = r;
        column = c;
        strValue = "XX";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.GREEN);
    }
}