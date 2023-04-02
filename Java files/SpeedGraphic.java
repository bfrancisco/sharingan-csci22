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

public class SpeedGraphic extends DrawingObject{
    // displays rotation speed.
    private Square[] squares;
    private int[] squaresOpacity;
    private int speed = 1;
    public SpeedGraphic(double x, double y, double side, double radius){
        // (x y) is top right.
        super(x, y);
        squares = new Square[5];
        squaresOpacity = new int[5];
        for (int i = 0; i < 5; i++){
            squares[i] = new Square(x - side*(5-i), y + side, side, new Color(255, 255, 255, 255), x, radius);
            squaresOpacity[i] = 0;
        }
    }

    public void setSpeed(int s){
        speed = s;
        for (int i = 0; i < 5; i++){
            if (i+1 <= speed/2)
                squaresOpacity[i] = 70;
            else
                squaresOpacity[i] = 0;
        }
    }
 
    public void draw(Graphics2D g2d, AffineTransform reset){
        for(int i = 0; i < 5; i++){
            squares[i].setColor(new Color(255, 255, 255, squaresOpacity[i]));
            squares[i].draw(g2d, reset);
            g2d.setTransform(reset);
        }
    }
}
