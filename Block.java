public class Block extends GameSquare
{
    public Block()
    {
    }

    public Block(SquareType t, int r, int c)
    {
        type = t;
        row = r;
        column = c;
        strValue = "B";
        shape = new Square(/* side, cX, cY, color */);
    }

    public void display(Graphics g)
    {
        // TODO
    }

    public void vanish(Graphics g)
    {
        // TODO
    }
}