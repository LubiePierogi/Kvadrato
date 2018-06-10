package kvadrato.game.appearance;

import java.util.Objects;

import kvadrato.utils.vec2.Vec2drs;

public class AppearanceElement
{
  public double x;
  public double y;
  public double angle;
  public double scale;

  public Vec2drs getVec()
  {
    return new Vec2drs(x,y,angle,scale);
  }
  public void setVec(Vec2drs v)
  {
    Objects.requireNonNull(v);
    x=v.x;y=v.y;angle=v.angle;scale=v.scale;
  }
}
