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
  public final Vec2d center;
  public final double volume;
  CollisionOccurrence(Vec2d center,double volume)
  {
    this.center=center;
    this.volume=volume;
    // Na razie dajemy nic.
  }
}
