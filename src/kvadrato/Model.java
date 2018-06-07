package kvadrato;
import kvadrato.game.World;
import kvadrato.game.WorldAccess;
import kvadrato.game.Vector2d;
import kvadrato.game.Entity;
import kvadrato.game.prefabs.Square;
import kvadrato.game.GameException;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.Transform;
import kvadrato.game.components.Physics;

public class Model
{
  /**
   * Świat, na którym się gra. Ten obiekt jest pusty, jeśli nie mamy świata.
   */
  private World world;
  /**
   * Zmienna, przez która model dostaje się do świata.
   */
  private WorldAccess wa;
  /**
  * Ta zmienna przechowuje, którym czymś na świecie jest gracz.
  */
  private Entity playerControlPointer;
  private Entity viewPointer;
  /**
  * Domyślny konstruktor.
  */
  public Model() throws GameException
  {
    // Tutaj nic się nie dzieje.
  }
  /**
   * Model musi być zainicjalizowany.
   */
  public void init() throws GameException
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
    System.out.println("ZAMYKANIE MODELU!!!");
    if(world==null)
      return;
    try
    {
      world.close();
    }
    catch(GameException exc)
    {
      // Ten wyjątek można całkiem zignorować, bo i tak mamy pewność, że świat
      // został zamknięty.
    }
    finally
    {
      world=null;
    }
  }
  protected void finalize()
  {
    //close();
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
      Entity player=wa.spawn("Square");
      playerControlPointer=player;
      viewPointer=player;
      Entity dwa=wa.spawn("Square");
      ((Physics)dwa.getComponent("Physics")).addPlace(new Transform(1.5,2,0));
    }
    catch(GameException exc)
    {
      System.out.println("trololololololololo");
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
      wa.setSpeed(1.0);
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
  public ViewOfWorld getWorldView()
  {
    wa=world.getAccess();
    try
    {
      return wa.getView(viewPointer,15.0);
    }
    finally
    {
      wa.drop();
    }
  }
}
