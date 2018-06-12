package kvadrato.game;

import kvadrato.utils.GameException;

public interface WorldWorkerWhichReturns
{
  int[] call(WorldAccess wa) throws GameException;
}
