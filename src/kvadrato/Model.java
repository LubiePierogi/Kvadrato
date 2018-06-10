package kvadrato;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.World;
import kvadrato.game.Entity;
import kvadrato.game.WorldAccess;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.ControlProxy;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Control;
import kvadrato.game.components.ObstacleComponent;
import kvadrato.game.prefabs.Square;

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
  private ControlProxy cp;
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
    cp=new ControlProxy();
    world.init();
  }
  /**
   * Ta funkcja musi być wywołana przed zamknięciem programu, bo inaczej wątek
   * świata będzie blokował to zamknięcie.
   */
  public void close()
  {
    cp=null;
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
    close();
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
      ((Control)player.getComponent("Control")).setProxy(ct);
      //Entity dwa=wa.spawn("Square");
      ((Physics)player.getComponent("Physics")).addPlace
        (new Vec2dr(0.15,0.2,3.14159*0.25));
      Entity two=wa.spawn("Square");
      ((Physics)two.getComponent("Physics")).addPlace
        (new Vec2dr(-1.,-1.,0.));


      Entity enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(.5,-.5,0));
      ((ObstacleComponent)enemy.getComponent("ObstacleComponent")).setSize
        (0.2,0.1);

      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(2.5,-3.5,0));


      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(-.8,0,0));
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
    return ViewOfWorld();
  }
  public ControlProxy getControlProxy()
  {
    return cp;
  }
}
