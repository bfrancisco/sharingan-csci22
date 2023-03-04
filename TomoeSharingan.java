import java.awt.*;
import java.awt.geom.*;

public class TomoeSharingan extends DrawingObject{
    private double radius;
    Color tomoeC;
    Color primary;
    Color primaryDark;
    private double tx;
    private double ty;
    protected boolean canMove = true;
    
    private int tomoeSpeed;
    // private int tomoeSpeedVal = 0;

    private int[] tomoeRotVals;
    private Circle outerCircle;
    private Circle innerCircle;
    private Circle outlineCircle;
    private Circle pupil;
    private Tomoe[] tomoe;
    private Ellipse highlight;
    private Circle shadowCircleClip;

    public TomoeSharingan(double x, double y, double scale, double radius, Color tomoeC, Color primary, Color primaryDark, int tspeed){
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
       
        generateComponents();
    }

    public void getEyeDisplacement(double ex, double ey){
        if (canMove){
            tx = ex;
            ty = ey;
        }
        else{
            tx = 0;
            ty = 0;
        }
    }

    public void generateComponents(){
        // System.out.printf("%.2f , %.2f\n", tx, ty);
        outerCircle = new Circle(x, y, radius, scale, tomoeC, true);
        innerCircle = new Circle(x, y, radius * 0.95f, scale, primary, primaryDark, tx, ty);
        outlineCircle = new Circle(x-tx, y-ty, radius * 0.54f, scale, tomoeC, false);
        pupil = new Circle(x-tx*1.7, y-ty*1.7, radius * 0.20f, scale, tomoeC, true);
        tomoe = new Tomoe[3];
        for (int i = 0; i < 3; i++)
            tomoe[i] = new Tomoe(x-tx, y-ty, radius/475.0f, tomoeC, (double)(i * 120.0f - ((double) tomoeRotVals[i] / 2)), 0, radius*0.29f); // 0.29f on PC
        highlight = new Ellipse(x-tx*0.2, y-ty*0.2, radius * 0.46f, radius * 0.31f, new Color(255, 255, 255, 30), new Color(255, 255, 255), 0, radius*0.20f*1.35f, radius);
        shadowCircleClip = new Circle(x, y, radius * 0.95f, scale, new Color(0, 0, 0, 30), new Color(0, 0, 0, 95), tx, ty);

        // tomoeSpeedVal = (tomoeSpeedVal + tomoeSpeed) % 360;
    }

    public void animateTomoe(){
        for (int i = 0; i < 3; i++){
            tomoeRotVals[i] = (tomoeRotVals[i] + tomoeSpeed) % 720;
        }
    }

    // public void setTomoeSpeed(int val){
    //     tomoeSpeed = val;
    // }

    public void draw(Graphics2D g2d, AffineTransform reset){
        generateComponents();
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
