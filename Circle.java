import java.awt.*;
import java.awt.geom.*;

public class Circle extends DrawingObject{
    // x and y is the center of the circle
    private double radius;
    private double scale;

    private boolean isGradient;
    private boolean isFill;

    private Color solidColor;

    // for gradient
    Point2D center;
    float[] fractions;
    Color[] colors;
    RadialGradientPaint gradient;

    Ellipse2D.Double circle;
    
    public Circle(double x, double y, double r, double s, Color c, boolean isfill){
        super(x, y);
        radius = r;
        scale = s;
        this.solidColor = c;
        isGradient = false;
        isFill = isfill;
        circle = new Ellipse2D.Double(this.x-radius*scale/2, this.y-radius*scale/2, radius*scale, radius*scale);
    }

    public Circle(double x, double y, double r, double s, Color g1, Color g2, double tx, double ty){
        super(x, y);
        radius = r;
        scale = s;
        isGradient = true;
        isFill = true;
        center = new Point2D.Double(x-tx, y-ty);
        fractions = new float[4];
        fractions[0] = 0.0f;
        fractions[1] = 0.35f; 
        fractions[2] = 0.80f;
        fractions[3] = 1.0f;
        colors = new Color[4];
        colors[0] = Color.BLACK;
        colors[1] = g1;
        colors[2] = g1;
        colors[3] = g2;
        gradient = new RadialGradientPaint(center, (float) radius/2, fractions, colors);
        circle = new Ellipse2D.Double(this.x-radius*scale/2, this.y-radius*scale/2, radius*scale, radius*scale);
    }

    public Circle(double x, double y, double radius){
        super(x, y);
        circle = new Ellipse2D.Double(this.x-radius/2, this.y-radius/2, radius, radius);
    }

    public Ellipse2D.Double getCircle(){
        return circle;
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        if (!isGradient)
            g2d.setPaint(solidColor);
        else
            g2d.setPaint(gradient);
        
        if (isFill)
            g2d.fill(circle);
        else{
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(circle);
        }

        g2d.setTransform(reset);
    }

    public void drawMangekyo(Graphics2D g2d, AffineTransform reset, Color c){
        g2d.setPaint(c);
        g2d.draw(circle);
        g2d.setTransform(reset);
    }

    public void clip(Graphics2D g2d, AffineTransform reset, double scaling){
        // reverse setClip. Create Area of whole canvas -> subtract wanted area -> setclip -> voila
        // https://stackoverflow.com/questions/1241253/inside-clipping-with-java-graphics
        
        Ellipse2D circle2 = new Ellipse2D.Double(this.x-radius/2 -(radius * 0.20f * (scaling * 0.90)), this.y-radius/2 -(radius * 0.20f * (scaling * 0.90)), radius*scale, radius*scale);
        Area toSubtract = new Area(circle2);
        Area out = new Area(new Rectangle2D.Double(0, 0, x*2, y*2));
        out.subtract(toSubtract);
        g2d.setPaint(gradient);
        g2d.setClip(out);
        g2d.fill(circle);
        g2d.setTransform(reset);
    }
}
