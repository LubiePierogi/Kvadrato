package kvadrato.game.prefabs;

import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

import kvadrato.utils.GameException;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Appearance;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.AnchorAe;

/**
 * To jest klasa obiektu, który nie jest widoczny w grze, ale gracz cały czas
 * jest do niego przyczepiony i środek ekranu jest ustawwiony w miejscu,
 * gdzie jest ten obiekt. Ściany też są do niego przyczepione.
 */
public class Anchor extends Prefab
{
  public void makeEntity(Entity ent)
    throws GameException
  {
    ent.setTag("Anchor");

    ent.addComponent("Physics");
    ent.addComponent("Appearance");
    ent.addComponent("Camera");

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
    }

    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setMass(1.0);
    }
  }
}
