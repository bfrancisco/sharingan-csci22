/**
This is a template for a Java file.
@author James Bryan M. Francisco (222677)
@version March 6, 2023
**/
/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

import java.awt.*;
import java.awt.geom.*;

public class Square extends DrawingObject{
    private Color fillColor;
    private Rectangle2D.Double square;
    public Square(double x, double y, double side, Color c, double tx, double radius){
        // (x y) is bottom right.
        super(x, y);
        fillColor = c;
        square = new Rectangle2D.Double(x, y, side*0.9, side*0.9);
    }

    public void setColor(Color c){
        fillColor = c;
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        g2d.setPaint(fillColor);
        g2d.fill(square);
        g2d.setTransform(reset);
    }
}
