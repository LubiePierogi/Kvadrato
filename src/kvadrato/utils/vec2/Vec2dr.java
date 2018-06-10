package kvadrato.utils.vec2;

import java.lang.Math;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2drs;

/**
 * Wektor z dwoma wymiarami i jeszcze z kątem.
 */
public class Vec2dr// extends Vec2d
{
  public final double x;
  public final double y;
  public final double angle;
  public Vec2dr()
  {
    x=0.0;y=0.0;angle=0.0;
  }
  public Vec2dr(double x,double y,double angle)
  {
    this.x=x;
    this.y=y;
    this.angle=angle;
  }
  public Vec2dr(Vec2d v)
  {
    x=v.x;y=v.y;angle=0;
  }
  public Vec2dr(Vec2d v,double a)
  {
    x=v.x;y=v.y;angle=a;
  }
  public Vec2dr(Vec2dr q)
  {
    x=q.x;y=q.y;angle=0.;
  }
  public Vec2dr(Vec2drs q)
  {
    x=q.x;y=q.y;angle=q.angle;
  }
  public Vec2dr addDR(Vec2dr q)
  {
    return new Vec2dr(x+q.x,y+q.y,angle+q.angle);
  }
  public Vec2dr subDR(Vec2dr q)
  {
    return new Vec2dr(x-q.x,y-q.y,angle-q.angle);
  }
  public Vec2dr mulD(double number)
  {
    return new Vec2dr(x*number,y*number,angle);
  }
  public Vec2dr mulDR(double number)
  {
    return new Vec2dr(x*number,y*number,angle*number);
  }

  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Vec2dr rotateDR(double a)
  {
    return new Vec2dr
    (
      x*Math.cos(a)-y*Math.sin(a),
      y*Math.cos(a)+x*Math.sin(a),
      angle+a
    );
  }
  public Vec2dr rotateD(double a)
  {
    return new Vec2dr
    (
      x*Math.cos(a)-y*Math.sin(a),
      y*Math.cos(a)+x*Math.sin(a),
      angle
    );
  }
}
