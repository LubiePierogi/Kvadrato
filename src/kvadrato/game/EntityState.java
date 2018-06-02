package kvadrato.game;
/**
 * To jest wzór klasy do przechowywania stanu jednostki na świecie. Jednostki
 * Mają jednocześnie dwa stany, teraźniejszy i przyszły. Przy zmienianiu czegoś
 * na świecie czytany jest stan teraźniejszy, a pisany jest nowy. Po zmianie
 * wszystkiego na świecie teraźniejszy jest zastępowany nowym.
 */
abstract public class EntityState implements Cloneable
{
  /**
   * Funkcja usuwająca odnośniki do usuniętych jednostek i ogólnie naprawiająca
   * jednostkę przed funkcją update. Działa ona na starym stanie.
   */
  public abstract void fix(World world);
  /**
   * Kopiowanie stanu.
   */
  public EntityState clone()
  {
    try
    {
      return (EntityState)super.clone();
    }
    catch(CloneNotSupportedException exc)
    {
      // Robienie niczego.
    }
    return null;
  }
}
