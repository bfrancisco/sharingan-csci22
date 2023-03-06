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
    private Area aBounds;
    public Mangekyo(double x, double y, double d, double rotation, double boxw, Area aBounds){
        super(x, y);
        distance = d;
        this.rotation = rotation;
        boxWidth = boxw;
        circles = new Circle[6];
        tX = new double[6];
        tY = new double[6];
        this.aBounds = aBounds; 
        generate();
    }

    public void generate(){
        double deg = -rotation;
        for (int i = 0; i < 6; i++){
            tX[i] = distance * Math.cos(Math.toRadians(deg));
            tY[i] = distance * Math.sin(Math.toRadians(deg));

            deg += 60.0;
            if (deg >= 360.00f)
                deg -= 360.00f;
        }

        for (int i = 0; i < 6; i++){
            circles[i] = new Circle(x + tX[i], y + tY[i], boxWidth);
        }

    }

    public void addRotate(double inc){
        rotation += inc;
        if (rotation >= 360.00f) rotation -= 360.00f;
    }

    public void draw(Graphics2D g2d, AffineTransform reset){
        Area sharpEllipse1 = new Area(circles[0].getCircle());
        sharpEllipse1.intersect(new Area(circles[3].getCircle()));
        Area sharpEllipse2 = new Area(circles[1].getCircle());
        sharpEllipse2.intersect(new Area(circles[4].getCircle()));
        Area sharpEllipse3 = new Area(circles[2].getCircle());
        sharpEllipse3.intersect(new Area(circles[5].getCircle()));
        
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(sharpEllipse1);
        g2d.draw(sharpEllipse2);
        g2d.draw(sharpEllipse3);

        Area star = new Area(); // curved hexagram
        star.add(sharpEllipse1);
        star.add(sharpEllipse2);
        star.add(sharpEllipse3);

        g2d.draw(star); 

        aBounds.subtract(star);
        g2d.fill(aBounds);
        g2d.setTransform(reset);
    }
}
