package kvadrato.game;

import kvadrato.utils.GameException;

public interface WorldWorkerWhichReturns
{
  Object call(WorldAccess wa) throws GameException;
}
