package kvadrato.utils.vec2;

import java.lang.Math;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;

public class Vec2drs
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
  {x=q.x;y=q.y;angle=a;scale=s;}
  public Vec2drs(Vec2dr q)
  {x=q.x;y=q.y;angle=q.angle;scale=1.;}
  public Vec2drs(Vec2dr q,double s)
  {x=q.x;y=q.y;angle=q.angle;scale=s;}
  public Vec2drs(Vec2drs q)
  {x=q.x;y=q.y;angle=q.angle;scale=q.scale;}

  public Vec2drs mulDRS(double number)
  {
    return new Vec2drs(x*number,y*number,angle*number,scale*number);
  }

  public Vec2drs transform(Vec2drs v)
  {
    return new Vec2drs
    (
      x+scale*(v.x*Math.cos(angle)-v.y*Math.sin(angle)),
      y+scale*(v.y*Math.cos(angle)+v.x*Math.sin(angle)),
      angle+v.angle,
      scale*v.scale
    );
}

}
