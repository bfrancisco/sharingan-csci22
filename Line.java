import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Line extends DrawingObject{
    private BasicStroke strokeW;    
    private Color color;
    private Line2D.Double line;
    private double radiusCap;

    public Line(double sx, double sy, double radiusCap){
        // start coords is at center. end coords is randomly generated.
        super(sx, sy);
        this.radiusCap = radiusCap; 
        color = new Color(15, 15, 15, 140);
        strokeW = new BasicStroke(1);
        generate();
        
    }

    public void generate(){
        // Randomly generates length of line.
        // length = radiusCap +- radiusCap*0.4
        Random rand = new Random();
        double val = rand.nextDouble((radiusCap*0.4));
        line = new Line2D.Double(x, y, x, y - radiusCap - val);
    }

    public void drawRotate(Graphics2D g2d, AffineTransform reset, int rotate){
        g2d.setPaint(color);
        g2d.setStroke(strokeW);
        g2d.rotate(Math.toRadians(rotate), x, y);
        g2d.draw(line);
        g2d.setTransform(reset);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){}
}
