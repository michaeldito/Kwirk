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
        shape = new Square(/* side, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }

    public void fillHole(Graphics g)
    {
        // TODO
    }
}