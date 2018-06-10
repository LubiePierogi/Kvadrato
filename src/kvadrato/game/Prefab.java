package kvadrato.game;

import kvadrato.utils.GameException;

public abstract class Prefab
{
  public abstract void makeEntity(Entity ent)
    throws GameException
  ;
}
