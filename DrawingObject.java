import java.awt.*;
import java.awt.geom.*;

public abstract class DrawingObject {
    protected double x;
    protected double y;
    protected double rotation;
    protected double tx;
    protected double ty;
    protected double scale;

    public DrawingObject(){}
    
    public DrawingObject(double x, double y){
        this.x = x;
        this.y = y;
    }

    public DrawingObject(double x, double y, double r, double s){
        this.x = x;
        this.y = y;
        rotation = r;
        scale = s;
    }

    public DrawingObject(double x, double y, double r, double s, double tx, double ty){
        this.x = x;
        this.y = y;
        rotation = r;
        scale = s;
        this.tx = tx;
        this.ty = ty;
    }

    public DrawingObject(double x, double y, double s, double tx, double ty){
        this.x = x;
        this.y = y;
        scale = s;
        this.tx = tx;
        this.ty = ty;
    }

    abstract void draw(Graphics2D g2d, AffineTransform reset);
    // can add animate method
}
