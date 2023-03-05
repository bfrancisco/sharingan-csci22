import java.awt.*;
import java.awt.geom.*;

public class Mangekyo extends DrawingObject{
    private Color color = new Color(0, 0, 0, 150);
    private double distance; // distance of to-clip circle from center
    private double rotation;
    private double boxWidth; // min(width, height)
    private Circle[] circles; // (0, 1), (2, 3), (4, 5)
    private double[] tX;
    private double[] tY;
    public Mangekyo(double x, double y, double d, double rotation, double boxw){
        super(x, y);
        distance = d;
        this.rotation = rotation;
        boxWidth = boxw;
        circles = new Circle[6];
        tX = new double[6];
        tY = new double[6];

        generate();
    }

    public void generate(){
        double deg = rotation;
        for (int i = 0; i < 6; i++){
            tX[i] = distance * Math.cos(Math.toRadians(deg));
            tY[i] = distance * Math.sin(Math.toRadians(deg));

            deg += 60.0;
            if (deg >= 360.00f)
                deg -= 360.0f; 
        }

        for (int i = 0; i < 6; i++){
            circles[i] = new Circle(x + tX[i], y + tY[i], boxWidth);
        }

    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        for (int i = 0; i < 6; i++){
            circles[i].drawMangekyo(g2d, reset, color);
        }
    }
}
