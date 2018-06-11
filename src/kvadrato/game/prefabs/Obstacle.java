package kvadrato.game.prefabs;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.components.ObstacleComponent;
import kvadrato.game.components.Appearance;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.ObstacleRectangle;

/**
 * Klasa przeszkód, które trzeba omijać.
 */
public class Obstacle extends Prefab
{
  private final static Function<Entity,List<AppearanceElement>>
    appearanceFn
    =ent->
  {
    ArrayList<AppearanceElement> list=new ArrayList<AppearanceElement>();
    ObstacleRectangle rect=new ObstacleRectangle();
    ObstacleComponent oc=
      (ObstacleComponent)ent.getComponent("ObstacleComponent");
    BgColorComponent bg=(BgColorComponent)ent.getComponent("BgColorComponent");
    rect.x=0.;
    rect.y=0.;
    rect.angle=0.;
    rect.scale=1.;
    Vec2d v=oc.getSize();
    rect.width=v.x;
    rect.height=v.y;
    rect.color=bg.getColor();
    list.add(rect);
    return list;
  };
  public void makeEntity(Entity ent)
    throws GameException
  {
    ent.addComponent("Physics");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");
    ent.addComponent("BgColorComponent");
    ent.addComponent("ObstacleComponent");

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setFn(appearanceFn);
      q.setRenderDistanceFn(renderDistanceFn);
    }
  }
  private final static Function<Entity,Double>renderDistanceFn=ent->
  {
    return
      ((ObstacleComponent)ent.getComponent("ObstacleComponent"))
      .getSize().dist();
  };
}
