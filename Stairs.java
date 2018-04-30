public class Stairs extends GameSquare
{
    public Stairs()
    {
    }

    public Stairs(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "W";
        shape = new Right(/* side1, side2, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }
}