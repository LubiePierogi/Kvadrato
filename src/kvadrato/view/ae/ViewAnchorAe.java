package kvadrato.view.ae;

import java.lang.Math;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.AnchorAe;

public class ViewAnchorAe extends ViewAppearanceElement
{
  public ViewAnchorAe()
  {
  }
  public void set(AppearanceElement e)
  {
    // Nic do zrobienia.
  }
  public void draw(GraphicsContext context)
  {
    context.setFill(new Color(0.,1.,0.,1./3.));
    context.fillPolygon
      (new double[]{.3,0.,.24,-.3},new double[]{.3,0.,.27,.3},4);
  }
}
