package kvadrato.game.collision;

import java.util.List;
import java.util.ArrayList;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;

/**
 * Jest to klasa zawierające informacje i jakimś zderzeniu dwóch rzeczy
 * przypisywana do jednostek po obliczniu kolizji.
 */
public final class CollisionOccurrence
{
  public final Vec2d center;
  public final double volume;
  CollisionOccurrence(Vec2d center,double volume)
  {
    this.center=center;
    this.volume=volume;
    // Na razie dajemy nic.
  }
  //public static Collision compute(Shape a,Shape b,Vec2dr av,Vec2dr bv)
  //{
  //  ArrayList<Vec2d> q=new ArrayList<Vec2d>();
  //  ArrayList<Vec2d> w=new ArrayList<Vec2d>();
//
//    for(Vec2d x:a.getVertices())
  //  {
    //  q.add(x.rotateD(av.angle).addD(new Vec2d(av)));
    //}
    //for(Vec2d x:b.getVertices())
    //{
    //  w.add(x.rotateD(bv.angle).addD(new Vec2d(bv)));
    //}



    //return null;
  //}
}
