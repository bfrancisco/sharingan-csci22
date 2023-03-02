import java.awt.*;
import java.awt.geom.*;

public class Circle extends DrawingObject{
    // x and y is the center of the circle
    private double x;
    private double y;
    private double radius; // size/radius
    private Color fillColor; 
    Ellipse2D.Double circle;
    
    public Circle(double x, double y, double r, Color c){
        super(x, y);
        radius = r;
        this.fillColor = c;
        circle = new Ellipse2D.Double(this.x-radius/2, this.y-radius/2, r, r);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        g2d.setColor(fillColor);
        g2d.fill(circle);
    }

    public Ellipse2D.Double getCircleObject(){
        return circle;
    }
}
