public class Pivot extends GameSquare
{
    public Pivot()
    {
    }

    public Pivot(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "P";
        shape = new Circle(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.CYAN);
    }

    public void paintComponent(Graphics2D g2)
    {
        // TODO
    }
}