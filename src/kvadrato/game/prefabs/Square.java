package kvadrato.game.prefabs;

import kvadrato.game.Prefab;
import kvadrato.game.Entity;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.other.BackgroundColor;
/**
 * Klasa kwadratu, którym steruje gracz.
 */
public class Square extends Prefab
{
  public void makeEntity(Entity ent)
    throws ClassNotFoundException,InstantiationException,IllegalAccessException
  {
    ent.addComponent("Physics");
    ent.addComponent("SquareComponent");
    ent.addComponent("Camera");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");


    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      SquareSquare sqsq=new SquareSquare();
      sqsq.colorOfSquare=BackgroundColor.WHITE;
      q.addElement(new SquareSquare());
    }

    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setType(Physics.Type.DYNAMIC);
      q.setMass(1.0);
    }
    // Camera
    {
      // W kamerze nic nie zmieniamy.
    }
    // Control
    {
      // Tu na razie też nic.
    }
  }

}
/*
  extends Entity
  implements
    EntityWithPlace,ControllableEntity,EntityWithView,
    DynamicEntity,EntityWithCollisions
{
  /*
  public void doThings()
  {

  }
  public Square()
  {
    state=new State();
    ((State)state).place=new Vector2d();
    ((State)state).velocity=new Vector2d();
    ((State)state).angle=0.0;
    ((State)state).colorOfBackground=Color.WHITE;

    stateNew=null;
  }
  public Vector2d getPlace()
  {
    return ((State)state).place;
  }
  public double getAngle()
  {
    return ((State)state).angle;
  }
  public Vector2d getViewPlace()
  {
    if(((State)state).cord!=null)
    {
      return ((State)state).cord.getPlace();
    }
    return getPlace();
  }
  public double getViewAngle()
  {
    if(((State)state).cord!=null)
    {
      return ((State)state).cord.getAngle();
    }
    return getAngle();
  }
  public Vector<Collision> getCollisions()
  {
    return null;
  }
  public void addCollision(EntityWithCollisions ent,Collision collision)
  {}
  public void clearCollisions()
  {}
  public Shape getCollisionShape()
  {
    return null;
  }*//*
  public SquareCord getCord()
  {
    return ((State)state).cord;
  }
  public void setCord(SquareCord cord) throws GameException
  {
    if(cord.getWorld()!=this.world)
      throw new GameException();
    if(stateNew==null)
      forkState();
    ((State)stateNew).cord=cord;
  }*//*
  static class State extends EntityState
  {
    Vector2d place;
    Vector2d velocity;
    double angle;
    Color colorOfBackground;
    SquareCord cord;
    public void fix(World world)
    {
      if(cord!=null&&cord.getWorld()!=world)
        cord=null;
    }
  }*/
//}
