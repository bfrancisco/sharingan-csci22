import java.awt.*;
import java.awt.geom.*;

public interface DrawingObject {
    void draw(Graphics2D g2d, AffineTransform reset);
    // can add animate method
}
