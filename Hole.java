public class Hole extends GameSquare
{
    public Hole()
    {
    }

    public Hole(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "H";
        shape = new Square(SHAPE_SIDE, row * ROW_HEIGHT, column * COL_WIDTH, Color.BLACK);
    }

    public void fillHole(Graphics g)
    {
        shape.setColor(Color.WHITE);
    }
}