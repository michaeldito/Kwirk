import java.awt.*;
import static java.lang.Math.*;
public class Stairs extends GameSquare
{
    private Shape step1;
    private Shape step2;
    private Shape step3;
    private Shape step4;

    public Stairs()
    {
    }

    public Stairs(int r, int c)
    {
        type = SquareType.STAIRS;
        row = r;
        column = c;
        strValue = "$$";
        shape = new Right(SHAPE_SIDE - 3, CENTER_X + SHAPE_SIDE * (column + 1) + 7, CENTER_Y + 7 + SHAPE_SIDE * (row + 1), Color.GREEN);
        shape.rotate(Math.toRadians(-90));
        step1 = new Square(SHAPE_SIDE/3, CENTER_X + SHAPE_SIDE * (column + 1) + 11, CENTER_Y - 15 + SHAPE_SIDE * (row + 1), Color.BLACK);
        step2 = new Square(SHAPE_SIDE/3, CENTER_X + SHAPE_SIDE * (column + 1) + 2, CENTER_Y - 6 + SHAPE_SIDE * (row + 1), Color.BLACK);
        step3 = new Square(SHAPE_SIDE/3, CENTER_X + SHAPE_SIDE * (column + 1) - 7, CENTER_Y + 3 + SHAPE_SIDE * (row + 1), Color.BLACK);
        step4 = new Square(SHAPE_SIDE/3, CENTER_X + SHAPE_SIDE * (column + 1) - 15, CENTER_Y + 12 + SHAPE_SIDE * (row + 1), Color.BLACK);

    }

    public void paintComponent(Graphics2D g2)
    {
        step1.paintComponent(g2);
        step2.paintComponent(g2);
        step3.paintComponent(g2);
        step4.paintComponent(g2);
        shape.paintComponent(g2);
    }
}