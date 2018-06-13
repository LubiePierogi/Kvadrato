package kvadrato.game;

import java.util.Map;
import java.util.TreeMap;

import kvadrato.utils.GameException;
import kvadrato.game.Component;

public class Entity implements Comparable
// W dokumentacji pisali, żeby to napisać:
// Note: this class has a natural ordering that is inconsistent with equals.
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
   * Identyfikator jednostki na świecie, nie powtarzają się.
   */
  int id;
  /**
   * Zwykły konstruktor. Nie jest publiczny, bo tylko świat ma móc
   * zrobić jednostkę.
   */
  Entity()
  {
    hasToBeRemoved=false;
    components=new TreeMap<String,Component>();
    id=-1; // (-1) oznacza, że jeszcze nie jest ustawione.
  }
  /**
   * Funkcja ustawiająca, że jednostka ma być usunięta ze świata.
   */
  public void remove()
  {
    hasToBeRemoved=true;
  }
  /**
   * Zwraca nazwę klasy jednostki.
   */
  public String getName()
  {
    return prefabName;
  }
  /**
   *  Daje id jednostki.
   */
  public int getId()
  {
    return id;
  }
  public int compareTo(Object o)
  {
    if(id<((Entity)o).id)return -1;
    if(id>((Entity)o).id)return 1;
    return 0;
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
    try
    {
      components.forEach((k,v)->
      {
        v.doThings();
      });
    }
    catch(Throwable exc)
    {
      System.out.println("Funkcja doThings przy pewnej jednostce "+
        this.prefabName+" rzuciła wyjątek.");
      exc.printStackTrace(System.out);
    }
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
    throws GameException
  {
    Class c;
    Component co;
    try
    {
      c=Class.forName("kvadrato.game.components."+name);
    }
    catch(ClassNotFoundException exc){throw new GameException(exc);}
    try
    {
      co=(Component)c.newInstance();
    }
    catch(InstantiationException exc){throw new GameException(exc);}
    catch(IllegalAccessException exc){throw new GameException(exc);}
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
  public double getDelta()
  {
    return world.getDeltaTime()/1000000000.;
  }
}
