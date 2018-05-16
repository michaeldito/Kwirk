/* Block.java
 *
 * This class represents a gamesquare that is a block.
 */

import java.awt.*;

public class Block extends GameSquare
{
    public Block()
    {
    }

    public Block(int r, int c)
    {
        type = SquareType.BLOCK;
        row = r;
        column = c;
        strValue = "BL";
        shape = new Square(SHAPE_SIDE, CENTER_X + SHAPE_SIDE * (column + 1), CENTER_Y + SHAPE_SIDE * (row + 1), Color.green);
    }

    public void vanish()
    {
        shape.setColor(Color.WHITE);
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