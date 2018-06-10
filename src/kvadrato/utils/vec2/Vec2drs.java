package kvadrato.utils.vec2;

import java.lang.Math;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;

/**
 * He he
 */
public class Vec2drs// extends Vec2dr
{
  public final double x;
  public final double y;
  public final double angle;
  public final double scale;

  public Vec2drs()
  {x=0.;y=0.;angle=0.;scale=1.;}
  public Vec2drs(double x,double y,double angle,double scale)
  {
    this.x=x;
    this.y=y;
    this.angle=angle;
    this.scale=scale;
  }
  public Vec2drs(Vec2d q)
  {x=q.x;y=q.y;angle=0.;scale=1.;}
  public Vec2drs(Vec2d q,double a,double s)
  {x=q.x;y=q.y;angle=a;scale=s}
  public Vec2drs(Vec2dr q)
  {x=q.x;y=q.y;angle=q.angle;scale=1.;}
  public Vec2drs(Vec2dr q,double s)
  {x=q.x;y=q.y;angle=q.angle;scale=s}
  public Vec2drs(Vec2drs q)
  {x=q.x;y=q.y;angle=q.scale;scale=q.scale}
}
