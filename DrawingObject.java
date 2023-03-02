import java.awt.*;
import java.awt.geom.*;

public abstract class DrawingObject {
    protected double x;
    protected double y;
    protected double rotation;
    protected double tx;
    protected double ty;

    public DrawingObject(){}
    
    public DrawingObject(double x, double y){
        this.x = x;
        this.y = y;
    }

    public DrawingObject(double x, double y, double r){
        this.x = x;
        this.y = y;
        rotation = r;
    }

    public DrawingObject(double x, double y, double r, double tx, double ty){
        this.x = x;
        this.y = y;
        rotation = r;
        this.tx = tx;
        this.ty = ty;
    }

    abstract void draw(Graphics2D g2d, AffineTransform reset);
    // can add animate method
}
