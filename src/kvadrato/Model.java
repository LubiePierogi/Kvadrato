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
import kvadrato.game.components.Control;
import kvadrato.game.ControlThing;

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
   * Obiekt ze sterowaniem.
   */
  private ControlThing ct;
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
    ct=new ControlThing();
    world.init();
  }
  /**
   * Ta funkcja musi być wywołana przed zamknięciem programu, bo inaczej wątek
   * świata będzie blokował to zamknięcie.
   */
  public void close()
  {
    //System.out.println("ZAMYKANIE MODELU!!!");
    ct=null;
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
      ((Control)player.getComponent("Control")).setThing(ct);
      //Entity dwa=wa.spawn("Square");
      ((Physics)player.getComponent("Physics")).addPlace(new Transform(0.15,0.2,3.14159*0.25));
      Entity dwa=wa.spawn("Square");
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
  public ControlThing getControlThing()
  {
    return ct;
  }
}
