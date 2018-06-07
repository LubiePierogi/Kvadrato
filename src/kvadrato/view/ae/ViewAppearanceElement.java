package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interfejs do klas, które określają, jak rysować różne elementy z pakietu
 * kvadrato.game.appearance.
 */
public abstract class ViewAppearanceElement
{
  public abstract void draw
    (GraphicsContext context,double x,double y,double angle,double scale);
}
