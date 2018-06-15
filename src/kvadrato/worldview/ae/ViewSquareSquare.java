package kvadrato.worldview.ae;

import java.lang.Math;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import kvadrato.game.other.BgColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.worldview.Colors;

public class ViewSquareSquare extends ViewAppearanceElement
{
  private BgColor bg;
  public ViewSquareSquare()
  {
    bg=BgColor.WHITE;
  }
  public void set(AppearanceElement e)
  {
    SquareSquare ee=(SquareSquare)e;
    bg=ee.bgColor;
  }
  public void draw(GraphicsContext context)
  {
    context.setFill(Colors.bgToSquare(bg));
    context.fillRect(-.125,-.125,.25,.25);
    context.setFill(Colors.bgToSquareTriangle(bg));
    context.fillPolygon(new double[]{.08,.01,.01},new double[]{0.,.07,-.07},3);
  }
}
