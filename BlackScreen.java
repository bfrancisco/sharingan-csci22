import java.awt.*;
import java.awt.geom.*;

public class BlackScreen extends DrawingObject{
    private double width;
    private double height;
    private Rectangle2D.Double rect;
    private Color c;

    public BlackScreen(double x, double y, double w, double h, Color c){
        super(x, y);
        width = w;
        height = h;
        this.c = c;
        rect = new Rectangle2D.Double(0, 0, width, height);
    }

    public void setColor(Color c){
        this.c = c;
        // System.out.println(c.getAlpha());
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);
        g2d.setTransform(reset);
        System.out.println("happen");
    }

    
}
