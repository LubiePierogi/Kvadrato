package kvadrato.game.collision;

import java.util.List;
import java.util.ArrayList;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;

/**
 * Jest to klasa zawierające informacje i jakimś zderzeniu dwóch rzeczy
 * przypisywana do jednostek po obliczniu kolizji.
 */
public final class CollisionOccurrence
{
  public final Vec2d translation;
  CollisionOccurrence(Vec2d translation)
  {
    this.translation=translation;
  }
  public CollisionOccurrence negate()
  {
    return new CollisionOccurrence(translation.negD());
  }
}
