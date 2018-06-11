package kvadrato;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.World;
import kvadrato.game.Entity;
import kvadrato.game.WorldAccess;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.ControlProxy;
import kvadrato.game.other.BgColor;
import kvadrato.game.other.WallColor;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Control;
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
      ((Control)player.getComponent("Control")).setProxy(cp);
      //Entity dwa=wa.spawn("Square");
      ((Physics)player.getComponent("Physics")).addPlace
        (new Vec2dr(0,0,0));
      ((Physics)player.getComponent("Physics")).addVelocity
        (new Vec2dr(0,0,-1.));

      Entity two=wa.spawn("Square");
      ((Physics)two.getComponent("Physics")).addPlace
        (new Vec2dr(-.5,-.5,0.));


      Entity enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(.5,.5,0));
      ((ObstacleComponent)enemy.getComponent("ObstacleComponent")).setSize
        (0.2,0.1);

      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(.5,-.5,0));
      ((BgColorComponent)enemy.getComponent("BgColorComponent")).setColor
        (BgColor.RED);


      enemy=wa.spawn("Obstacle");
      ((Physics)enemy.getComponent("Physics")).addPlace
        (new Vec2dr(-.8,0,0));


      Entity wall=wa.spawn("Wall");
      ((Physics)wall.getComponent("Physics")).addPlace
        (new Vec2dr(0,1,0));
      ((WallComponent)wall.getComponent("WallComponent")).setColor
        (WallColor.INFINITE);
      ((WallComponent)wall.getComponent("WallComponent")).setSize
        (new Vec2d(5,1./3.));


        wall=wa.spawn("Wall");
        ((Physics)wall.getComponent("Physics")).addPlace
          (new Vec2dr(3,0,0));
        ((WallComponent)wall.getComponent("WallComponent")).setColor
          (WallColor.MOVING);
        ((WallComponent)wall.getComponent("WallComponent")).setSize
          (new Vec2d(1./3.,4));

        for(int i=0;i<10;++i)
        {
          Entity zx=wa.spawn("Obstacle");
          ((Physics)zx.getComponent("Physics")).addPlace
            (new Vec2dr(0.,0.+.12*i,.08*i));
          ((ObstacleComponent)zx.getComponent("ObstacleComponent")).setSize
            (0.3,0.1);
        }

        Entity daemon=wa.spawn("GameDaemon");
        ((GameDaemonComponent)daemon.getComponent("GameDaemonComponent")).
          begin(player);

//        Entity anchor=wa.spawn("Anchor");

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
      return wa.getView(viewPointer,2.4);
    }
    catch(GameException exc)
    {
      return null;
    }
    finally
    {
      wa.drop();
    }
  }
  public ControlProxy getControlProxy()
  {
    return cp;
  }
}
