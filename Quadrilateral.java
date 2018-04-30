import static java.lang.Math.*;
import java.awt.*;

public class Quadrilateral extends Shape
{
    protected int [] vertexX = new int [4];
    protected int [] vertexY = new int [4];
    protected double [] doubleVertexX = new double [4];
    protected double [] doubleVertexY = new double [4];
    protected Polygon polygon = new Polygon (vertexX, vertexY, 4);
    protected double angle = 0;
    
    /*
     * Default Constructor.
     */
    Quadrilateral ()
    {
    }

    public void paintComponent (Graphics2D g2)
    {
        g2.setPaint (color);
		g2.fillPolygon (vertexX, vertexY, 4);
		g2.drawPolygon (vertexX, vertexY, 4);
		g2.setPaint (Color.BLACK);
        g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point

        if (this.isSelected == true) {
            g2.setPaint (Color.WHITE);
            g2.drawPolygon (vertexX, vertexY, 4);
            g2.setPaint (color);
        }
    }

    /*
     * Returns the name of the object.
     *
     * @param 	    none
     * @return		String
     */
    public String getName()
    {
        return "Quadrilateral";
    }
    
    public boolean isIn (int x, int y)
    {
		return polygon.contains (x, y);
    }

	public void move (int dx, int dy)
	{
		centerX += dx;
        centerY += dy;
		for (int i = 0; i < 4; i++) {
			 doubleVertexX[i] += dx;
             doubleVertexY[i] += dy;
		}
		 for (int i = 0; i < 4; i++) {
		 	vertexX[i] = (int) (doubleVertexX[i] + .5);
		 	vertexY[i] = (int) (doubleVertexY[i] + .5);
         }
        polygon = new Polygon(vertexX, vertexY, 4);
    }

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
        string += color.getRGB() + " ";
        string += angle + " ";
		string += doubleVertexX[0] + " " + doubleVertexY[0] + " ";
		string += doubleVertexX[1] + " " + doubleVertexY[1] + " ";
        string += doubleVertexX[2] + " " + doubleVertexY[2] + " ";
        string += doubleVertexX[3] + " " + doubleVertexY[3] + " ";
		return string;
    }
    
    public void rotate (double amount)
	{
		for (int i = 0; i < 4; i++) {
			doubleVertexX[i] = vertexX[i];
			doubleVertexY[i] = vertexY[i];
		}
		for (int i = 0; i < 4; i++) {
			double x = doubleVertexX[i] - centerX;
			double y = doubleVertexY[i] - centerY;
			doubleVertexX[i] = x * cos(amount) - y * sin(amount);
			doubleVertexY[i] = x * sin(amount) + y * cos(amount);
			doubleVertexX[i] += centerX;
			doubleVertexY[i] += centerY;
		}
		for (int i = 0; i < 4; i++) {
			vertexX[i] = (int)(doubleVertexX[i] + 0.5);
			vertexY[i] = (int)(doubleVertexY[i] + 0.5);
		}
        polygon = new Polygon(vertexX, vertexY, 4);
    }
    
    public void resize(double n)
    {
        angle += n;

        double tX = 0;
        double tY = 0;
        for (int i = 0; i < 4;  i++) {
            tX = doubleVertexX[i] - centerX;
            tY = doubleVertexY[i] - centerY;

            if (n > 0) {
                doubleVertexX[i] = tX * 1.1;
                doubleVertexY[i] = tY * 1.1;
            }
            else {
                doubleVertexX[i] = tX * .9;
                doubleVertexY[i] = tY * .9;
            }
            doubleVertexX[i] += centerX;
            doubleVertexY[i] += centerY;
        }
        for (int i = 0; i < 4; i++) {
            vertexX[i] = (int) (doubleVertexX[i] + .5);
            vertexY[i] = (int) (doubleVertexY[i] + .5);
        }
        polygon = new Polygon(vertexX, vertexY, 4);
    }

    public void setDoubleVertices() {
        for (int i = 0; i < 4; i++) {
            doubleVertexX[i] = vertexX[i];
            doubleVertexY[i] = vertexY[i];
        }
    }
}