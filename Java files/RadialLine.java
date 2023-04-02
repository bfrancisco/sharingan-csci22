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

public class RadialLine extends DrawingObject{
    private Line[] radialLine;
    
    public RadialLine(double x, double y, double radiusCap){
        // (x y) is at center.
        super(x, y);
        radialLine = new Line[360];
        for (int i = 0; i < 360; i++)
            radialLine[i] = new Line(x, y, radiusCap);
        
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        for (int i = 0; i < 360; i++){
            radialLine[i].generate();
            radialLine[i].drawRotate(g2d, reset, i);
        }
            
    }
}
