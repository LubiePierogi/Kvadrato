package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.other.BackgroundColor;
import kvadrato.view.Backgrounds;

public class ViewSquareSquare extends ViewAppearanceElement
{
  private BackgroundColor bg;
  public ViewSquareSquare()
  {
    bg=BackgroundColor.WHITE;
  }
  public void set(AppearanceElement e)
  {
    SquareSquare ee=(SquareSquare)e;
    bg=ee.colorOfSquare;
  }
  public void draw(GraphicsContext context)
  {
    context.setFill(Backgrounds.squareColor(bg));
    context.fillRect(-.125,-.125,.25,.25);
  }
}
