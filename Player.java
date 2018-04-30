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
        shape = new Circle(/* side, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }
}