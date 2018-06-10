package kvadrato.utils.vec2;

import java.lang.Math;

import kvadrato.utils.Vec2dr;
import kvadrato.utils.Vec2drs;

/**
 * Wektor z dwoma wymiarami. Jest to klasa niezmiennicza czy jakoś tak.
 */
public class Vec2d
{
  public final double x;
  public final double y;
  public Vec2d()
  {
    x=0.0;
    y=0.0;
  }
  public Vec2d(double x,double y)
  {
    this.x=x;
    this.y=y;
  }
  public Vec2d(Vec2d q)
  {
    x=q.x;y=q.y;
  }
  public Vec2d(Vec2dr q)
  {
    x=q.x;y=q.y;
  }
  public Vec2d(Vec2drs q)
  {
    x=q.x;y=q.y;
  }
  public Vec2d addD(Vec2d other)
  {
    return new Vec2d(this.x+other.x,this.y+other.y);
  }
  public Vec2d subD(Vec2d other)
  {
    return new Vec2d(this.x-other.x,this.y-other.y);
  }
  public Vec2d mulD(double number)
  {
    return new Vec2(this.x*number,this.y*number);
  }
  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Vec2d rotateD(double angle)
  {
    return new Vec2d
    (
      x*Math.cos(angle)-y*Math.sin(angle),
      y*Math.cos(angle)+x*Math.sin(angle)
    );
  }
  public Vec2d negD()
  {
    return new Vec2d(-x,-y);
  }
  public Vec2d negXD()
  {
    return new Vec2d(-x,y);
  }
  public Vec2d negYD()
  {
    return new Vec2d(x,-y);
  }
}
