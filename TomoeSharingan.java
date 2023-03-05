import java.awt.*;
import java.awt.geom.*;

public class TomoeSharingan extends DrawingObject{
    private double radius;
    Color tomoeC;
    Color primary;
    Color primaryDark;
    private double tx;
    private double ty;
    
    private int tomoeSpeed;
    private double radialTolerance;
    private double moveScaling;

    private int[] tomoeRotVals;
    private Circle outerCircle;
    private Circle innerCircle;
    private Circle outlineCircle;
    private RadialLine radialLine;
    private Circle pupil;
    private Tomoe[] tomoe;
    private Ellipse highlight;
    private Circle shadowCircleClip;
    
    public TomoeSharingan(double x, double y, double scale, double radius, Color tomoeC, Color primary, Color primaryDark, int tspeed){
        // (x, y) is at center.
        super(x, y, scale);
        this.radius = radius;
        this.tomoeC = tomoeC;
        this.primary = primary;
        this.primaryDark = primaryDark;
        tomoeRotVals = new int[3];
        tomoeSpeed = tspeed;
        tomoeRotVals[0] = tomoeSpeed;
        tomoeRotVals[1] = tomoeSpeed;
        tomoeRotVals[2] = tomoeSpeed;
        radialTolerance = 0.14f;
        moveScaling = 1.0f;
        
        generateComponents();
    }

    public void generateComponents(){
        // System.out.printf("%.2f , %.2f\n", tx, ty);
        outerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius, scale, tomoeC, true);
        innerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, primary, primaryDark, tx, ty);
        outlineCircle = new Circle(x-tx, y-ty, radius * 0.54f, scale, tomoeC, false);
        radialLine = new RadialLine(x-tx*1.7, y-ty*1.7, radius*radialTolerance);
        pupil = new Circle(x-tx*1.7, y-ty*1.7, radius * 0.20f * moveScaling, scale, tomoeC, true);
        tomoe = new Tomoe[3];
        for (int i = 0; i < 3; i++)
            tomoe[i] = new Tomoe(x-tx, y-ty, 1, tomoeC, (double)(i * 120.0f - ((double) tomoeRotVals[i] / 2)), 0, radius*0.29f); // 0.29f on PC
    
        highlight = new Ellipse(x-tx*0.4, y-ty*0.4, radius * 0.46f, radius * 0.31f, new Color(255, 255, 255, 30), new Color(255, 255, 255), 0, radius*0.20f*1.35f, radius);
        shadowCircleClip = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, new Color(0, 0, 0, 30), new Color(0, 0, 0, 95), tx, ty);
        
    }

    public void setEyeDisplacement(double ex, double ey){
        tx = ex;
        ty = ey;
    }

    public void setMoveScaling(double scaling){
        moveScaling = scaling;
    }

    public void animateTomoe(){
        for (int i = 0; i < 3; i++){
            tomoeRotVals[i] = (tomoeRotVals[i] + tomoeSpeed) % 720;
        }
    }

    public void setRotationSpeed(int val){
        tomoeSpeed = val;
        radialTolerance = 0.1f + (0.02*val);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        generateComponents();
        outerCircle.draw(g2d, reset);
        innerCircle.draw(g2d, reset);
        outlineCircle.draw(g2d, reset);
        radialLine.draw(g2d, reset);
        pupil.draw(g2d, reset);
        for (int i = 0; i < 3; i++)
            tomoe[i].draw(g2d, reset);
        highlight.draw(g2d, reset);
        shadowCircleClip.clip(g2d, reset);
    }
}
