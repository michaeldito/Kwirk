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
		doubleSide = 0;
		centerX = 0;
		centerY = 0;
		color = Color.WHITE;
		isSelected = false;
		hasVertices = false;
	}

	public Circle (Circle C)
	{
		side = C.side;
		centerX = C.centerX;
		centerY = C.centerY;
		color = C.color;
		isSelected = C.isSelected;
		hasVertices = C.hasVertices;
	}

	public double area ()
	{
		return Math.PI * side * side;
	}

	public double perimeter ()
	{
		return 2 * Math.PI * side;
	}

	public String getName ()
	{
		return "Circle";
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillOval (centerX-side, centerY-side, 2*side, 2*side);
		g2.drawOval (centerX-side, centerY-side, 2*side, 2*side);
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point

		if (this.isSelected) {
			g2.setPaint (Color.WHITE);
			g2.drawOval (centerX-side, centerY-side, 2*side, 2*side);
			g2.setPaint (color);
		}
	}

	public boolean isIn (int x, int y)
	{
		int deltaX = x - centerX;
		int deltaY = y - centerY;
		double dist = sqrt (deltaX * deltaX + deltaY * deltaY);
		return dist <= side;
	}

	public void modifyShape (JFrame frame, int x, int y)
	{
		System.out.println("Modifying a circle with dialog box.");
		CircleDialog circleDialog = new CircleDialog (frame, true, x, y, side); 
		if (circleDialog.getAnswer() == true)
		{
			side = circleDialog.getSide ();
			doubleSide = side;
			color = circleDialog.getColor ();
		}
		System.out.println("side: " + side + ", color: " + color);
	}
}
