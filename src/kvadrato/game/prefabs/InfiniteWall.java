package kvadrato.game.prefabs;

import kvadrato.utils.GameException;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;

public class InfiniteWall extends Prefab
{
  public void makeEntity(Entity ent)
    throws GameException
  {
    throw new GameException();
  }
}
