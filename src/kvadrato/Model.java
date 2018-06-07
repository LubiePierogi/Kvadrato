package kvadrato;
import kvadrato.game.World;
import kvadrato.game.Vector2d;
import kvadrato.game.Entity;
import kvadrato.game.prefabs.Square;
import kvadrato.game.GameException;

public class Model
{
  //View view; // I tak bdzie tylko jeden.
  /**
   * Świat, na którym się gra. Ten obiekt jest pusty, jeśli nie mamy świata.
   */
  World world;
  /**
  * Ta zmienna przechowuje, którym czymś na świecie jest gracz.
  */
  Entity playerControlPointer;
  Entity viewPointer;
  /**
  * Domyślny konstruktor.
  */
  public Model()
  {
    world=new World();
  }
  public void clearTheWorld()
  {
    world.lock();
    try
    {
      world.removeAllEntities();
    }
    finally
    {
      world.unlock();
    }
  }
  public void cookLevelStart()
  {
    world.lock();
    try
    {
      Entity gracz=world.spawn("Square");
      playerControlPointer=gracz;
      viewPointer=gracz;
      System.out.println("Tu tesz");
    }
    catch(ClassNotFoundException exc)
    {

    }
    catch(InstantiationException exc)
    {

    }
    catch(IllegalAccessException exc)
    {

    }
    finally
    {
      world.unlock();
    }
  }
  public void pushWorld() throws GameException
  {
    world.lock();
    try
    {
      System.out.println("No i jeszcze tu");
      world.setSpeed(1.0);
      System.out.println("A tutaj jusz pewnie nie");
    }
    finally
    {
      world.unlock();
    }
  }
  public void haltWorld() throws GameException
  {
    world.lock();
    try
    {
      world.setSpeed(0.0);
    }
    finally
    {
      world.unlock();
    }
  }
}
