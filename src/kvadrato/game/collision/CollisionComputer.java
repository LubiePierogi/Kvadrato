package kvadrato.game.collision;

import kvadrato.utils.vec2.Vec2d;

public final class CollisionComputer
{
  private CollisionComputer(){}
  /**
   * @return null, jeśli nie było kolizji.
   */
  public static CollisionOccurrence computeFromBaked(BakedShape first,BakedShape second)
  {
    if(checkBoundingBoxes(first,second))
      return computeSAT(first,second);
    return null;
  }
  ;
  static boolean checkBoundingBoxes(BakedShape first,BakedShape second)
  {
      // Opieramy się na tym, że prawe max zawsze jest większe niż lewe min,
      // a górne max większe niż dolne min.
      if(first .right<second.left )return false;
      if(second.right<first .left )return false;
      if(first .up   <second.down )return false;
      if(second.up   <first .down )return false;
      return true;
  }
  static CollisionOccurrence computeSAT
    (BakedShape first,BakedShape second)
  {;
    // Jest takie ograniczenie, że to wykrycie działa dobrze tylko
    // przy wypukłych kształtach, ale nie mamy inncyh w grze.

    // No więc sprawdźmy każdy odcinek, jaki mamy.

    double shortestIntersect=1./0.;
    Vec2d vectorOfShortestIntersect=null;
    // Pierwszy wielokąt. i drugi wielokąt
    BakedShape present=first;
    do
    {
      int quantity=present.vertices.size();
      for(int i=0;i<quantity-1;++i)
      {
        Vec2d castingVector=
          present.vertices.get((i+1)%quantity).subD
          (present.vertices.get(i)).spin90().norm();
        double lengthOfIntersection=compareProjections
        (
          castShape(first ,castingVector),
          castShape(second,castingVector)
        );
        if(Double.isNaN(lengthOfIntersection))
          // Jest dziura między obiektami.
          return null;
        if(Math.abs(lengthOfIntersection)<Math.abs(shortestIntersect))
        {
          shortestIntersect=lengthOfIntersection;
          vectorOfShortestIntersect=castingVector;
        }
      }
      if(present==first)present=second;else present=null;
    }while(present!=null);
    if(vectorOfShortestIntersect!=null)
      return new CollisionOccurrence
        (vectorOfShortestIntersect.mulD(shortestIntersect));
    return null;
  }

  static Projection castShape(BakedShape sh,Vec2d vec)
  {
    Projection q=new Projection();
    q.smaller=1./0.;
    q.greater=-1./0.;
    for(Vec2d x:sh.vertices)
    {
      double w=x.x*vec.x+x.y*vec.y;
      if(w>q.greater)q.greater=w;
      if(w<q.smaller)q.smaller=w;
    }
    return q;
  }
  /**
   * @return 'długość' najmniejszego nakładania się, NaN, jeśli się
   * nie nakładają
   */
  static double compareProjections(Projection q,Projection w)
  {
    if(q.greater<w.smaller) return 0./0.; // NaN
    if(w.greater<q.smaller) return 0./0.;

    if(q.greater>w.greater)
      if(q.smaller>w.smaller)
        return w.greater-q.smaller;
      else
        return w.greater-w.smaller;
    else
      if(q.smaller>w.smaller)
        return q.smaller+q.greater;
      else
        return w.smaller-q.greater;
  }
}
