import java.awt.*;

public class Player extends GameSquare
{
    private Shape background;

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
        background = new Square(SHAPE_SIDE - 7, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.WHITE);
    }
    
    public void paintComponent(Graphics2D g2)
    {
        background.paintComponent(g2);
        shape.paintComponent(g2);
        // paintTopBorder(g2);
        // paintBottomBorder(g2);
        // paintLeftBorder(g2);
        // paintRightBorder(g2);
    }

    public static void main(String[] args)
    {
        GameSquare gs = GameSquare.create(SquareType.PLAYER, 0, 0);
        System.out.println(gs);

    }
}