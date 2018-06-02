package kvadrato.game.entityinterfaces;
import kvadrato.game.collision.Shape;
import kvadrato.game.collision.Collision;
import java.util.Vector;
/**
 * Interfejs jednostek, które mogą zderzać się z innymi.
 */
public interface EntityWithCollisions extends EntityWithPlace
{
  Shape getCollisionShape();
  void addCollision(EntityWithCollisions ent,Collision collision);
  Vector<Collision> getCollisions();
  void clearCollisions();
}
