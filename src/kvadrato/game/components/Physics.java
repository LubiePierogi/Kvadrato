package kvadrato.game.components;

import java.util.function.Function;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.World;
import kvadrato.game.Entity;
import kvadrato.game.Component;

//////
// Szczegóły tego anchor i tak dalej:
//  Na razie to działa tylko z prędkością, teleporty nic nie robią, bo nie jest
//  to potrzebne.
//////

public class Physics extends Component
{
  private Vec2dr place;
  private Vec2dr placeNew;

  private Vec2dr velocity;
  private Vec2dr velocityNew;

  private double mass;
  private double massNew;

  private Entity anchor;
  private Entity anchorNew;

  private Function<Entity,Vec2dr>fn;

  public Physics()
  {
    place=new Vec2dr();
    placeNew=new Vec2dr();

    velocity=new Vec2dr();
    velocityNew=new Vec2dr();

    mass=1.;
    massNew=1.;
  }

  public void setFn(Function<Entity,Vec2dr>func)
  {
    fn=func;
  }

  public double getMass()
  {
    return mass;
  }

  public void setMass(double m)
  {
    massNew=m;
  }

  public Vec2dr getPlace()
  {
    return place;
  }
  public Vec2dr getVelocity()
  {
    return velocity;
  }

  public void addPlace(Vec2dr x)
  {
    placeNew=placeNew.addDR(x);
  }
  public void addVelocity(Vec2dr x)
  {
    velocityNew=velocityNew.addDR(x);
  }
  public void accelerate(Vec2dr x)
  {
    velocityNew=velocityNew.addDR(x.mulDR(getDelta()));
  }
  public void attach(Entity e)
  {
    anchorNew=e;
  }
  public void detach()
  {
    anchorNew=null;
  }
  public Entity getAnchor()
  {
    return anchor;
  }

  public void fix()
  {
    placeNew=place;
    velocityNew=velocity;
    if(anchor!=null)
    {
      World w=getEntity().getWorld();
      if(w!=anchor.getWorld())
        anchor=null;
      if(w!=anchorNew.getWorld())
        anchorNew=null;
    }
  }
  public void doThings()
  {
    if(fn!=null)
      accelerate(fn.apply(getEntity()));

    placeNew=placeNew.addDR(velocity.mulDR(getDelta()));
    velocityNew=velocityNew;

    if(anchor!=null)
    {
      Physics ph=((Physics)anchor.getComponent("Physics"));
      Vec2dr w=ph.getPlace();
      Vec2dr q=ph.getVelocity().mulDR(getDelta());
      Vec2d e=new Vec2d(w);
      Vec2d diff=(new Vec2d(placeNew)).subD(e);
      diff=diff.rotateD(q.angle);
      placeNew=new Vec2dr((e.addD(diff)),placeNew.angle);
      placeNew=placeNew.addDR(q);
      velocityNew=velocityNew.rotateD(q.angle);
    }
  }
  public void update()
  {
    place=placeNew;
    velocity=velocityNew;
    anchor=anchorNew;
    //System.out.print("Miejsce:\n## "+place.x+"\n## "+place.y+"\nPrędkość:\n## "+velocity.x+"\n## "+velocity.y+'\n');
  }
}
