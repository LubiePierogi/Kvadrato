package kvadrato.game;

import java.util.Map;
import java.util.TreeMap;

public class Entity
{
  /**
   * Ta zmienna przechowuje, w którym świecie jest jednostka.
   * Będzie tylko jeden świat, ale tak jest ładnie.
   */
  World world;
  /**
   * Ta zmienna przechowuje, czy następna funkcja update na świecie
   * ma usunąć tę jednostkę.
   */
  private boolean hasToBeRemoved;
  /**
   * Mapa przechowująca wszytkie komponenty, każdy jest przyporządkowany
   * do swej nazwy.
   */
  Map<String,Component> components;
  /**
   * Na razie bezużyteczna zmienna, ale przechowuje nazwę klasy jednostki.
   */
  String prefabName;
  /**
   * Zwykły konstruktor. Nie jest publiczny, bo tylko świat ma móc
   * zrobić jednostkę.
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
  /**
   * Zwraca nazwę klasy.
   */
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
    //System.out.println("vvvvvvvvvvvvvvvvvvvvv");
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
    try
    {
      components.forEach((k,v)->
      {
        v.update();
      });
    }
    catch(Throwable exc)
    {
      exc.printStackTrace();
    }
  }
  public final void addComponent(String name)
    throws GameException//,IllegalAccessException
  {
    try
    {
      Class c=Class.forName("kvadrato.game.components."+name);
    }
    catch(ClassNotFoundException exc){throw new GameException(exc);}
    try
    {
      Component co=(Component)c.newInstance();
    }
    catch(InstantiationException exc){throw new GameException(exc);}
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
