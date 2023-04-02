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

public abstract class DrawingObject {
    protected double x;
    protected double y;
    protected double tx;
    protected double ty;
    protected double rotation;
    protected double scale;
    protected boolean canMove;

    public DrawingObject(){}
    
    public DrawingObject(double x, double y){
        this.x = x;
        this.y = y;
    }

    public DrawingObject(double x, double y, double r, double s){
        this.x = x;
        this.y = y;
        rotation = r;
        scale = s;
    }

    public DrawingObject(double x, double y, double r, double s, double tx, double ty){
        this.x = x;
        this.y = y;
        rotation = r;
        scale = s;
        this.tx = tx;
        this.ty = ty;
    }

    public DrawingObject(double x, double y, double s){
        this.x = x;
        this.y = y;
        scale = s;
    }

    public void translate(double mx, double my){
        if (canMove){
            x += mx;
            y += my;
        }   
        
    }

    abstract void draw(Graphics2D g2d, AffineTransform reset);
    
}
