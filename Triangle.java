// File: Triangle.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Triangle. 

import java.awt.*;
import static java.lang.Math.*;
import javax.swing.*;

public class Triangle extends Shape
{
	protected int [] vertexX = new int [3];
	protected int [] vertexY = new int [3];
	protected double [] doubleVertexX = new double [3];
	protected double [] doubleVertexY = new double [3];
	protected Polygon polygon = new Polygon (vertexX, vertexY, 3);
	protected double angle = 0.0;

	Triangle ()
	{
	}

	public String getName ()
	{
		return "Triangle";
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillPolygon (vertexX, vertexY, 3);
		g2.drawPolygon (vertexX, vertexY, 3);
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point

		if (this.isSelected == true) {
			g2.setPaint (Color.WHITE);
			g2.drawPolygon (vertexX, vertexY, 3);
			g2.setPaint (color);
		}
	}

	public boolean isIn (int x, int y)
	{
		return polygon.contains (x, y);
	}

	public void move (int dx, int dy)
	{
		centerX += dx;
		centerY += dy;
		for (int i = 0; i < 3; i++) {
			doubleVertexX[i] += dx;
			doubleVertexY[i] += dy;
		}
		for (int i = 0; i < 3; i++) {
			vertexX[i] = (int) (doubleVertexX[i] + .5);
			vertexY[i] = (int) (doubleVertexY[i] + .5);
		}
		polygon = new Polygon(vertexX, vertexY, 3);
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
		return string;
	}

	public void rotate (double amount)
	{
		angle += amount;
		for (int i = 0; i < 3; i++) {
			doubleVertexX[i] = vertexX[i];
			doubleVertexY[i] = vertexY[i];
		}
		for (int i = 0; i < 3; i++) {
			double x = doubleVertexX[i] - centerX;
			double y = doubleVertexY[i] - centerY;
			doubleVertexX[i] = x * cos(amount) - y * sin(amount);
			doubleVertexY[i] = x * sin(amount) + y * cos(amount);
			doubleVertexX[i] += centerX;
			doubleVertexY[i] += centerY;
		}
		for (int i = 0; i < 3; i++) {
			vertexX[i] = (int)(doubleVertexX[i] + 0.5);
			vertexY[i] = (int)(doubleVertexY[i] + 0.5);
		}
		polygon = new Polygon(vertexX, vertexY, 3);
	}

	public void resize(double n)
	{
		double tX = 0;
		double tY = 0;
		for (int i = 0; i < 3;  i++) {
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
		for (int i = 0; i < 3; i++) {
			vertexX[i] = (int) (doubleVertexX[i] + .5);
			vertexY[i] = (int) (doubleVertexY[i] + .5);
		}
		polygon = new Polygon(vertexX, vertexY, 3);
	}

	public double area() {
		return 0;
	}

	public void setDoubleVertices() {
		for (int i = 0; i < 3; i++) {
			doubleVertexX[i] = vertexX[i];
			doubleVertexY[i] = vertexY[i];
		}
	}
}
