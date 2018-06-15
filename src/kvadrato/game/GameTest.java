package kvadrato.game;

import org.junit.Assert;
import org.junit.Test;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Health;

public class GameTest
{
  @Test
  public void spawnThroughWATest()
  {
    World world=new World();
    Object o=false;
    try
    {
      world.init();
      world.doWork(wa->
      {
        wa.spawn("Wall");
        wa.updateWorld();
      });
      o=world.doWorkAndReturn(wa->
      {
        Entity entity=wa.getEntById(1);
        if(entity==null)
          return false;
        if(entity.getName().equals("Wall"))
          return true;
        return false;
      });
    }
    catch(GameException exc)
    {
      Assert.fail("GameException:\n"+exc.getMessage());
    }
    Assert.assertTrue((boolean)o);
  }
  @Test
  public void movingEntity()
  {
    World world=new World();
    Object o=false;
    try
    {
      world.init();
      o=world.doWorkAndReturn(wa->
      {
        Entity e=wa.spawn("Square");
        if(e==null)
          return false;
        Physics ph=(Physics)e.getComponent("Physics");
        ph.addPlace(new Vec2dr(3.,8.,8.));
        wa.updateWorld();
        Vec2dr v=ph.getPlace();
        if
        (
          Math.abs(v.x-3.0)<0.001 &&
          Math.abs(v.y-8.0)<0.001 &&
          Math.abs((v.angle-8.0)%(2.*(Math.PI)))<0.001
        )
          return true;
        return false;
      });
    }
    catch(GameException exc)
    {
      Assert.fail("GameException:\n"+exc.getMessage());
    }
    Assert.assertTrue((boolean)o);
  }
  @Test
  public void hurtingSquare()
  {
    World world=new World();
    Object o=0./0.;
    try
    {
      world.init();
      o=world.doWorkAndReturn(wa->
      {
        Entity e=wa.spawn("Square");
        if(e==null)
          return false;
        Health h=(Health)e.getComponent("Health");
        h.change(-50);
        wa.updateWorld();
        return h.getCurrent();
      });
    }
    catch(GameException exc)
    {
      Assert.fail("GameException:\n"+exc.getMessage());
    }
    Assert.assertEquals("Miało zabrać 50 życia ze 100.",100.0,(double)o,0.0001);
  }
}
