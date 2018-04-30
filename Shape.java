// File: Shape.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a virtual class called Shape. 

import java.text.NumberFormat;
import java.awt.*;
import javax.swing.*;

public class Shape implements Comparable<Shape>
{
	public enum ShapeType {CIRCLE, SQUARE, RECTANGLE, EQUILATERAL, RIGHT, SCALENE};
	protected int centerX;
	protected int centerY;
	protected int side;
	protected Color color;

	public Shape ()
	{
		side = 0;
		color = Color.WHITE;
	}

	public void setCenterX (int X)
	{
		centerX = X;
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
	}

	public void setColor (Color C)
	{
		color = C;
	}

	public void modifyShape (JFrame frame, int x, int y)
	{
	}

	public double area ()
	{
		return 0;
	}

	public double perimeter ()
	{
		return 0;
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			if (Integer.parseInt(parts[2]) > 0)
			{
				side = Integer.parseInt(parts[2]);
				color = new Color(Integer.parseInt(parts[3]));
			}
			else
			{
				color = new Color(Integer.parseInt(parts[3]));
				side = Integer.parseInt(parts[2]);
			}
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
		string += color.getRGB() + " ";
		string += side + " ";
		return string;
	}

	public int compareTo (Shape S)
	{
		double a1 = area (), a2 = S.area ();
		double p1 = perimeter (), p2 = S.perimeter ();
		if (a1 < a2) return -1;
		if (a1 > a2) return 1;
		if (p1 < p2) return -2;
		if (p1 > p2) return 2;
		return 0;
	}

	public String getName ()
	{
		return "Shape";
	}

	public void paintComponent (Graphics2D g2)
	{
	}

	public boolean isIn (int X, int Y)
	{
		return false;
	}

	public void move (int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
		//System.out.println ("Moving shape " + deltaX + "," + deltaY + " units");
	}
}
