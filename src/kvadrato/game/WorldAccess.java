package kvadrato.game;

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
    w.lock();
    world=w;
  }
  public void drop()
  {
    world.updateAll();
    world.unlock();
    world=null;
  }
  protected void finalize()
  {
    if(world!=null)
      drop();
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
    // Chyba do zmiany.
  }
  public void removeEntity(Entity ent)
    throws GameException
  {
    world.removeEntity(ent);
    // Chyba do zmiany.
  }
  public void removeAllEntities()
  {
    world.removeAllEntities();
  }
  private void updateAll()
  {
    world.updateAll();
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
  public ViewOfWorld getView(Entity where,double distance)
  {
    return new ViewOfWorld(world,where,distance);
  }
  public void doImmediatelyTicks(int x) throws GameException
  {
    world.doImmediatelyTicks(x);
  }
}
