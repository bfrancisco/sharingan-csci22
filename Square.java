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
