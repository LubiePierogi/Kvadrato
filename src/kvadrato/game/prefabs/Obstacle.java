package kvadrato.game.prefabs;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.components.Physics;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.components.ObstacleComponent;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Collider;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.ObstacleRectangle;
import kvadrato.game.collision.ElementaryShape;

/**
 * Klasa przeszkód, które trzeba omijać.
 */
public class Obstacle extends Prefab
{
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
      q.setListFn(appearanceFn);
      q.setRenderDistanceFn(renderDistanceFn);
    }

    // Collider
    {
      Collider q=(Collider)ent.getComponent("Collider");
      q.setShapeFn(shapeFn);
      q.setOnCollideFn(onCollideFn);
    }
  }
  private final static Appearance.ListFnType
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
  private final static Appearance.RenderDistanceFnType renderDistanceFn=ent->
  {
    return
      ((ObstacleComponent)ent.getComponent("ObstacleComponent"))
      .getSize().dist();
  };
  private final static Collider.ShapeFnType shapeFn=ent->
  {
    ObstacleComponent w=
      (ObstacleComponent)ent.getComponent("ObstacleComponent");
    Vec2d size=w.getSize().mulD(.5);
    try
    {
      return new ElementaryShape(new Vec2d[]
      {
        new Vec2d( size.x, size.y),
        new Vec2d( size.x,-size.y),
        new Vec2d(-size.x,-size.y),
        new Vec2d(-size.x, size.y)
      });
    }
    catch(GameException exc)
    {
      System.err.println("To tylko na razie!!!\nA oprócz tego, to jest błąd "+
      "w przeszkodzie!");
    }
    return null;
  };
  private final static Collider.OnCollideFnType onCollideFn=(e,o,c)->
  {
    Physics ph=(Physics)o.getComponent("Physics");
    Vec2dr p=ph.getPlace();
    Vec2dr v=ph.getVelocity();
    ph.addPlace(v.mulDR(-4.5).mulDR(e.getDelta()));
    ph.addVelocity(v.mulDR(-5.75).mulDR(e.getDelta()));
  };
}
