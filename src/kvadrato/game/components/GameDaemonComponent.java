package kvadrato.game.components;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Component;
import kvadrato.game.components.Physics;
import kvadrato.game.other.BgColor;
import kvadrato.game.other.WallColor;

public class GameDaemonComponent extends Component
{
  private Random rng;

  private Entity player;

  private Entity anchor;

  private double distanceCovered;
  private double distanceCoveredNew;

  private int lastSpawn;
  private int spawnedObstacles;

  private double goalVelocity;
  private double goalVelocityNew;

  private List<Entity> walls;
  private List<Entity> obstacles;

  public GameDaemonComponent()
  {
    distanceCovered=0.0;
    distanceCoveredNew=0.0;
    goalVelocity=0.0;
    goalVelocityNew=0.0;
    lastSpawn=0;
    spawnedObstacles=0;
    walls=new ArrayList<Entity>();
    obstacles=new ArrayList<Entity>();
    rng=new Random(0l);
  }

  public void begin(Entity p,int s)
    throws GameException
  {
    end();
    rng.setSeed(s);
    lastSpawn=0;
    spawnedObstacles=0;
    goalVelocity=1.5;
    goalVelocityNew=1.5;
    anchor=spawnEnt("Anchor");
    Physics ph=(Physics)anchor.getComponent("Physics");
    ph.addVelocity(new Vec2dr(goalVelocity,0.,0.));
    player=p;
    if(p!=null&&p.hasComponent("Physics"))
    {
      ((Physics)p.getComponent("Physics")).attach(anchor);
    }
    {
      Entity w;
      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(-4.,0.,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(1./3.,5.4));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.MOVING);
      walls.add(w);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(4.,0.,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(1./3.,5.4));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.MOVING);
      walls.add(w);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(0.,2.7,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(8.,1./3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.INFINITE);
      walls.add(w);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(0.,-2.7,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(8.,1./3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.INFINITE);
      walls.add(w);
    }
    distanceCovered=0.0;
    distanceCoveredNew=0.0;
  }
  public void end()
  {
    if(anchor!=null)
      anchor.remove();
    while(!walls.isEmpty())
    {
      walls.get(walls.size()-1).remove();
      walls.remove(walls.size()-1);
    }
    while(!obstacles.isEmpty())
    {
      obstacles.get(obstacles.size()-1).remove();
      obstacles.remove(obstacles.size()-1);
    }
    anchor=null;
  }

  public void fix()
  {
    //for

    //Trzeba dopisać usuwanie pustych wskaźników na przeszkody, ściany i gracza.
  }
  public void doThings()
  {
    if(anchor!=null)
    {
      Physics ph=(Physics)anchor.getComponent("Physics");
      goalVelocityNew=goalVelocity+getDelta()*(1./120.+goalVelocity*1./360.);
      Vec2dr diff=new Vec2dr(goalVelocity-(ph.getVelocity().x),0,0);
      ph.addVelocity(diff);
      distanceCoveredNew=ph.getPlace().x;

      destroyOverpastObstacles();

      while(lastSpawn<(int)distanceCovered)
      {
      //  System.out.println("Metr: "+lastSpawn);
        spawnObstacles(lastSpawn);
        ++lastSpawn;
      }
    }
  }
  public void update()
  {
    if(anchor!=null)
    {
      distanceCovered=distanceCoveredNew;
      goalVelocity=goalVelocityNew;
    }
  }
  private void spawnObstacles(int meter)
  {
    try
    {
      if(rng.nextFloat()<1.f/2.f)
      {
        ++spawnedObstacles;
    //    System.out.println("##: "+spawnedObstacles);
        int count=(int)(1.f+2.f*rng.nextFloat());
        while(count>0)
        {
          Entity q=spawnEnt("Obstacle");
          obstacles.add(q);
          ObstacleComponent
            oc=(ObstacleComponent)q.getComponent("ObstacleComponent");
          oc.setSize
          (
            .1+.3*rng.nextDouble(),
            .5+4.*rng.nextDouble()
          );
          BgColorComponent
            bcc=(BgColorComponent)q.getComponent("BgColorComponent");
          int whichColor=rng.nextInt(BgColor.values().length);
          bcc.setColor(BgColor.values()[whichColor]);
          Physics ph=(Physics)q.getComponent("Physics");
          Vec2dr spawnPlace=
          new Vec2dr
          (
              meter+
              7.+
              1.5*rng.nextDouble()
            ,
              -3.+
              6.*rng.nextDouble()
            ,
              Math.PI*rng.nextDouble()
          );
          ph.addPlace(spawnPlace);
          --count;
        }
      }
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.out);
    }
  }
  private void destroyOverpastObstacles()
  {
    //if(0==0)return;
    for(int i=0;i<obstacles.size();++i)
    {
      Vec2dr anchorPlace=((Physics)anchor.getComponent("Physics")).getPlace();
      Vec2dr obstaclePlace=((Physics)obstacles.get(i)
        .getComponent("Physics")).getPlace();
      //System.out.println("Anchor: "+anchor.getId()+' '+anchor.getName()+' '+
    //    anchorPlace.x+' '+anchorPlace.y);
    //  System.out.println("Obstacle: "+obstacles.get(i).getId()+' '+
    //    obstacles.get(i).getName()+' '+obstaclePlace.x+' '+obstaclePlace.y);
      double dist=anchorPlace.subDR(obstaclePlace).dist();
  //    System.out.println("Różnica: "+dist);
      if(dist>20.)
      {
      //  System.out.println("Usuwanie");
        obstacles.get(i).remove();
        obstacles.remove(i);
        --i;
        continue;
      }
    }
  }
}
