import java.awt.*;
import java.awt.geom.*;

public class RadialLine extends DrawingObject{
    private Line[] radialLine;
    
    public RadialLine(double x, double y, double radiusCap){
        // (x y) is at center.
        super(x, y);
        radialLine = new Line[360];
        for (int i = 0; i < 360; i++)
            radialLine[i] = new Line(x, y, radiusCap);
        
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        for (int i = 0; i < 360; i++){
            radialLine[i].generate();
            radialLine[i].drawRotate(g2d, reset, i);
        }
            
    }
}
