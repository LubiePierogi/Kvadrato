package kvadrato.game.components;

import java.util.function.Function;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Component;

public class Physics extends Component
{
  private Vec2dr place;
  private Vec2dr placeNew;

  private Vec2dr velocity;
  private Vec2dr velocityNew;

  private double mass;
  private double massNew;

  private Function<Entity,Vec2dr>fn;

  public Physics()
  {
    place=new Vec2dr();
    velocity=new Vec2dr();
    acceleration=new Vec2dr();

    placeNew=new Vec2dr();
    velocityNew=new Vec2dr();
    accelerationNew=new Vec2dr();

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
    velocityNew=velocityNew.addDR(x.mul(getDelta()));
  }

  public void fix()
  {
    placeNew=place;
    velocityNew=velocity;
  }
  public void doThings()
  {
    if(fn!=null)
    accelerate(fn.apply(getEntity()));
  }
  public void update()
  {
    place=placeNew.addDR(velocity.mulDR(getDelta()));
    velocity=velocityNew;
    //System.out.print("Miejsce:\n## "+place.x+"\n## "+place.y+"\nPrędkość:\n## "+velocity.x+"\n## "+velocity.y+'\n');
  }
}
