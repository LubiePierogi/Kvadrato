package kvadrato;
import kvadrato.game.World;
import kvadrato.game.entityinterfaces.ControllableEntity;
import kvadrato.game.entityinterfaces.EntityWithView;
import kvadrato.game.Vector2d;
import kvadrato.game.prefabs.Square;

public class Model
{
  //View view; // I tak bdzie tylko jeden.
  /**
   * Świat, na którym się gra. Ten obiekt jest pusty, jeśli nie mamy świata.
   */
  World world;
  /**
  * Ta zmienna przechowuje, którym czymś na świecie jest gracz.
  */
  ControllableEntity playerControlPointer;
  EntityWithView viewPointer;
  /**
  * Domyślny konstruktor.
  */
  public Model()
  {
    world=new World();
  }
  public void clearTheWorld()
  {
    world.lock();
    try
    {
      world.removeAllEntities();
    }
    finally
    {
      world.unlock();
    }
  }
  public void cookLevelStart()
  {
    world.lock();
    try
    {
      world.spawn("Square");
    }
    catch(ClassNotFoundException exc)
    {

    }
    catch(InstantiationException exc)
    {

    }
    catch(IllegalAccessException exc)
    {

    }
    finally
    {
      world.unlock();
    }
  }
}
