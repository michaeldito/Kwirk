import java.awt.*;

public class Player extends GameSquare
{
    public Player()
    {
    }

    public Player(int r, int c)
    {
        type = SquareType.PLAYER;
        row = r;
        column = c;
        strValue = "KW";
        shape = new Circle(SHAPE_SIDE / 2, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.RED);
    }

    public static void main(String[] args)
    {
        GameSquare gs = GameSquare.create(SquareType.PLAYER, 0, 0);
        System.out.println(gs);

    }
}