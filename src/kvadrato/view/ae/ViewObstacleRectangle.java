package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;
import kvadrato.game.appearance.ObstacleRectangle;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.other.BackgroundColor;
import kvadrato.game.Vector2d;

public class ViewObstacleRectangle extends ViewAppearanceElement
{
  private BackgroundColor bg;
  private double width;
  private double height;
  public ViewObstacleRectangle()
  {
    bg=BackgroundColor.WHITE;
    width=1.;
    height=1.;
  }
  public void set(AppearanceElement e)
  {
    ObstacleRectangle ee=(ObstacleRectangle)e;
    bg=ee.color;
    width=ee.width;
    height=ee.height;
  }
  public void draw(GraphicsContext context)
  {
    ////System.out.println("10 10 7");
    if(bg==null)
    {
      bg=BackgroundColor.WHITE;
    }
    switch(bg)
    {
      case WHITE:
        context.setFill(new Color(.5,.5,.5,1.));
        break;
      default:
        context.setFill(new Color(.2,.4,.6,1.));
    }
    context.fillRect(-.5*width,-.5*height,width,height);
  }
}
