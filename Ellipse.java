import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends DrawingObject{
    private double rx;
    private double ry;
    private double tx;
    private double ty;
    private Color solidColor;
    Ellipse2D.Double ellipse;
    public Ellipse(double x, double y, double rx, double ry, Color c, double tx, double ty){
        super(x, y);
        this.rx = rx;
        this.ry = ry;
        this.tx = tx;
        this.ty = ty;
        solidColor = c;
        ellipse = new Ellipse2D.Double(x, y, this.rx, this.ry);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        //align to center
        double cx = ellipse.getBounds2D().getCenterX() - x;
        double cy = ellipse.getBounds2D().getCenterY() - y;
        g2d.translate(-cx, -cy);
        
        //apply translation
        g2d.translate(-tx, -ty);

        // rotate from center of object
        g2d.rotate(Math.toRadians(-45), 
            -(g2d.getTransform().getTranslateX() - x), 
            -(g2d.getTransform().getTranslateY() - y));

        g2d.setPaint(solidColor);
        g2d.fill(ellipse);
        g2d.setTransform(reset);
    }
}
