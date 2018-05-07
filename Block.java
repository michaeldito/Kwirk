import java.awt.*;

public class Block extends GameSquare
{
    public Block()
    {
    }

    public Block(int r, int c)
    {
        type = SquareType.BLOCK;
        row = r;
        column = c;
        strValue = "BL";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.YELLOW);
    }

    public void vanish()
    {
        shape.setColor(Color.WHITE);
    }
}