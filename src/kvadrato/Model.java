package kvadrato;
import kvadrato.game.World;
import kvadrato.game.entityinterfaces.ControllableEntity;
import kvadrato.game.entityinterfaces.EntityWithView;
import kvadrato.game.Vector2d;
import kvadrato.game.Square;
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
    world.removeAllEntities();
  }
  public void cookLevelStart()
  {
    Square square=new Square();
  }
}
