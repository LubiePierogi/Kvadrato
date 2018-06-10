package kvadrato.utils;
import java.lang.Math;
/**
 * Wektor z dwoma wymiarami. Jest to klasa niezmiennicza czy jakoś tak.
 */
public class Vec2
{
  public final double x;
  public final double y;
  public Vec2()
  {
    x=0.0;
    y=0.0;
  }
  public Vec2(double x,double y)
  {
    this.x=x;
    this.y=y;
  }
  public Vec2 add(Vec2 other)
  {
    return new Vec2(this.x+other.x,this.y+other.y);
  }
  public Vec2 sub(Vec2 other)
  {
    return new Vec2(this.x-other.x,this.y-other.y);
  }
  public Vec2 mul(double number)
  {
    return new Vec2(this.x*number,this.y*number);
  }
  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Vec2 rotate(double angle)
  {
    return new Vec2(x*Math.cos(angle)-y*Math.sin(angle),y*Math.cos(angle)+x*Math.sin(angle));
  }
  public Vec2 neg()
  {
    return new Vec2(-x,-y);
  }
  public Vec2 negX()
  {
    return new Vec2(-x,y);
  }
  public Vec2 negY()
  {
    return new Vec2(x,-y);
  }
}
