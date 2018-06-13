package kvadrato;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.World;
import kvadrato.game.Entity;
import kvadrato.game.WorldAccess;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.ControlProxy;
import kvadrato.game.EventProxy;
import kvadrato.game.other.BgColor;
import kvadrato.game.other.WallColor;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Control;
import kvadrato.game.components.EventSender;
import kvadrato.game.components.ObstacleComponent;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.components.WallComponent;
import kvadrato.game.components.GameDaemonComponent;
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
  private int viewEntId;
  /**
   * Obiekt ze sterowaniem.
   */
  private ControlProxy cp;
  private EventProxy ep;
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
    ep=new EventProxy();
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
    throws GameException
  {
    world.doWork(wa->wa.clear());
  }
  public void cookLevelStart()
    throws GameException
  {
    viewEntId=world.doWorkAndReturn(wa->
    {
      wa.clear();

      Entity player=wa.spawn("Square");
      ((Control)player.getComponent("Control")).setProxy(cp);
      ((EventSender)player.getComponent("EventSender")).setProxy(ep);

      Entity daemon=wa.spawn("GameDaemon");
      ((GameDaemonComponent)daemon.getComponent("GameDaemonComponent")).
        begin(player,0);

      wa.updateWorld();

      return new int[]{player.getId()};
    })[0];
  }
  public void cookTestLevel()
    throws GameException
  {
    viewEntId=world.doWorkAndReturn(wa->
    {
      wa.clear();

      Entity player=wa.spawn("Square");
      ((Control)player.getComponent("Control")).setProxy(cp);
      ((EventSender)player.getComponent("EventSender")).setProxy(ep);
      //Entity dwa=wa.spawn("Square");
      ((Physics)player.getComponent("Physics")).addPlace
        (new Vec2dr(0,0,0));
      ((Physics)player.getComponent("Physics")).addVelocity
        (new Vec2dr(0,0,0));


      Entity enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(3.5,-3.5,0));
      ((ObstacleComponent)enemy.getComponent("ObstacleComponent")).setSize
        (0.2,0.1);

      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(6.5,-6.5,0));
      ((BgColorComponent)enemy.getComponent("BgColorComponent")).setColor
        (BgColor.RED);


      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(-9.8,0,0));


      Entity wall=wa.spawn("Wall");
      ((Physics)wall.getComponent("Physics")).addPlace
        (new Vec2dr(0,1,0));
      ((WallComponent)wall.getComponent("WallComponent")).setColor
        (WallColor.INFINITE);
      ((WallComponent)wall.getComponent("WallComponent")).setSize
        (new Vec2d(5,1./3.));


      Entity xz=wa.spawn("Obstacle");
      ((Physics)xz.getComponent("Physics")).addPlace
        (new Vec2dr(0.,4.5,Math.PI*.25));
      ((ObstacleComponent)xz.getComponent("ObstacleComponent")).setSize
        (5.,0.1);


      wa.updateWorld();

      return new int[]{player.getId()};
    })[0];
  }
  public void pushWorld()
    throws GameException
  {
    world.doWork(wa->wa.setSpeed(1.0));
  }
  public void haltWorld()
    throws GameException
  {
    world.doWork(wa->wa.setSpeed(0.0));
  }
  public ViewOfWorld getWorldView()
    throws GameException
  {
    return world.getView(viewEntId,5000.0);
  }
  public ControlProxy getControlProxy()
  {
    return cp;
  }
  public EventProxy getEventProxy()
  {
    return ep;
  }
}
