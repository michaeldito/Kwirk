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
        shape = new Circle(/* side, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }
}