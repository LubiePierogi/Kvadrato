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
    System.err.println("ZXCzxc");
    if(checkBoundingBoxes(first,second))
    {
      System.err.println("!@#@!");
      return computeSAT(first,second);
    }
    System.err.println("DPLFDp");
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
    double angleOfShortestIntersect=0.0;

    // Pierwszy wielokąt. i drugi wielokąt
    BakedShape whichToCheck=first;
    do
    {
      for(int i=0;i<first.vertices.size()-1;++i)
      {
        double angle=
          getLineSegmentAngle(first.vertices.get(i),first.vertices.get(i+1));
        double lengthOfIntersection=compareProjections
        (
          castShapeOnAngle(first ,angle),
          castShapeOnAngle(second,angle)
        );
        if(lengthOfIntersection<shortestIntersect)
        {
          shortestIntersect=lengthOfIntersection;
          angleOfShortestIntersect=angle;
        }
      }
      if(whichToCheck==first) whichToCheck=second;
      else whichToCheck=null;
    }while(whichToCheck!=null);

    return new CollisionOccurrence(new Vec2d(),1.0);
  }

  static double getLineSegmentAngle(Vec2d p1,Vec2d p2)
  {
    return p2.subD(p1).getArgD();
  }
  static Projection castShapeOnAngle(BakedShape sh,double angle)
  {
    Projection q=new Projection();
    q.smaller=1./0.;
    q.greater=-1./0.;
    for(Vec2d x:sh.vertices)
    {
      double w=x.rotateD(angle).y;
      if(w>q.greater)q.greater=w;
      if(w<q.smaller)q.smaller=w;
    }
    return q;
  }
  /**
   * @return 'długość' najmniejszego nakładania się, ujemna, jeśli się
   * nie nakładają
   */
  static double compareProjections(Projection q,Projection w)
  {
    if(q.greater<w.smaller) return -1.;
    if(w.greater<q.smaller) return -1.;

    if(q.greater>w.greater)
      if(q.smaller>w.smaller)
        return w.greater-q.smaller;
      else
        return w.greater-w.smaller;
    else
      if(q.smaller>w.smaller)
        return q.greater-q.smaller;
      else
        return q.greater-w.smaller;
  }
}
