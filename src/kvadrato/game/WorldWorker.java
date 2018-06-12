package kvadrato.game;

import kvadrato.utils.GameException;

public interface WorldWorker
{
  void call(WorldAccess wa) throws GameException;
}
