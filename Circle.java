// File: Circle.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Circle. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Circle extends Shape
{
	public Circle ()
	{
		side = 0;
		centerX = 0;
		centerY = 0;
		color = Color.WHITE;
	}

	public Circle (Circle C)
	{
		side = C.side;
		centerX = C.centerX;
		centerY = C.centerY;
		color = C.color;
	}

	public Circle(int S, int cX, int cY, Color C)
	{
		side = S;
		centerX = cX;
		centerY = cY;
		color = C;
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillOval (centerX-side, centerY-side, 2*side, 2*side);
		g2.drawOval (centerX-side, centerY-side, 2*side, 2*side);
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
	}
}
