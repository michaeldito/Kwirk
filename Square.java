import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Square extends Quadrilateral
{
    /*
     * Default Constructor.
     */
    public Square ()
    {
    }

    /*
     * Copy Constructor.
     *
     * @param S	    Square
     * @return		Square
     */
    public Square (Square S)
    {
        side = S.side;
        centerX = S.centerX;
        centerY = S.centerY;
        color = S.color;
        for (int i = 0; i < 4; i++)
		{
			vertexX[i] = S.vertexX[i];
            vertexY[i] = S.vertexY[i];			
            doubleVertexX[i] = S.doubleVertexX[i];
			doubleVertexY[i] = S.doubleVertexY[i];
        }
        polygon = S.polygon;
    }

    /*
     * Returns the perimeter of the Square.
     *
     * @param 	    none
     * @return		double
     */
    public double perimeter ()
    {
        return 4 * side;
    }

    /*
     * Returns the area of the Square.
     *
     * @param 	    none
     * @return		double
     */
    public double area ()
    {
        return side * side;
    }

    /*
     * Returns the name of the object.
     *
     * @param 	    none
     * @return		String
     */
    public String getName ()
    {
        return "Square";
    }

    public void setVertices()
    {        
        if (! hasVertices) {
            doubleVertexX[0] = (centerX + (side / 2));
            doubleVertexX[1] = (centerX - (side / 2));
            doubleVertexX[2] = (centerX - (side / 2));
            doubleVertexX[3] = (centerX + (side / 2));
            doubleVertexY[0] = (centerY - (side / 2));
            doubleVertexY[1] = (centerY - (side / 2));
            doubleVertexY[2] = (centerY + (side / 2));
            doubleVertexY[3] = (centerY + (side / 2));
        }

        for (int i = 0; i < 4; i++) {
            vertexX[i] = (int) doubleVertexX[i];
            vertexY[i] = (int) doubleVertexY[i];
        }
    
        if (angle != 0)
            rotate(angle);

        if (hasVertices) {
            double s = Math.sqrt(Math.pow(vertexX[1] - vertexX[0], 2) + Math.pow(vertexY[1] - vertexY[0], 2)); 
            side = (int) s;
            doubleSide = s;
        }
        hasVertices = false;
        polygon = new Polygon (vertexX, vertexY, 4);
    }
    
    public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
            int length = parts.length;
            if (length == 4) {
                side = Integer.parseInt(parts[2]);
                color = new Color(Integer.parseInt(parts[3]));
            }
            else if (length == 5) {
                color = new Color(Integer.parseInt(parts[2]));
                side = Integer.parseInt(parts[3]);
                angle = Double.parseDouble(parts[4]);
            }
            else {
                color = new Color(Integer.parseInt(parts[2]));
                angle = Double.parseDouble(parts[3]);
                for (int i = 0; i < 4; i++) {
                    doubleVertexX[i] = Double.parseDouble(parts[4 + (2 * i)]);
                    doubleVertexY[i] = Double.parseDouble(parts[5 + (2 * i)]);
                }
                hasVertices = true;
            }
            setVertices();
		}
		catch (NumberFormatException e)
		{
			//System.out.println ("Numeric input error");
		}
    }
    public void modifyShape (JFrame frame, int x, int y)
	{
		SquareDialog squareDialog = new SquareDialog (frame, true, x, y, side, angle); 
		if (squareDialog.getAnswer() == true)
		{
            side = squareDialog.getSide ();
            doubleSide = (double) side;
			angle = Math.toRadians(squareDialog.getAngle ());
			color = squareDialog.getColor ();
            setVertices ();
            rotate(angle);
		}
	}
}