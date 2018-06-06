package kvadrato.game;
import java.lang.Math;
/**
 * Wektor z dwoma wymiarami. Jest to klasa niezmiennicza czy jakoś tak.
 */
public class Vector2d
{
  public final double x;
  public final double y;
  public Vector2d()
  {
    x=0.0;
    y=0.0;
  }
  public Vector2d(double x,double y)
  {
    this.x=x;
    this.y=y;
  }
  public Vector2d add(Vector2d other)
  {
    return new Vector2d(this.x+other.x,this.y+other.y);
  }
  public Vector2d sub(Vector2d other)
  {
    return new Vector2d(this.x-other.x,this.y-other.y);
  }
  public Vector2d mul(double number)
  {
    return new Vector2d(this.x*number,this.y*number);
  }
  public double dist()
  {
    return Math.sqrt(x*x+y*y);
  }
  public Vector2d rotate(double angle)
  {
    return new Vector2d(x*Math.cos(angle)-y*Math.sin(angle),y*Math.cos(angle)+x*Math.sin(angle));
  }
  public Vector2d neg()
  {
    return new Vector2d(-x,-y);
  }
  public Vector2d negX()
  {
    return new Vector2d(-x,y);
  }
  public Vector2d negY()
  {
    return new Vector2d(x,-y);
  }
}
