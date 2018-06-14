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

  public void begin(Entity p,long s)
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
      ((Physics)p.getComponent("Physics")).attach(anchor);
    spawnWalls();
    distanceCovered=0.0;
    distanceCoveredNew=0.0;
  }
  private void spawnWalls()
    throws GameException
  {
    Vec2dr[] places=new Vec2dr[]
    {
      new Vec2dr(-4., 0. ,0.),new Vec2dr( 4., 0. ,0.),
      new Vec2dr( 0., 2.7,0.),new Vec2dr( 0.,-2.7,0.)
    };
    Vec2d[] sizes=new Vec2d[]
    {
      new Vec2d(1./3.,5.4  ),new Vec2d(1./3.,5.4  ),
      new Vec2d(16.  ,1./3.),new Vec2d(16.  ,1./3.)
    };
    WallColor[] colors=new WallColor[]
      {WallColor.MOVING,WallColor.MOVING,WallColor.INFINITE,WallColor.INFINITE};
    for(int i=0;i<4;++i)
    {
      Entity w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(places[i]);
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).setSize(sizes[i]);
      ((WallComponent)w.getComponent("WallComponent")).setColor(colors[i]);
      walls.add(w);
    }
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
  public int getScore()
  {
    return (int)Math.floor(distanceCovered*30.);
  }

  public void fix()
  {
    if(player.getWorld()!=getWorld())
      player=null;
    for(int i=0;i<walls.size();++i)
      if(walls.get(i).getWorld()!=getWorld())
      {
        walls.remove(i);
        --i;
      }
    for(int i=0;i<obstacles.size();++i)
      if(obstacles.get(i).getWorld()!=getWorld())
      {
        obstacles.remove(i);
        --i;
      }
  }
  public void doThings()
    throws GameException
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
    throws GameException
  {
    if(rng.nextFloat()<1.f/2.f)
    {
      ++spawnedObstacles;
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
  private void destroyOverpastObstacles()
  {
    for(int i=0;i<obstacles.size();++i)
    {
      Vec2dr anchorPlace=((Physics)anchor.getComponent("Physics")).getPlace();
      Vec2dr obstaclePlace=((Physics)obstacles.get(i)
        .getComponent("Physics")).getPlace();
      double dist=anchorPlace.subDR(obstaclePlace).dist();
      if(dist>20.)
      {
        obstacles.get(i).remove();
        obstacles.remove(i);
        --i;
        continue;
      }
    }
  }
}
