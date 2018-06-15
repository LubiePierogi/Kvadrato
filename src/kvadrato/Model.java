package kvadrato;

import java.util.Random;

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
  private int playerId;
  private int daemonId;
  /**
   * Obiekt ze sterowaniem.
   */
  private ControlProxy cp;
  private EventProxy ep;

  private Random rng;

  int hiScore;

  public Model() throws GameException
  {
    rng=new Random();
    hiScore=0;
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
   * świata będzie mógł blokować to zamknięcie.
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
    Object o=world.doWorkAndReturn(wa->
    {
      wa.clear();

      Entity player=wa.spawn("Square");
      ((Control)player.getComponent("Control")).setProxy(cp);
      ((EventSender)player.getComponent("EventSender")).setProxy(ep);

      Entity daemon=wa.spawn("GameDaemon");
      ((GameDaemonComponent)daemon.getComponent("GameDaemonComponent")).
        begin(player,rng.nextLong());

      wa.updateWorld();

      return new int[]{player.getId(),daemon.getId()};
    });
    playerId=((int[])o)[0];
    daemonId=((int[])o)[1];
  }
  public void cookTestLevel()
    throws GameException
  {
    Object o=world.doWorkAndReturn(wa->
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
        (new Vec2dr(0,-4,0));
      ((WallComponent)wall.getComponent("WallComponent")).setColor
        (WallColor.INFINITE);
      ((WallComponent)wall.getComponent("WallComponent")).setSize
        (new Vec2d(5,1./3.));


      wall=wa.spawn("Wall");
      ((Physics)wall.getComponent("Physics")).addPlace
        (new Vec2dr(2,0,0));
      ((WallComponent)wall.getComponent("WallComponent")).setColor
        (WallColor.INFINITE);
      ((WallComponent)wall.getComponent("WallComponent")).setSize
        (new Vec2d(1./3.,6));

      Entity xz=wa.spawn("Obstacle");
      ((Physics)xz.getComponent("Physics")).addPlace
        (new Vec2dr(0.,4.5,Math.PI*.25));
      ((ObstacleComponent)xz.getComponent("ObstacleComponent")).setSize
        (5.,0.1);


      wa.updateWorld();

      return player.getId();
    });
    playerId=(int)o;
  }
  public int getScore()
    throws GameException
  {
    int score;
    Object o=world.doWorkAndReturn(wa->
    {
      Entity ent=wa.getEntById(daemonId);
      if(ent==null)return -1;
      GameDaemonComponent gdc
        =(GameDaemonComponent)ent.getComponent("GameDaemonComponent");
      return gdc.getScore();
    });
    if((int)o>hiScore)hiScore=(int)o;
    return (int)o;
  }
  public int getHiScore()
  {
    return hiScore;
  }
  public void clearHiScore()
  {
    hiScore=0;
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
  public ViewOfWorld getWorldView(double dist)
    throws GameException
  {
    return world.getView(playerId,dist);
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
