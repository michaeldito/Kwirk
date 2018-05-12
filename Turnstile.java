import java.awt.*;

public class Turnstile extends GameSquare
{
    public Turnstile()
    {
    }

    public Turnstile(int r, int c)
    {
        type = SquareType.TURNSTILE;
        row = r;
        column = c;
        strValue = "TS";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.BLUE);
    }

    public void paintTopBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1);
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        endX = CENTER_X + SHAPE_SIDE * (column + 1) + SHAPE_SIDE/2;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);
    }

    public void paintBottomBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1);
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        endX = CENTER_X + SHAPE_SIDE * (column + 1) + SHAPE_SIDE/2;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);
    }


    public void paintLeftBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1) - 1;
        startY = CENTER_Y + SHAPE_SIDE * (row + 1) - SHAPE_SIDE/2;
        endX = SHAPE_SIDE * (column + 1) - 1;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY); 
    }

    public void paintRightBorder(Graphics2D g2)
    {
        g2.setPaint (Color.BLACK);
        int startX, startY, endX, endY;
        startX = SHAPE_SIDE * (column + 1)+ SHAPE_SIDE-3;
        startY = CENTER_Y + SHAPE_SIDE * (row + 1)  - SHAPE_SIDE/2;
        endX = SHAPE_SIDE * (column + 1)+ SHAPE_SIDE-3;
        endY = CENTER_Y + SHAPE_SIDE * (row + 1)  + SHAPE_SIDE/2;
        g2.drawLine(startX, startY, endX, endY);  
    }
}