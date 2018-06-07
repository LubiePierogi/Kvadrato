package kvadrato.view.ae;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.other.BackgroundColor;

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
    switch(bg)
    {
      case WHITE:
        context.setFill(new Color(.3,.3,.3,1.));
        break;
      case BLUE:
        context.setFill(new Color(.4,.2,0.,1.));
        break;
      case GREEN:
        context.setFill(new Color(.36,0.,.36,1.));
        break;
      case YELLOW:
        context.setFill(new Color(.33,.18,0.,1.));
        break;
      case CYAN:
        context.setFill(new Color(.0,.25,.0,1.));
        break;
      default:
        context.setFill(new Color(0.1,0.2,0.3,1.));
    }
    context.fillRect(-.125,-.125,.25,.25);
  }
}
