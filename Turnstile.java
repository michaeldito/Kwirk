public class Turnstile extends GameSquare
{
    private int pivotX;
    private int pivotY;

    public Turnstile()
    {
    }

    public Turnstile(SquareType t, int r, int c, int pX, int pY)
    {
        type = t;
        row = r;
        column = c;
        strValue = "K";
        shape = new Square(/* side, cX, cY, color */);
        pivotX = pX;
        pivotY = pY;
    }

    public void display(Graphics g)
    {
        // TODO
    }
}