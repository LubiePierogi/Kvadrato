package kvadrato.game;
import java.lang.Math;

public class Transform
{
  final public double x;
  final public double y;
  final public double angle;
  public Transform()
  {
    x=0.0;y=0.0;angle=0.0;
  }
  public Transform(double x,double y,double angle)
  {
    this.x=x;
    this.y=y;
    this.angle=angle;
  }
  public Transform(Vector2d v)
  {
    x=v.x;
    y=v.y;
    angle=0;
  }
  public Transform(Vector2d v,double a)
  {
    x=v.x;
    y=v.y;
    angle=a;
  }
  public Transform add(Transform other)
  {
    return new Transform
    (
      this.x+other.x*Math.cos(angle)-other.y*Math.sin(angle),
      this.y+other.y*Math.cos(angle)+other.x*Math.sin(angle),
      this.angle+other.angle
    );
  }
  public Transform addVec(Transform other)
  {
    return new Transform
    (
      this.x+other.x,
      this.y+other.y,
      this.angle+other.angle
    );
  }
  public Transform sub(Transform other)
  {
    return new Transform
    (
      this.x-other.x*Math.cos(angle)+other.y*Math.sin(angle),
      this.y-other.y*Math.cos(angle)-other.x*Math.sin(angle),
      this.angle-other.angle
    );
  }
  public Transform subVec(Transform other)
  {
    return new Transform
    (
      this.x-other.x,
      this.y-other.y,
      this.angle-other.angle
    );
  }
  public Transform mul(double number)
  {
    return new Transform(this.x*number,this.y*number,this.angle*number);
  }
  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Transform rotate(double angle)
  {
    return new Transform(x*Math.cos(angle)-y*Math.sin(angle),y*Math.cos(angle)+x*Math.sin(angle),this.angle+angle);
  }
  public Transform neg()
  {
    return new Transform(-x,-y,-angle);
  }
}
