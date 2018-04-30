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
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.BLUE);
        pivotX = pX;
        pivotY = pY;
    }
}