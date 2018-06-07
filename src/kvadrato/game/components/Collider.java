package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.collision.Shape;
import kvadrato.game.collision.Collision;
import kvadrato.game.Entity;
import java.util.Map;
import java.util.TreeMap;
import kvadrato.game.TriConsumer;

/**
 * Komponent odpowiadający za możliwość zderzania obiektu z innymi rzeczami
 * i posiadanie przezeń kształtu.
 */
public class Collider extends Component
{
  /**
   * Aktualny kształt.
   */
  private Shape shape;
  /**
   * Kształt, który przyjmie obiekt w następnym kroku świata.
   */
  private Shape shapeNew;
  /**
   * Mapa z kolizjami, które trzeba odsłużyć w następnej funkcji doThings().
   * Jest ona czyszczona w funkcji update().
   */
  Map<Entity,Collision> collisions;
  /**
   * Funkcja, która jeśli istnieje, to jest wywoływana dla jdnostki. Pierwszy
   * argument to ta jednostka, drugi to tamta inna, a trzeci to obiekt
   * z kolizją.
   */
  TriConsumer<Entity,Entity,Collision> fn;

  public Collider()
  {
    collisions=new TreeMap<Entity,Collision>();
  }





  /**
   * Zwraca aktualny kształt obiektu.
   */
  public Shape getShape()
  {
    return shape;
  }
  /**
   * Zwraca kształt obiektu z uwzględnieniem przesunięcia i obrotu.
   */
  public Shape getTransformedShape()
  {
    return shape; // To później się zmieni.
  }

  /**
   * Ustawia kształt obiektu, który będzie aktualny w następnym kroku na
   * świecie.
   */
  public void setShape(Shape sh)
  {
    shapeNew=sh;
  }

  /**
   * Dodaje kolizję do obsłużenia w następnym kroku.
   */
  public void addCollision(Entity ent,Collision col)
  {
    collisions.put(ent,col);
  }
  public void setFn(TriConsumer<Entity,Entity,Collision> func)
  {
    fn=func;
  }

  public void fix()
  {
    // Fix może być pusty, bo nigdy nie bęzie w liście jednostki, której nie ma
    // na świecie.
  }
  public void doThings()
  {
    if(fn!=null)
      collisions.forEach((k,v)->{fn.accept(this.getEntity(),k,v);});
  }
  public void update()
  {
    if(shapeNew!=null)
    {
      shape=shapeNew;
    }
    collisions.clear();
  }
}
