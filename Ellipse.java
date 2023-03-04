import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends DrawingObject{
    private double rx;
    private double ry;
    private double tx;
    private double ty;
    private Color solidColor;

    // for gradient
    private boolean isGradient;
    Point2D start, end;
    float[] fractions;
    Color[] colors;
    LinearGradientPaint gradient;
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

    // for oval highlight
    public Ellipse(double x, double y, double rx, double ry, Color g1, Color g2, double tx, double ty, double radius){
        super(x, y);
        this.rx = rx;
        this.ry = ry;
        this.tx = tx;
        this.ty = ty;
        isGradient = true;
        start = new Point2D.Double(x-(radius/2), y-(radius/2));
        end = new Point2D.Double(x, y);
        // System.out.printf("%.2f , %.2f\n%.2f , %.2f\n", start.getX(), start.getY(), end.getX(), end.getY());
        // System.out.printf("%.2f\n", radius);
        fractions = new float[2];
        fractions[0] = 0.00f;
        fractions[1] = 1.00f;
        colors = new Color[2];
        colors[0] = g2;
        colors[1] = g1;
        gradient = new LinearGradientPaint(start, end, fractions, colors);
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

        if (isGradient)
            g2d.setPaint(gradient);
        else
            g2d.setPaint(solidColor);
        g2d.fill(ellipse);
        g2d.setTransform(reset);
    }
}
