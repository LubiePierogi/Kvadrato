package kvadrato.game;

import kvadrato.utils.GameException;

public abstract class Component
{
  public abstract void fix();
  public abstract void doThings();
  public abstract void update();
  Entity ent;

  protected final Entity getEntity()
  {
    return ent;
  }
  protected final double getDelta()
  {
    return ent.world.getDeltaTime()/1000000000.;
  }
  protected final Entity spawnEnt(String name)
    throws GameException
  {
    return ent.world.spawn(name);
  }

  // Tymczaasowe
  protected final Entity getFirstEntByPrefab(String q)
    throws GameException
  {
    return ent.world.getFirstEntByPrefab(q);
  }
}
