/**
This is a template for a Java file.
@author James Bryan M. Francisco (222677)
@version March 6, 2023
**/
/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

import java.awt.*;
import java.awt.geom.*;

public class MangekyoSharingan extends DrawingObject{
    private double radius;
    Color primary;
    Color primaryDark;
    private double tx;
    private double ty;
    private double boxW;

    private int mangekyoSpeed;
    private int mangekyoRotVal;
    private double radialTolerance;
    private double moveScaling;

    private Circle outerCircle;
    private Circle innerCircle;
    private Mangekyo mangekyo;
    private RadialLine radialLine;
    private Circle pupil;
    private Ellipse highlight;
    private Circle shadowCircleClip;

    public MangekyoSharingan(double x, double y, double scale, double radius, Color primary, Color primaryDark, int tspeed, double boxW){
        // (x, y) is at center.
        super(x, y, scale);
        this.radius = radius;
        this.primary = primary;
        this.primaryDark = primaryDark;
        this.boxW = boxW;
        mangekyoSpeed = tspeed;
        mangekyoRotVal = mangekyoSpeed;
        radialTolerance = 0.14f;
        moveScaling = 1.0f;
        setRotationSpeed(10);
        generateComponents();
    }

    public void generateComponents(){
        outerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.97f, scale, new Color(0, 0, 0, 200), true);
        innerCircle = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, primary, primaryDark, tx, ty);
        mangekyo = new Mangekyo(x-tx*1.1, y-ty*1.1, (radius * 1.345f / 2), mangekyoRotVal, boxW, new Area(innerCircle.getCircle()));
        radialLine = new RadialLine(x-tx*1.7, y-ty*1.7, radius*radialTolerance);
        pupil = new Circle(x-tx*1.7, y-ty*1.7, radius * 0.20f * moveScaling, scale, Color.black, true);
    
        highlight = new Ellipse(x-tx*0.4, y-ty*0.4, radius * 0.46f * (moveScaling * 0.90), radius * 0.31f * (moveScaling * 0.90), new Color(255, 255, 255, 30), new Color(255, 255, 255), 0, radius*0.20f*1.35f, radius);
        shadowCircleClip = new Circle(x-tx*0.35, y-ty*0.35, radius * 0.95f, scale, new Color(0, 0, 0, 30), new Color(0, 0, 0, 95), tx, ty);
    }

    public void setEyeDisplacement(double ex, double ey){
        tx = ex;
        ty = ey;
    }

    public void animateMangekyo(){
        mangekyoRotVal = (mangekyoRotVal + mangekyoSpeed) % 720;
    }

    public void setMoveScaling(double scaling){
        moveScaling = scaling;
    }

    public void setRotationSpeed(int val){
        mangekyoSpeed = val;
        radialTolerance = 0.1f + (0.02*val);
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        generateComponents();
        outerCircle.draw(g2d, reset);
        innerCircle.draw(g2d, reset);
        mangekyo.draw(g2d, reset);
        radialLine.draw(g2d, reset);
        pupil.draw(g2d, reset);
        highlight.draw(g2d, reset);
        shadowCircleClip.clip(g2d, reset, moveScaling);
    }
}
