package kvadrato.game.components;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

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
  private TriConsumer<Entity,Entity,CollisionOccurrence> onCollideFn;
  private Function<Entity,ElementaryShape> shapeFn;
  public Collider()
  {
    collisions=new TreeMap<Entity,CollisionOccurrence>();
  }
  public void setShapeFn(Function<Entity,ElementaryShape> func)
  {
    shapeFn=func;
  }
  /**
   * Zwraca aktualny kształt obiektu.
   * Jeśli dało null, to znaczy, że obiekt się z niczym nie zderza.
   */
  public ElementaryShape getShape()
  {
    if(shapeFn!=null)
      return shapeFn.apply(getEntity());
    return null;
  }
  /**
   * Dodaje kolizję do obsłużenia w następnym kroku.
   */
  public void addCollision(Entity ent,CollisionOccurrence col)
  {
    collisions.put(ent,col);
  }
  public void setOnCollideFn
    (TriConsumer<Entity,Entity,CollisionOccurrence> func)
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
      collisions.forEach((k,v)->{onCollideFn.accept(this.getEntity(),k,v);});
  }
  public void update()
  {
    collisions.clear();
  }
}
