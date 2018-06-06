package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.Vector2d;
import kvadrato.game.Transform;

class Physics extends Component
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

  Physics()
  {
    type=Type.STATIC;
    place=new Transform();
    velocity=new Transform();
    acceleration=new Transform();

    placeNew=new Transform();
    velocityNew=new Transform();
    accelerationNew=new Transform();
  }

  Transform getPlace()
  {
    return place;
  }
  Transform getVelocity()
  {
    return velocity;
  }

  void addPlace(Transform x)
  {
    placeNew=placeNew.add(x);
  }
  void addVelocity(Transform x)
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
