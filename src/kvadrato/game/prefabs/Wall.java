package kvadrato.game.prefabs;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Collider;
import kvadrato.game.components.WallComponent;
import kvadrato.game.other.WallColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.WallAe;
import kvadrato.game.collision.ElementaryShape;

public class Wall extends Prefab
{
  public void makeEntity(Entity ent)
    throws GameException
  {
    ent.addComponent("Physics");
    ent.addComponent("Appearance");
    ent.addComponent("Collider");
    ent.addComponent("WallComponent");

    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setMass(1./0.); // Ściana będzie nie do przesunięcia.
    }

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setFn(appearanceFn);
      q.setRenderDistanceFn(renderDistanceFn);
    }

    // Collider
    {
      Collider q=(Collider)ent.getComponent("Collider");
      q.setShapeFn(shapeFn);
    }
  }
  public final static Function<Entity,List<AppearanceElement>>appearanceFn=ent->
  {
    ArrayList<AppearanceElement> list=new ArrayList<AppearanceElement>();
    WallComponent w=(WallComponent)ent.getComponent("WallComponent");
    Vec2d size=w.getSize();
    WallAe ae=new WallAe();
    ae.x=0.0;
    ae.y=0.0;
    ae.angle=0.0;
    ae.scale=1.0;
    ae.width=size.x;
    ae.height=size.y;
    ae.color=w.getColor();
    list.add(ae);
    return list;
  };
  private final static Function<Entity,Double>renderDistanceFn=ent->
  {
    return ((WallComponent)ent.getComponent("WallComponent")).getSize().dist();
  };
  private final static Function<Entity,ElementaryShape>shapeFn=ent->
  {
    WallComponent w=(WallComponent)ent.getComponent("WallComponent");
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
      "w ścianie!");
    }
    return null;
  };
}
