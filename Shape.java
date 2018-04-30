// File: Shape.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a virtual class called Shape. 

import java.text.NumberFormat;
import java.awt.*;
import javax.swing.*;

public class Shape implements Comparable<Shape>
{
	public enum ShapeType { CIRCLE, EQUILATERAL, RECTANGLE, RIGHT, SCALENE, SQUARE };
	protected int centerX;
	protected int centerY;
	protected int side;
	protected double doubleSide;
	protected Color color;
	protected boolean isSelected;
	protected boolean hasVertices;

	public Shape ()
	{
		side = 0;
		doubleSide = 0;
		centerX = 0;
		centerY = 0;
		color = Color.WHITE;
		isSelected = false;
		hasVertices = false;
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

	protected double area ()
	{
		return 0;
	}

	protected double perimeter ()
	{
		return 0;
	}

	protected void setVertices ()
	{
	}

	protected void resize (double delta)
	{
		if (doubleSide < 10.0)
			doubleSide = 10;
		doubleSide += (delta * doubleSide) * .1;
		side = (int) doubleSide;
	}

	protected void rotate (double amount) 
	{
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			if (Integer.parseInt(parts[2]) < 0) {
				color = new Color(Integer.parseInt(parts[2]));
				side = Integer.parseInt(parts[3]);
			}
			else {
				side = Integer.parseInt(parts[2]);
				color = new Color(Integer.parseInt(parts[3]));
			}
			setVertices();
			doubleSide = (double) side;
		}
		catch (NumberFormatException e)
		{
			//System.out.println ("Numeric input error");
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += color.getRGB() + " ";
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

	public boolean isIn (int x, int y)
	{
		return false;
	}

	public void move (int dx, int dy)
	{
		centerX += dx;
		centerY += dy;
	}
}
