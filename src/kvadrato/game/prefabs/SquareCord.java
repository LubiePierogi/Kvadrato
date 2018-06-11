package kvadrato.game.prefabs;

import kvadrato.utils.GameException;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;

/**
 * To jest klasa obiektu, który nie jest widoczny w grze, ale gracz cały czas
 * jest do niego przyczepiony i środek ekranu jest ustawwiony w miejscu,
 * gdzie jest ten obiekt.
 */
public class SquareCord extends Prefab
{
  public void makeEntity(Entity ent)
    throws GameException
  {
    throw new GameException();
  }
}
