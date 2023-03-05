import java.awt.*;
import java.awt.geom.*;

public class SpeedGraphic extends DrawingObject{
    // displays rotation speed.
    private Square[] squares;
    private int[] squaresOpacity;
    private int speed = 1;
    public SpeedGraphic(double x, double y, double side, double radius){
        // (x y) is bottom right.
        super(x, y);
        squares = new Square[5];
        squaresOpacity = new int[5];
        // System.out.printf("%.2f, %.2f\n", x-side, y-side);
        for (int i = 0; i < 5; i++){
            squares[i] = new Square(x - side*(5-i), y - side, side, new Color(255, 255, 255, 255), x, radius);
            squaresOpacity[i] = 0;
        }
    }

    public void setSpeed(int s){
        speed = s;
        for (int i = 0; i < 5; i++){
            if (i+1 <= speed/2)
                squaresOpacity[i] = 140;
            else
                squaresOpacity[i] = 0;
        }
    }
 
    public void draw(Graphics2D g2d, AffineTransform reset){
        for(int i = 0; i < 5; i++){
            squares[i].setColor(new Color(255, 255, 255, squaresOpacity[i]));
            squares[i].draw(g2d, reset);
            g2d.setTransform(reset);
        }
    }
}
