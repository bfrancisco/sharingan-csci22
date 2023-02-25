import java.awt.*;
import java.awt.geom.*;

public class Circle implements DrawingObject{
    // x and y is the center of the circle
    private double x;
    private double y;
    private double r; // size/radius
    private Color fillColor; 
    
    public Circle(double x, double y, double r, Color c){
        this.x = x;
        this.y = y;
        this.r = r;
        this.fillColor = c;
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        Ellipse2D.Double circle = new Ellipse2D.Double(x-r/2, y-r/2, r, r);
        g2d.setColor(fillColor);
        g2d.fill(circle);
    }
}
