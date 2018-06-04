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

  private Vector2d place;
  private Vector2d velocity;
  private Vector2d acceleration;

  Vector2d getPlace()
  {
    return new Vector2d();
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
