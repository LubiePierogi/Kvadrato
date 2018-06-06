package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.Vector2d;
import kvadrato.game.Transform;

public class Physics extends Component
{
  public enum Type
  {
    STATIC,
    HALF,
    DYNAMIC,
  }

  private Type type;

  private Transform place;
  private Transform velocity;
  private Transform acceleration;


  private Transform placeNew;
  private Transform velocityNew;
  private Transform accelerationNew;

  private double mass;

  public Physics()
  {
    type=Type.STATIC;
    place=new Transform();
    velocity=new Transform();
    acceleration=new Transform();

    placeNew=new Transform();
    velocityNew=new Transform();
    accelerationNew=new Transform();

    mass=1.0;
  }

  public Type getType()
  {
    return type;
  }

  public void setType(Type t)
  {
    type=t;
  }

  public double getMass()
  {
    return mass;
  }

  public void setMass(double m)
  {
    mass=m;
  }

  public Transform getPlace()
  {
    return place;
  }
  public Transform getVelocity()
  {
    return velocity;
  }

  public void addPlace(Transform x)
  {
    placeNew=placeNew.add(x);
  }
  public void addVelocity(Transform x)
  {
    velocityNew=velocityNew.add(x);
  }

  public void fix()
  {
    placeNew=place;
    velocityNew=velocity;
  }
  public void doThings(){}
  public void update()
  {
    if(type!=Type.STATIC)
    {
      place=placeNew.add(velocity);
      velocity=velocityNew;
    }
  }
}
