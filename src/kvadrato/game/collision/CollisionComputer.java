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
    // Opieramy się na tym, że prawe max zawsze jest większe niż lewe min,
    // a górne max większe niż dolne min.
    if(first .right<second.left )return null;
    if(second.right<first .left )return null;
    if(first .up   <second.down )return null;
    if(second.up   <first .down )return null;
    return new CollisionOccurrence(new Vec2d(),1.);
  }
  ;
}
