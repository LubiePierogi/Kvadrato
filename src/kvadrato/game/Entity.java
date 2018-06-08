package kvadrato.game;

import java.util.Map;
import java.util.TreeMap;

public class Entity
{
  /**
   * Ta zmienna przechowuje, w którym świecie jest jednostka.
   * Będzie tylko jedenświat, ale tak jest ładnie.
   */
  World world;
  private boolean hasToBeRemoved;
  Map<String,Component> components;
  String prefabName;
  /**
   * Konstuktor, który jest tylko po to, żeby ustawić zmienną hasToBeRemoved
   * na false i żeby zrobić tablicę skłądników.
   */
  public Entity()
  {
    hasToBeRemoved=false;
    components=new TreeMap<String,Component>();
  }
  /**
   * Funkcja ustawiająca, że jednostka ma być usunięta ze świata.
   */
  public void remove()
  {
    hasToBeRemoved=true;
  }
  public String getName()
  {
    return prefabName;
  }
  /**
   * Ta funkcja zwraca, czy jednostka ma być usunięta
   */
  public boolean isRemoved()
  {
    return hasToBeRemoved;
  }
  public final void fix()
  {
    components.forEach((k,v)->
    {
      v.fix();
    });
  }
  /**
   * Funkcja zmieniająca stan jednostki lub świata co każdy krok. Tworzy ona
   * nowy stan i zmienia tylko jego.
   */
  public final void doThings()
  {
    System.out.println("vvvvvvvvvvvvvvvvvvvvv");
    components.forEach((k,v)->
    {
      v.doThings();
    });
  }
  /**
   * Funkcja wpisująca nowy stan jednostki w miejsce aktualnego/starego.
   */
  // Zadaniem tej funkcji jest przepisanie nowego stanu do teraźniejszego
  // i ustawienie nowego stanu na null.
  public final void update()
  {
    components.forEach((k,v)->
    {
      v.update();
    });
  }
  public final void addComponent(String name)
    throws ClassNotFoundException,InstantiationException,IllegalAccessException
  {
    Class c=Class.forName("kvadrato.game.components."+name);
    Component co=(Component)c.newInstance();
    co.ent=this;
    components.put(name,co);
  }
  public final Component getComponent(String name)
  {
    Component x=components.get(name);
    return x;
  }
  public final boolean hasComponent(String name)
  {
    return components.containsKey(name);
  }
  /**
   * Funkcja zwracająca świat, na którym jest jednostka.
   */
  public World getWorld()
  {
    return world;
  }
}
