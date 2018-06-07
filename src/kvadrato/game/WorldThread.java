package kvadrato.game;


/**
 * To jest klasa, która ma wątek zajmujący się wszystkim, co się dzieje na
 * świecie. Nie jest ona publiczna, ma być widoczna tylko w pakiecie,
 */
class WorldThread extends Thread
{
  private World world;
  /**
   * Tutaj też bym coś napisał, ale na razie nie wiem, co.
   */
  WorldThread(World w)
  {
    world=w;
  }
  /**
   * Zwykła wątkowa funkcja, która ma być wywołana, jak wątek ma działać.
   */
  public void run()// throws InterruptedException
  {
    long remainingTime;
    world.lock();
    try
    {
      while(true)
      {
        while(world.decImmediatelyTicks())
        {
          world.oneTick();
        }
        if(world.runs())
        {
          // Wiemy, że świat nie jest zatrzymany, więc czekamy na przerwanie
          // albo na wyznaczony czas.
          remainingTime=world.awaitNanos(world.getWaitTime());
        }
        else
        {
          // Świat jest zatrzymany, więc czekamy na coś w nieskończonośc.
          world.await();

          // Czekaliśmy w nieskończoność, więc na pewno było przerwane, więc
          // ustawiamy tak, że było przerwane.
          remainingTime=1;
        }
        if(world.getEnder()) // Nieważne, czy przerwane, czy nie, jeśli ma być
                             // skończone, to bezwzględnie kończymy.
        {
          return;
          // Wtedy join już się zrobi.
          // Wiem, że ten komentarz nie jest potrzebny, ale mogę później nie
          // pamiętać, o co tu chodzi.
        }
        if(remainingTime>0) // przerwane
        {
          // Dodatkowo, po przerwaniu czekanie ustawia się jeszcze raz, więc
          // wtedy świat jest tak naprawdę spowolniony, ale to nie
          // przeszkadza. Przynajmniej na razie.
          continue;
        }
        else // minął czas
        {
          world.oneTick();
          continue;
        }
      }
    }
    catch(InterruptedException exc)
    {
      // Nie mam pojęcia, co tu zrobić.
    }
    finally
    {
      world.unlock();
    }
  }
}
