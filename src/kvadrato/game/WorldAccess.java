package kvadrato.game;

import java.util.function.Consumer;

import kvadrato.utils.GameException;
import kvadrato.game.World;

/**
 * Klasa pośrednicząca w dostępie do świata. Uzyskanie jej obiektu ze świata
 * sprawia, że jest w nim zablokowany muteks i wszystko inne, co ma do niego
 * dostęp, jest zablokowane.
 */
public class WorldAccess
{
  World world;
  WorldAccess(World w)
  {
    world=w;
  }

  public void setTickrate(int tr)
   throws GameException
  {
    world.setTickrate(tr);
  }
  public void setSpeed(double s)
   throws GameException
  {
    world.setSpeed(s);
  }
  public Entity spawn(String name)
    throws GameException
  {
    return world.spawn(name);
  }
  public void removeEntity(Entity ent)
    throws GameException
  {
    world.removeEntity(ent);
  }
  public Entity getEntById(int q)
  {
    return world.getEntById(q);
  }
  public void clear()
  {
    world.clear();
  }
  public void updateWorld()
  {
    world.updateWorld();
  }
  public int getDeltaTime()
  {
    return world.getDeltaTime();
  }
  public int getWaitTime()
  {
    return world.getWaitTime();
  }
  public boolean runs()
  {
    return world.runs();
  }
  public void doImmediatelyTicks(int x) throws GameException
  {
    world.doImmediatelyTicks(x);
  }
}
