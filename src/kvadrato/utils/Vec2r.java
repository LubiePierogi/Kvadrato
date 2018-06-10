package kvadrato.utils;
import java.lang.Math;
/**
 * Wektor z dwoma wymiarami i jeszcze z kątem.
 */
public class Vec2r
{
  public final double x;
  public final double y;
  public final double angle;
  public Vec2r()
  {
    x=0.0;y=0.0;angle=0.0;
  }
  public Vec2r(double x,double y,double angle)
  {
    this.x=x;
    this.y=y;
    this.angle=angle;
  }
  public Vec2r(Vec2 v)
  {
    x=v.x;
    y=v.y;
    angle=0;
  }
  public Vec2r(Vec2 v,double a)
  {
    x=v.x;
    y=v.y;
    angle=a;
  }
  public Vec2r add(Vec2r other)
  {
    return new Vec2r(this.x+other.x,this.y+other.y);
  }
  public Vec2r sub(Vec2r other)
  {
    return new Vec2r(this.x-other.x,this.y-other.y);
  }
  public Vec2r mul(double number)
  {
    return new Vec2r(this.x*number,this.y*number);
  }
  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Vec2r rotate(double angle)
  {
    return new Vec2r(x*Math.cos(angle)-y*Math.sin(angle),y*Math.cos(angle)+x*Math.sin(angle));
  }
  public Vec2r neg()
  {
    return new Vec2r(-x,-y,-angle);
  }
  public Vec2r negX()
  {
    return new Vec2r(-x,y);
  }
  public Vec2r negY()
  {
    return new Vec2r(x,-y);
  }
}
