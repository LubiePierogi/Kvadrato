package kvadrato.game.entityinterfaces;
import kvadrato.game.Vector2d;
/**
 * Interfejs jednostki, która ma jakieś swoje miejsce na świecie.
 */
public interface EntityWithPlace
{
  Vector2d getPlace();
  double getAngle();
}
