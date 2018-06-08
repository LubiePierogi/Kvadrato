package kvadrato.game.prefabs;

import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.ObstacleRectangle;
import kvadrato.game.components.ObstacleComponent;
import kvadrato.game.components.Appearance;
import kvadrato.game.Vector2d;
import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

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
    rect.x=0.;
    rect.y=0.;
    rect.angle=0.;
    rect.scale=1.;
    Vector2d v=oc.getSize();
    rect.width=v.x;
    rect.height=v.y;
    list.add(rect);
    return list;
  };
  public void makeEntity(Entity ent)
    throws ClassNotFoundException,InstantiationException,IllegalAccessException
  {
    ent.addComponent("Physics");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");
    ent.addComponent("ObstacleComponent");

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setFn(appearanceFn);
    }

  }
}
