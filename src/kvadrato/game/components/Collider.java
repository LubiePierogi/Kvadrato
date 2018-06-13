package kvadrato.game.components;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import kvadrato.utils.GameException;
import kvadrato.utils.function.TriConsumer;
import kvadrato.game.Component;
import kvadrato.game.Entity;
import kvadrato.game.collision.ElementaryShape;
import kvadrato.game.collision.CollisionOccurrence;

/**
 * Komponent odpowiadający za możliwość zderzania obiektu z innymi rzeczami
 * i posiadanie przezeń kształtu.
 */
public class Collider extends Component
{
  /**
   * Mapa z kolizjami, które trzeba odsłużyć w następnej funkcji doThings().
   * Jest ona czyszczona w funkcji update().
   */
  private Map<Entity,CollisionOccurrence> collisions;
  /**
   * Funkcja, która jeśli istnieje, to jest wywoływana dla jdnostki. Pierwszy
   * argument to ta jednostka, drugi to tamta inna, a trzeci to obiekt
   * z kolizją.
   */
  private OnCollideFnType onCollideFn;
  public static interface OnCollideFnType
  {void call(Entity e,Entity o,CollisionOccurrence c);}
  /**
   * Funkcja zwracająca kształt obiektu.
   */
  private ShapeFnType shapeFn;
  public static interface ShapeFnType
  {ElementaryShape call(Entity e) throws GameException;}
  public Collider()
  {
    collisions=new TreeMap<Entity,CollisionOccurrence>();
  }
  public void setShapeFn(ShapeFnType func)
  {
    shapeFn=func;
  }
  /**
   * Zwraca aktualny kształt obiektu.
   * Jeśli dało null, to znaczy, że obiekt się z niczym nie zderza.
   */
  public ElementaryShape getShape()
    throws GameException
  {
    if(shapeFn!=null)
      return shapeFn.call(getEntity());
    return null;
  }
  /**
   * Dodaje kolizję do obsłużenia w następnym kroku.
   */
  public void addCollision(Entity ent,CollisionOccurrence col)
  {
    collisions.put(ent,col);
  }
  public void setOnCollideFn(OnCollideFnType func)
  {
    onCollideFn=func;
  }
  public void fix()
  {
    // Fix może być pusty, bo nigdy nie bęzie w liście jednostki, której nie ma
    // na świecie.
  }
  public void doThings()
  {
    if(onCollideFn!=null)
      for(Map.Entry<Entity,CollisionOccurrence> co:collisions.entrySet())
      {
        onCollideFn.call(this.getEntity(),co.getKey(),co.getValue());
      }
  }
  public void update()
  {
    collisions.clear();
  }
}
