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
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.YELLOW);
    }

    public void vanish(Graphics g)
    {
        shape.setColor(Color.WHITE);
    }
}