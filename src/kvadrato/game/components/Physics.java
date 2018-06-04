package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.Vector2d;

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
    type=STATIC;
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
  Transform getAcceleration()
  {
    return acceleration;
  }

  void addPlace(Transform x)
  {
    placeNew=placeNew.add(x);
  }
  void addVelocity(Transform x)
  {
    velocityNew=velocityNew.add(x);
  }
  void addAcceleration(Transform x)
  {
    accelerationNew=accelerationNew.add(x);
  }

  public void fix()
  {
    placeNew=place;
    velocityNew=velocity;
    accelerationNew=0.0;//acceleration;
  }
  public void doThings(){}
  public void update()
  {
    place=placeNew
  }
}
