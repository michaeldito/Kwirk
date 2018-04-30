public class Player extends GameSquare
{
    public Player()
    {
    }

    public Player(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "K";
        shape = new Circle(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.RED);
    }
}