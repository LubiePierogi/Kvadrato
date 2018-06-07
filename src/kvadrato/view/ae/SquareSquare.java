package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class SquareSquare extends ViewAppearanceElement
{
  public void draw
    (GraphicsContext context,double x,double y,double angle,double scale)
  {
    x=200.0*x;
    y=-200.0*y;
    double a,b,s,c;
    a=0.25*scale*200;
    b=a/2;
    s=Math.sin(angle);
    c=Math.cos(angle);
    double[]tabX=new double[4];
    double[]tabY=new double[4];
    tabX[0]=x+c*b-s*b;
    tabX[1]=x+c*b+s*b;
    tabX[2]=x-c*b+s*b;
    tabX[3]=x-c*b-s*b;
    tabX[0]=y-c*b+s*b;
    tabX[1]=y+c*b+s*b;
    tabX[2]=y+c*b-s*b;
    tabX[3]=y-c*b-s*b;
    context.fillPolygon(tabX,tabY,4);
    context.setFill(Color.ORANGE);
  }
}
