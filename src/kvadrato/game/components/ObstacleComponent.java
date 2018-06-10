package kvadrato.game.components;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.game.Component;
import kvadrato.game.other.BgColor;


public class ObstacleComponent extends Component
{
  private double width;
  private double height;

  private double widthNew;
  private double heightNew;

  public ObstacleComponent()
  {
    width=1.;
    height=1.;
    widthNew=1.;
    heightNew=1.;
  }
  public Vec2d getSize()
  {
    return new Vec2d(width,height);
  }
  public void setSize(Ved2d v)
  {
    widthNew=v.x;
    heightNew=v.y;
  }

  public void setSize(double w,double h)
  {
    widthNew=w;
    heightNew=h;
  }

  public void fix(){}
  public void doThings(){}
  public void update()
  {
    width=widthNew;
    height=heightNew;
  }
}
