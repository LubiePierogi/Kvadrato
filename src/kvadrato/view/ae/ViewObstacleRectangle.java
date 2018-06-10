package kvadrato.view.ae;

import java.lang.Math;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.game.other.BgColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.ObstacleRectangle;

public class ViewObstacleRectangle extends ViewAppearanceElement
{
  private BgColor bg;
  private double width;
  private double height;
  public ViewObstacleRectangle()
  {
    bg=BgColor.WHITE;
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
    if(bg==null)
    {
      bg=BgColor.WHITE;
    }
    switch(bg)
    {
      case WHITE:context.setFill(new Color(.5,.5,.5,1.));break;
      default:context.setFill(new Color(.2,.4,.6,1.));
    }
    context.fillRect(-.5*width,-.5*height,width,height);
  }
}
