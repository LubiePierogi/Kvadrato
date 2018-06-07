package kvadrato;
import kvadrato.game.World;
import kvadrato.game.WorldAccess;
import kvadrato.game.Vector2d;
import kvadrato.game.Entity;
import kvadrato.game.prefabs.Square;
import kvadrato.game.GameException;

public class Model
{
  /**
   * Świat, na którym się gra. Ten obiekt jest pusty, jeśli nie mamy świata.
   */
  World world;
  /**
   * Zmienna, przez która model dostaje się do świata.
   */
  WorldAccess wa;
  /**
  * Ta zmienna przechowuje, którym czymś na świecie jest gracz.
  */
  Entity playerControlPointer;
  Entity viewPointer;
  /**
  * Domyślny konstruktor.
  */
  public Model() throws GameException
  {
    world=new World();
    world.init();
  }
  /**
   * Ta funkcja musi być wywołana przed zamknięciem programu, bo inaczej wątek
   * świata będzie blokował to zamknięcie.
   */
  public void close()
  {
    try
    {
      world.close();
    }
    catch(GameException exc)
    {
      // Ten wyjątek można całkiem zignorować, bo i tak mamy pewność, że świat
      // został zamknięty.
    }
  }
  public void clearTheWorld()
  {
    wa=world.getAccess();
    try
    {
      wa.removeAllEntities();
    }
    finally
    {
      wa.drop();
    }
  }
  public void cookLevelStart()
  {
    wa=world.getAccess();
    try
    {
      Entity gracz=wa.spawn("Square");
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
      wa.drop();
    }
  }
  public void pushWorld() throws GameException
  {
    wa=world.getAccess();
    try
    {
      System.out.println("No i jeszcze tu");
      wa.setSpeed(1.0);
      System.out.println("A tutaj jusz pewnie nie");
    }
    finally
    {
      wa.drop();
    }
  }
  public void haltWorld() throws GameException
  {
    wa=world.getAccess();
    try
    {
      wa.setSpeed(0.0);
    }
    finally
    {
      wa.drop();
    }
  }
}
