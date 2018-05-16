import java.awt.*;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Player extends GameSquare
{
    private Square background;

    private int[] sides = { SHAPE_SIDE / 3, SHAPE_SIDE / 4, SHAPE_SIDE / 5, SHAPE_SIDE / 4, SHAPE_SIDE / 3, SHAPE_SIDE / 2 };
    private int sideIndex = 0;

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
        background = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.WHITE);
    }

    public void setShape(String s)
    {
        if (s.equals("Spud"))
            shape = new Circle(SHAPE_SIDE / 2, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.YELLOW);
        if (s.equals("Pickle Rick"))
            shape = new Ellipse(SHAPE_SIDE / 2, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.GREEN);
        else
            shape = new Circle(SHAPE_SIDE / 2, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.RED);
    }
    
    public void paintComponent(Graphics2D g2)
    {
        background.paintComponent(g2);
        shape.paintComponent(g2);
    }

    public static void main(String[] args)
    {
        GameSquare gs = GameSquare.create(SquareType.PLAYER, 0, 0);
        System.out.println(gs);
    }
}