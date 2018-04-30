// File: Right.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Right. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Right extends Triangle
{
	private int side2;
	private double doubleSide2;

	public Right ()
	{
		side2 = 0;
	}

	public Right (Right R)
	{
		side = R.side;
		side2 = R.side2;
		centerX = R.centerX;
		centerY = R.centerY;
		color = R.color;
		isSelected = R.isSelected;
		hasVertices = R.hasVertices;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] = R.vertexX[i];
			vertexY[i] = R.vertexY[i];
			doubleVertexX[i] = R.doubleVertexX[i];
			doubleVertexY[i] = R.doubleVertexY[i];
		}  
	}

	public void setVertices ()
	{
		if (! hasVertices) {
			doubleVertexX[0] = 0;
			doubleVertexY[0] = 0;
			doubleVertexX[1] = 0; 
			doubleVertexY[1] = -side2;
			doubleVertexX[2] = side; 
			doubleVertexY[2] = 0;

			double hyp = sqrt (side * side + side2 * side2);
			double perim = perimeter ();
			double inX = ((doubleVertexX[0] * hyp + doubleVertexX[1] * side + doubleVertexX[2] * side2) / perim);
			double inY = ((doubleVertexY[0] * hyp + doubleVertexY[1] * side + doubleVertexY[2] * side2) / perim);

			for (int i = 0; i < 3; i++)
			{
				doubleVertexX[i] += (centerX - inX);
				doubleVertexY[i] += (centerY - inY);
			}
		}
		hasVertices = false;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] += (int)(doubleVertexX[i] + .5);
			vertexY[i] += (int)(doubleVertexY[i] + .5);
		}
        if (angle != 0)
            rotate(angle);
		polygon = new Polygon (vertexX, vertexY, 3);
	}

	public double perimeter ()
	{
		return side + side2 + sqrt (side * side + side2 * side2);
	}

	public double area ()
	{
		return side * side2 / 2;
	}

	public String getName ()
	{
		return "Right";
	}

	public void fromString (String str)
	{
		String [] parts = str.split(" ");

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
				side = Integer.parseInt(parts[2]);
				side2 = Integer.parseInt(parts[3]);
				color = new Color(Integer.parseInt(parts[4]));
				angle = Double.parseDouble (parts[5]);
			}
			else {
				color = new Color(Integer.parseInt(parts[2]));
				int numSides = Integer.parseInt(parts[3]);
				for (int i = 0; i < numSides; i++) {
					doubleVertexX[i] = Double.parseDouble(parts[4 + (2 * i)]);
					doubleVertexY[i] = Double.parseDouble(parts[5 + (2 * i)]);
				}
				hasVertices = true;	
			}
			setVertices();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("File input error - invalid integer");;
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += side2 + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}

	public void modifyShape (JFrame frame, int x, int y)
	{
		RightDialog rightDialog = new RightDialog (frame, true, x, y, side, side2, angle); 
		if (rightDialog.getAnswer() == true)
		{
			side = rightDialog.getSide ();
			doubleSide = (double) side;
			side2 = rightDialog.getSide2();
			doubleSide2 = (double) side2;
			angle = Math.toRadians(rightDialog.getAngle ());
			color = rightDialog.getColor ();
			setVertices ();
			rotate(angle);
		}
	}
}	
