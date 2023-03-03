import java.awt.*;
import java.awt.geom.*;

public class TomoeSharingan extends DrawingObject{
    private Circle outerCircle;
    private Circle innerCircle;
    private Circle outlineCircle;
    private Circle pupil;
    private Tomoe[] tomoe;
    private Ellipse highlight;
    private Circle shadowCircleClip;

    public TomoeSharingan(double x, double y, double scale, double radius, double tx, double ty, Color tomoeC, Color primary, Color primaryDark){
        super(x, y, scale, tx, ty);
        outerCircle = new Circle(x, y, radius, scale, tomoeC, true);
        innerCircle = new Circle(x, y, radius * 0.95f, scale, primary, primaryDark);
        outlineCircle = new Circle(x, y, radius * 0.54f, scale, tomoeC, false);
        pupil = new Circle(x, y, radius * 0.20f, scale, tomoeC, true);
        tomoe = new Tomoe[3];
        for (int i = 0; i < 3; i++)
            tomoe[i] = new Tomoe(x, y, radius/475.0f, tomoeC, (double)(i * 120.0f), 0, radius*0.29f); // 0.29f on PC
        highlight = new Ellipse(x, y, radius * 0.46f, radius * 0.31f, new Color(255, 255, 255, 50), 0, radius*0.20f*1.35f);
        shadowCircleClip = new Circle(x, y, radius * 0.95f, scale, new Color(0, 0, 0, 45), new Color(0, 0, 0, 95));
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        outerCircle.draw(g2d, reset);
        innerCircle.draw(g2d, reset);
        outlineCircle.draw(g2d, reset);
        pupil.draw(g2d, reset);
        for (int i = 0; i < 3; i++)
            tomoe[i].draw(g2d, reset);
        highlight.draw(g2d, reset);
        shadowCircleClip.clip(g2d, reset);
    }
}
