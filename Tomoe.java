import java.awt.*;
import java.awt.geom.*;

public class Tomoe implements DrawingObject{
    private double x;
    private double y;
    private double r;
    private Color fillColor;
    private Circle circle;
    public Tomoe(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
        fillColor = Color.BLACK;
        circle = new Circle(this.x, this.y, this.r, fillColor);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        circle.draw(g2d, reset);
    }

}
