package kvadrato.game.components;

import java.util.Objects;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Component;
import kvadrato.game.components.Physics;
import kvadrato.game.other.BgColor;

public class GameDaemonComponent extends Component
{
  private Entity player;

  private Entity anchor;

  private double distanceCovered;
  private double distAdd;

  public GameDaemonComponent()
  {
    distanceCovered=0.0;
    distAdd=0.0;
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
    distanceCovered=0.0;
  }
  public void end()
  {
    if(anchor!=null)
      anchor.remove();
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
