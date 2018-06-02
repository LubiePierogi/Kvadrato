package kvadrato.game;
abstract public class Entity
{
  /**
   * Ta zmienna przechowuje, w którym świecie jest jednostka.
   * Będzie tylko jedenświat, ale tak jest ładnie.
   */
  World world;
  EntityState state;
  EntityState stateNew;
  private boolean hasToBeRemoved;
  /**
   * Konstuktor, który jest tylko po to, żeby ustawić zmienną hasToBeRemoved
   * na false.
   */
  public Entity()
  {
    hasToBeRemoved=false;
  }
  /**
   * Funkcja ustawiająca, że jednostka ma być usunięta ze świata.
   */
  public void remove()
  {
    hasToBeRemoved=true;
  }
  /**
   * Ta funkcja zwraca, czy jednostka ma być usunięta
   */
  public boolean isRemoved()
  {
    return hasToBeRemoved;
  }
  /**
   * Funkcja zmieniająca stan jednostki lub świata co każdy krok. Tworzy ona
   * nowy stan i zmienia tylko jego.
   */
  public abstract void doThings();
  /**
   * Funkcja wpisująca nowy stan jednostki w miejsce aktualnego/starego.
   */
  // Zadaniem tej funkcji jest przepisanie nowego stanu do teraźniejszego
  // i ustawienie nowego stanu na null.
  public final void update()
  {
    if(stateNew!=null)
    {
      state=stateNew;
      stateNew=null;
    }
  }
  /**
   * Ta funkcja służy do stworzenia nowego stanu w jednostce.
   */
  void forkState()
  {
    stateNew=state.clone();
  }
  /**
   * Funkcja zwracająca świat, na którym jest jednostka.
   */
  public World getWorld()
  {
    return world;
  }
}
