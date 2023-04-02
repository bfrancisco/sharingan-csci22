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

public class RectGradient extends DrawingObject{
    private double width;
    private double height;
    Rectangle2D.Double rect;
    Point2D center;
    float[] fractions;
    Color[] colors;
    RadialGradientPaint gradient;

    public RectGradient(double x, double y, double w, double h, float d1, float d2, Color c1, Color c2){
        super(x, y);
        width = w;
        height = h;
        rect = new Rectangle2D.Double(0,0,width,height);
        center = new Point2D.Double(width/2, height/2);
        fractions = new float[2];
        fractions[0] = 0.41f;
        fractions[1] = 1.0f;
        colors = new Color[2];
        colors[0] = c1;
        colors[1] = c2;
        gradient = new RadialGradientPaint(center, (float) (height/2), fractions, colors);
    }


    public void draw(Graphics2D g2d, AffineTransform reset){
        g2d.setPaint(gradient);
        g2d.fill(rect);
        g2d.setTransform(reset);
    }
}
