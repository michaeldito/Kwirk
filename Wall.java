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
        shape = new Square(/* side, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }
}