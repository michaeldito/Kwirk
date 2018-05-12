import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Rectangle extends Quadrilateral
{
    private int side2;
    private double doubleSide2;

    /*
     * Default Constructor.
     */
    public Rectangle ()
    {
        side2 = 0;
    }

    /*
     * Copy Constructor.
     *
     * @param R	    Rectangle
     * @return		Rectangle
     */
    public Rectangle (Rectangle R)
    {
        side = R.side;
        side2 = R.side2;
        centerX = R.centerX;
        centerY = R.centerY;
        color = R.color;
        for (int i = 0; i < 4; i++)
		{
			vertexX[i] = R.vertexX[i];
			vertexY[i] = R.vertexY[i];
		}
    }
    
    public Rectangle(int S, int cX, int cY, Color C)
    {
        side = S;
        side2 = S;
        centerX = cX;
        centerY = cY;
        color = C;
        setVertices();
    }

    /*
     * Returns the area of the Rectangle.
     *
     * @param 	    none
     * @return		double
     */
    public double area ()
    {
        return side * side2;
    }

    /*
     * Returns the perimeter of the Rectangle.
     *
     * @param 	    none
     * @return		double
     */
    public double perimeter ()
    {
        return (side + side2) * 2;
    }

    /*
     * Returns the name of the object.
     *
     * @param 	    none
     * @return		String
     */
    public String getName ()
    {
        return "Rectangle";
    }

    public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
            if (parts.length == 5) {
                side = Integer.parseInt(parts[2]);
                side2 = Integer.parseInt(parts[3]);
                color = new Color(Integer.parseInt(parts[4]));
            }
            else if (parts.length == 6) {
                color = new Color(Integer.parseInt(parts[2]));
                side = Integer.parseInt(parts[3]);
                side2 = Integer.parseInt(parts[4]);
                angle = Double.parseDouble (parts[5]);
            }
            else {
                color = new Color(Integer.parseInt(parts[2]));
                angle = Math.toRadians(Double.parseDouble(parts[3]));
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

    public void setVertices()
    {
        if (!hasVertices)
        {
            doubleVertexX[0] = (centerX + (side / 2));
            doubleVertexX[1] = (centerX - (side / 2));
            doubleVertexX[2] = (centerX - (side / 2));
            doubleVertexX[3] = (centerX + (side / 2));
            doubleVertexY[0] = (centerY - (side2 / 2));
            doubleVertexY[1] = (centerY - (side2 / 2));
            doubleVertexY[2] = (centerY + (side2 / 2));
            doubleVertexY[3] = (centerY + (side2 / 2));
        }
        for (int i = 0; i < 4; i++) {
            vertexX[i] = (int) doubleVertexX[i];
            vertexY[i] = (int) doubleVertexY[i];
        }
        if (hasVertices)
        {
            double s1 = Math.sqrt(Math.pow(vertexX[1] - vertexX[0], 2) + Math.pow(vertexY[1] - vertexY[0], 2));
            double s2 = Math.sqrt(Math.pow(vertexX[2] - vertexX[1], 2) + Math.pow(vertexY[2] - vertexY[1], 2));    
            side = (int) s1;
            side2 = (int) s2;
        }

        if (angle != 0)
            rotate(angle);

        hasVertices = false;
        polygon = new Polygon (vertexX, vertexY, 4);
    }

}