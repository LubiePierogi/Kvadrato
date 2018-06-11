package kvadrato.game.components;

import java.util.Objects;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.game.Component;
import kvadrato.game.other.WallColor;

public class WallComponent extends Component
{
  private double width;
  private double height;

  private double widthNew;
  private double heightNew;

  private WallColor color;
  private WallColor colorNew;

  public Vec2d getSize()
  {
    return new Vec2d(width,height);
  }

  public void setSize(Vec2d q)
  {
    Objects.requireNonNull(q);
    widthNew=q.x;
    heightNew=q.y;
  }

  public WallColor getColor()
  {
    return color;
  }

  public void setColor(WallColor q)
  {
    Objects.requireNonNull(q);
    colorNew=q;
  }

  public void fix(){}
  public void doThings(){}
  public void update()
  {
    width=widthNew;
    height=heightNew;
    color=colorNew;
  }
}
