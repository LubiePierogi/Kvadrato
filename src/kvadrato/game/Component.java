package kvadrato.game;
public abstract class Component
{
  public abstract void fix();
  public abstract void doThings();
  public abstract void update();
  Entity ent;

  protected final Entity getEntity()
  {
    return ent;
  }
  protected final double getDelta()
  {
    return ent.world.getDeltaTime()/1000000000.;
  }
}
