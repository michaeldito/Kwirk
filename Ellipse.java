import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public final class Ellipse extends Shape
{
	private int width = 100;
	private int height = 50;

	public Ellipse ()
	{
		side = 0;
		centerX = 0;
		centerY = 0;
		color = Color.WHITE;
	}

	public Ellipse(int S, int cX, int cY, Color C)
	{
		side = S;
		centerX = cX;
		centerY = cY;
		color = C;
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		Ellipse2D e = new Ellipse2D.Double(centerX, centerY, width, height);
		g2.draw(e);
		g2.fill(e);
		// Stroke previousStroke = g2.getStroke();
		// g2.setStroke(new BasicStroke(4.0f));
		// g2.setPaint (Color.BLACK);
		// g2.drawOval (centerX-side, centerY-side, 2*side, 2*side);
		// g2.setStroke(previousStroke);
		// g2.setPaint (color);
	}
}
