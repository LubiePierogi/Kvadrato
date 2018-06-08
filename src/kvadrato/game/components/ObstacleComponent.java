package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.Vector2d;
import kvadrato.game.other.BackgroundColor;


public class ObstacleComponent extends Component
{
  private BackgroundColor color;
  private BackgroundColor colorNew;

  private double width;
  private double height;


  public ObstacleComponent()
  {
    color=BackgroundColor.WHITE;
    colorNew=null;
    width=1.;
    height=1.;
  }

  public BackgroundColor getColor()
  {
    return color;
  }
  public void setColor(BackgroundColor c)
  {
    colorNew=c;
  }

  public Vector2d getSize()
  {
    return new Vector2d(width,height);
  }

  public void setSize(Vector2d v)
  {
    width=v.x;
    height=v.y;
  }

  public void setSize(double w,double h)
  {
    width=w;
    height=h;
  }

  public void fix(){}
  public void doThings(){}
  public void update()
  {
    if(colorNew!=null)
      color=colorNew;
    colorNew=null;
  }
}
