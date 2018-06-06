package kvadrato.game;

import kvadrato.game.Vector2d;

public final class Eye
{
  public final double x;
  public final double y;
  public final double angle;
  public final double scale;
  public Eye()
  {
    x=0.0;
    y=0.0;
    angle=0.0;
    scale=1.0;
  }
  public Eye(double x,double y,double angle,double scale)
  {
    this.x=x;
    this.y=y;
    this.angle=angle;
    this.scale=scale;
  }
  public Vector2d getPlace()
  {
    return new Vector2d(x,y);
  }
}
