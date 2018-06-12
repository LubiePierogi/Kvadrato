package kvadrato.game.components;

import java.util.Map;
import java.util.TreeMap;

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
   * Aktualny kształt.
   */
  //private Shape shape;
  /**
   * Kształt, który przyjmie obiekt w następnym kroku świata.
   */
  //private Shape shapeNew;
  /**
   * Mapa z kolizjami, które trzeba odsłużyć w następnej funkcji doThings().
   * Jest ona czyszczona w funkcji update().
   */
  Map<Entity,CollisionOccurrence> collisions;
  /**
   * Funkcja, która jeśli istnieje, to jest wywoływana dla jdnostki. Pierwszy
   * argument to ta jednostka, drugi to tamta inna, a trzeci to obiekt
   * z kolizją.
   */
  TriConsumer<Entity,Entity,CollisionOccurrence> fn;
  public Collider()
  {
    collisions=new TreeMap<Entity,CollisionOccurrence>();
  }
  /**
   * Zwraca aktualny kształt obiektu.
   */
  //public Shape getShape()
  //{
  //  return shape;
  //}
  /**
   * Zwraca kształt obiektu z uwzględnieniem przesunięcia i obrotu.
   */
  //public Shape getTransformedShape()
  //{
  //  return shape; // To później się zmieni.
  //}
  /**
   * Ustawia kształt obiektu, który będzie aktualny w następnym kroku na
   * świecie.
   */
  //public void setShape(Shape sh)
  //{
  ///  shapeNew=sh;
  //}
  ///**
   //* Dodaje kolizję do obsłużenia w następnym kroku.
   //*/
  public void addCollision(Entity ent,CollisionOccurrence col)
  {
    collisions.put(ent,col);
  }
  public void setFn(TriConsumer<Entity,Entity,CollisionOccurrence> func)
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
    //if(shapeNew!=null)
    //{
    //  shape=shapeNew;
    //}
    //collisions.clear();
  }
}
