import java.awt.*;
import java.awt.geom.*;

public class Tomoe extends DrawingObject{
    private Color fillColor;
    Path2D.Double tomoe;

    public Tomoe(double x, double y, double s, Color c, double r, double tx, double ty){
        super(x, y, r, s, tx, ty);
        scale = s;
        fillColor = c;
        tomoe = new Path2D.Double();
        tomoe.moveTo(x+scale*(79.250000), y+scale*(12.750000));
        tomoe.curveTo(x+scale*(67.973205), y+scale*(11.445636), x+scale*(56.547433), y+scale*(12.696558), x+scale*(45.820000), y+scale*(16.410000));
        tomoe.curveTo(x+scale*(59.703361), y+scale*(23.145159), x+scale*(66.775478), y+scale*(38.852030), x+scale*(62.615210), y+scale*(53.711446));
        tomoe.curveTo(x+scale*(58.454943), y+scale*(68.570863), x+scale*(44.255177), y+scale*(78.322135), x+scale*(28.892912), y+scale*(76.869244));
        tomoe.curveTo(x+scale*(13.530647), y+scale*(75.416354), x+scale*(1.410755), y+scale*(63.175894), x+scale*(0.110000), y+scale*(47.800000));
        tomoe.lineTo(x+scale*(0.110000), y+scale*(47.800000));
        tomoe.curveTo(x+scale*(0.000000), y+scale*(46.880000), x+scale*(0.000000), y+scale*(46.000000), x+scale*(0.000000), y+scale*(45.120000));
        tomoe.curveTo(x+scale*(-0.002955), y+scale*(41.140562), x+scale*(0.739866), y+scale*(37.195813), x+scale*(2.190000), y+scale*(33.490000));
        tomoe.curveTo(x+scale*(2.180994), y+scale*(33.471014), x+scale*(2.180994), y+scale*(33.448986), x+scale*(2.190000), y+scale*(33.430000));
        tomoe.curveTo(x+scale*(14.240000), y+scale*(-19.410000), x+scale*(66.320000), y+scale*(4.430000), x+scale*(79.630000), y+scale*(11.510000));
        tomoe.curveTo(x+scale*(79.910603), y+scale*(11.657745), x+scale*(80.046462), y+scale*(11.984172), x+scale*(79.953545), y+scale*(12.287377));
        tomoe.curveTo(x+scale*(79.860627), y+scale*(12.590581), x+scale*(79.565202), y+scale*(12.784841), x+scale*(79.250000), y+scale*(12.750000));
        tomoe.closePath();
    }


    public void draw(Graphics2D g2d, AffineTransform reset){
        //align to center
        double cx = tomoe.getBounds2D().getCenterX() - x;
        double cy = tomoe.getBounds2D().getCenterY() - y;
        g2d.translate(-cx, -cy);
        
        // apply translation
        g2d.translate(-tx, -ty);

        // rotate from center of object
        g2d.rotate(Math.toRadians(rotation), 
            -(g2d.getTransform().getTranslateX() - x), 
            -(g2d.getTransform().getTranslateY() - y));

        g2d.setPaint(fillColor);
        g2d.fill(tomoe);
        g2d.setTransform(reset);
    }


}
