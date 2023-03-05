import java.awt.*;
import java.awt.geom.*;

public class MangekyoSharingan extends DrawingObject{
    private double radius;
    Color primary;
    Color primaryDark;
    private double tx;
    private double ty;

    private int ovalSpeed;
    private double radialTolerance;
    private double moveScaling;

    private int[] ovalRotVals;
    private int starRotVal;
    private Circle outerCircle;
    private Circle innerCircle;
    private RadialLine radialLine;
    
    private Circle pupil;
    private Ellipse highlight;
    private Circle shadowCircleClip;

    public MangekyoSharingan(double x, double y, double scale, double radius, Color primary, Color primaryDark, int tspeed){
        // (x, y) is at center.
        super(x, y, scale);
        this.radius = radius;
        this.primary = primary;
        this.primaryDark = primaryDark;
        ovalRotVals = new int[3];
        ovalSpeed = tspeed;
        ovalRotVals[0] = ovalSpeed;
        ovalRotVals[1] = ovalSpeed;
        ovalRotVals[2] = ovalSpeed;
        radialTolerance = 0.14f;
        moveScaling = 1.0f;

        generateComponents();
    }

    public void generateComponents(){
        outerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.97f, scale, new Color(0, 0, 0, 200), true);
        innerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, primary, primaryDark, tx, ty);
        radialLine = new RadialLine(x-tx*1.7, y-ty*1.7, radius*radialTolerance);
        pupil = new Circle(x-tx*1.7, y-ty*1.7, radius * 0.20f * moveScaling, scale, Color.black, true);
    
        highlight = new Ellipse(x-tx*0.4, y-ty*0.4, radius * 0.46f * (moveScaling * 0.90), radius * 0.31f * (moveScaling * 0.90), new Color(255, 255, 255, 30), new Color(255, 255, 255), 0, radius*0.20f*1.35f, radius);
        shadowCircleClip = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, new Color(0, 0, 0, 30), new Color(0, 0, 0, 95), tx, ty);
    }

    public void setEyeDisplacement(double ex, double ey){
        tx = ex;
        ty = ey;
    }

    public void setMoveScaling(double scaling){
        moveScaling = scaling;
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        generateComponents();
        outerCircle.draw(g2d, reset);
        innerCircle.draw(g2d, reset);
        radialLine.draw(g2d, reset);
        pupil.draw(g2d, reset);
        highlight.draw(g2d, reset);
        shadowCircleClip.clip(g2d, reset, moveScaling);
    }
}
