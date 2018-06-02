package kvadrato.game;
import kvadrato.game.entityinterfaces.*;
/**
 * To jest klasa obiektu, który nie jest widoczny w grze, ale gracz cały czas
 * jest do niego przyczepiony i środek ekranu jest ustawwiony w miejscu,
 * gdzie jest ten obiekt.
 */
public class SquareCord extends Entity implements EntityWithPlace
{
  public SquareCord()
  {
  }
  public void doThings()
  {
  }
  public Vector2d getPlace()
  {
    return new Vector2d();
  }
  public double getAngle()
  {
    return 0.0;
  }
  static class State extends EntityState
  {
    Vector2d place;
    Vector2d velocity;
    /**
     * Jest to kąt kierunku, gdzie przyspiesza cały czas ten obiekt.
     */
    double angleOfAcceleration;
    Square square;
    public void fix(World world)
    {
      if(square!=null&square.getWorld()!=world)
        square=null;
    }
  }
}
