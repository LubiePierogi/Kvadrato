package kvadrato.game;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;

/**
 * To jest klasa, która ma wątek zajmujący się wszystkim, co się dzieje na
 * świecie. Nie jest ona publiczna, ma być widoczna tylko w pakiecie,
 */
class WorldThread extends ScheduledThreadPoolExecutor
{
  private World world;

  private Future imm;
  private ScheduledFuture tim;
  /**
   * Tutaj też bym coś napisał, ale na razie nie wiem, co.
   */
  WorldThread(World w)
  {
    super(2);
    world=w;
  }

  class Immediately implements Runnable
  {
    public void run()
    {
      world.lock();
      try
      {
        while(world.decImmediatelyTicks())
        {
          world.oneTick();
        }
      }
      finally
      {
        world.unlock();
      }
    }
  }

  class Timed implements Runnable
  {
    public void run()
    {
      world.lock();
      try
      {
        world.oneTick();
      }
      finally
      {
        world.unlock();
      }
    }
  }

  void reschedule()
  {
    halt();
    imm=submit(new Immediately());
    if(world.runs())
    {
      long time=world.getWaitTime();
      tim=scheduleAtFixedRate(new Timed(),time,time,TimeUnit.NANOSECONDS);
    }
  }
  void halt()
  {
    if(imm!=null)
    {
      imm.cancel(false);
      try
      {
        imm.get();
      }
      catch(InterruptedException exc){}
      catch(ExecutionException exc){}
      catch(CancellationException exc){}
      imm=null;
    }
    if(tim!=null)
    {
      tim.cancel(false);
      try
      {
        tim.get();
      }
      catch(InterruptedException exc){}
      catch(ExecutionException exc){}
      catch(CancellationException exc){}
      tim=null;
    }
  }
}
