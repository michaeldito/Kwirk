// File: Square.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Square. 

import static java.lang.Math.*;
import java.awt.*;

public final class Square extends Quadrilateral
{
	public Square ()
	{
	}

	public Square (int S, int X, int Y, Color C)
	{
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
	}

	public Square (Square C)
	{
		side = C.side;
		centerX = C.centerX;
		centerY = C.centerY;
		color = C.color;
	}

	public void setSide (int S)
	{
		side = S;
	}

	public int getSide ()
	{
		return side;
	}

	public double area ()
	{
		return side * side;
	}

	public double perimeter ()
	{
		return 4 * side;
	}

	public String getName ()
	{
		return "Square";
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			side = Integer.parseInt(parts[2]);
			color = new Color(Integer.parseInt(parts[3]));
			angle = Double.parseDouble (parts[4]);
		}
		catch (NumberFormatException e)
		{
			System.out.println ("Numeric input error");
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillRect (centerX-side/2, centerY-side/2, side, side);
		g2.drawRect (centerX-side/2, centerY-side/2, side, side);
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
	}

	public boolean isIn (int X, int Y)
	{
		int deltaX = Math.abs (X - centerX);
		int deltaY = Math.abs (Y - centerY);
		if (deltaX <= side/2 && deltaY <= side/2)
			return true;
		return false;
	}
}
