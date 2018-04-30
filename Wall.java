public class Wall extends GameSquare
{    
    public Wall()
    {
    }

    public Wall(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "W";
        shape = new Square(SHAPE_SIDE, row * ROW_HEIGHT, column * COL_WIDTH, Color.GREEN);
    }
}