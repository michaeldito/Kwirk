import java.awt.*;

public class Wall extends GameSquare
{    
    private Right shape2;
    public Wall()
    {
    }

    public Wall(int r, int c)
    {
        type = SquareType.WALL;
        row = r;
        column = c;
        strValue = "XX";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.GRAY);
        shape2 = new Right(SHAPE_SIDE - 5, CENTER_X + SHAPE_SIDE * (column + 1) - 7, CENTER_Y + 7 + SHAPE_SIDE * (row + 1), Color.DARK_GRAY);
    }
    public void paintComponent(Graphics2D g2)
    {
        shape.paintComponent(g2);
        shape2.paintComponent(g2);
        ((Square) shape).paintBorders(g2);
        Stroke previousSroke = g2.getStroke();
        g2.setStroke(new BasicStroke(4.0f));
        paintTopBorder(g2);
        paintBottomBorder(g2);
        paintLeftBorder(g2);
        paintRightBorder(g2);
        g2.setStroke(previousSroke);
    }
}