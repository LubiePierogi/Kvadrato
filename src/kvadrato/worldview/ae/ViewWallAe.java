package kvadrato.worldview.ae;

import java.lang.Math;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import kvadrato.game.other.WallColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.WallAe;
import kvadrato.view.Colors;

public class ViewWallAe extends ViewAppearanceElement
{
  private WallColor color;
  private double width;
  private double height;

  public ViewWallAe()
  {}
  public void set(AppearanceElement e)
  {
    WallAe ae=(WallAe)e;
    color=ae.color;
    width=ae.width;
    height=ae.height;
  }
  public void draw(GraphicsContext context)
  {
    context.setFill(Colors.getWallColor(color));
    context.fillRect(-width/2.,-height/2.,width,height);
  }
}
