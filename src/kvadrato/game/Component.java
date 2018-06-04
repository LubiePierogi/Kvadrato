package kvadrato.game;
public abstract class Component
{
  // nic tu nie ma
  public abstract void fix();
  public abstract void doThings();
  public abstract void update();



  Entity ent;

  protected final Entity getEntity()
  {
    return ent;
  }


  //getDeltaTime();
}
