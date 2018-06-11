package kvadrato.game.components;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

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
  private Entity player;

  private Entity anchor;

  private double distanceCovered;
  private double distAdd;

  private List<Entity> walls;

  public GameDaemonComponent()
  {
    distanceCovered=0.0;
    distAdd=0.0;
    walls=new ArrayList<Entity>();
  }

  public void begin(Entity p)
    throws GameException
  {
    end();
    anchor=spawnEnt("Anchor");
    ((Physics)anchor.getComponent("Physics")).addVelocity(new Vec2dr(1.,0.,0.));
    player=p;
    if(p!=null&&p.hasComponent("Physics"))
    {
      ((Physics)p.getComponent("Physics")).attach(anchor);
    }
    {
      Entity w;
      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(-2.,0.,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(1./3.,3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.MOVING);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(2.,0.,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(1./3.,3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.MOVING);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(0.,1.5,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(8.,1./3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.INFINITE);

      w=spawnEnt("Wall");
      ((Physics)w.getComponent("Physics")).addPlace(new Vec2dr(0.,-1.5,0.));
      ((Physics)w.getComponent("Physics")).attach(anchor);
      ((WallComponent)w.getComponent("WallComponent")).
        setSize(new Vec2d(8.,1./3.));
      ((WallComponent)w.getComponent("WallComponent")).
        setColor(WallColor.INFINITE);
    }
    distanceCovered=0.0;
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
    anchor=null;
  }

  public void fix()
  {
    distAdd=0.0;
  }
  public void doThings()
  {
    if(anchor!=null)
    {
      ((Physics)anchor.getComponent("Physics")).
        accelerate(new Vec2dr(.5/60.,0.,0.));
      distAdd=
      (
        ((Physics)anchor.getComponent("Physics")).getVelocity().dist()
      )
      ;
    }
  }
  public void update()
  {
    if(anchor!=null)
      distanceCovered=distanceCovered+distAdd;
  }
}
