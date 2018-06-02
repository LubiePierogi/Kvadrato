package kvadrato.game.entityinterfaces;
import kvadrato.game.Vector2d;
/**
 * Interfejs jednostki, która ma wskazanie na miejsce, gdzie ma być widok.
 */
public interface EntityWithView
{
  Vector2d getViewPlace();
  double getViewAngle();
}
