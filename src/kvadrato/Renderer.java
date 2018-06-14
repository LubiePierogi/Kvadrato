package kvadrato;

import java.lang.Math;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import kvadrato.utils.GameException;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.other.BgColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.worldview.Colors;
import kvadrato.worldview.ae.ViewAppearanceElement;

public final class Renderer
{
  private Renderer(){}

  public static void set(GraphicsContext context,AppearanceElement e,
    double w,double h)
  {
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.translate(w/2.+h/6.*e.x,h/2.-h/6.*e.y);
    context.scale(e.scale*h/6.,-e.scale*h/6.);
    context.rotate(e.angle*180./Math.PI);

  }

  public static void draw(GraphicsContext context,Model model,double w,double h)
    throws GameException
  {
    ViewOfWorld view=model.getWorldView(10.);
    if(view==null)
      return;
    BgColor bgColor=view.getBgColor();
    ViewAppearanceElement vae;
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.setFill(Colors.bgToBackground(bgColor));
    context.fillRect(0,0,w,h);
    List<AppearanceElement> list=view.getThings();
    for(AppearanceElement e:list)
    {
      String className=e.getClass().getName();
      // kvadrato.game.appearance.
      // kvadrato.worldview.ae.
      // 25
      className="kvadrato.worldview.ae.View"+className.substring(25);
      try
      {
        Class c=Class.forName(className);
        vae=(ViewAppearanceElement)c.newInstance();
        vae.set(e);
        Renderer.set(context,e,w,h);
        vae.draw(context);
      }
      catch
      (
        ClassNotFoundException | IllegalAccessException | InstantiationException
        exc
      )
      {
        throw new GameException(exc);
      }
    }
  }
}
