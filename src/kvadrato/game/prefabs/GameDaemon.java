package kvadrato.game.prefabs;

import kvadrato.utils.GameException;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;

public class GameDaemon extends Prefab
{
  public void makeEntity(Entity ent)
    throws GameException
  {
    ent.setTag("Daemon");

    ent.addComponent("GameDaemonComponent");
  }
}
