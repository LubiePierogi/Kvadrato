package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;

import kvadrato.game.appearance.AppearanceElement;

/**
 * Interfejs do klas, które określają, jak rysować różne elementy z pakietu
 * kvadrato.game.appearance.
 */
public abstract class ViewAppearanceElement
{
  public abstract void draw(GraphicsContext context);
  public abstract void set(AppearanceElement e);
}
