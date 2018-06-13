package kvadrato;

import java.lang.Math;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import kvadrato.utils.GameException;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.other.BgColor;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.view.Colors;
import kvadrato.view.ae.ViewAppearanceElement;

public final class Renderer
{
  private Renderer(){}

  public static void set(GraphicsContext context,AppearanceElement e)
  {
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.translate(400.+100.*e.x,300.-100.*e.y);
    context.scale(e.scale*100.,-e.scale*100.);
    context.rotate(e.angle*180./Math.PI);

  }

  public static void draw(GraphicsContext context,Model model)
    throws GameException
  {
    double width=800;
    double height=600;
    ViewOfWorld view=model.getWorldView();
    if(view==null)
      return;
    BgColor bgColor=view.getBgColor();
    ViewAppearanceElement vae;
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.setFill(Colors.bgToBackground(bgColor));
    context.fillRect(0,0,width,height);
    List<AppearanceElement> list=view.getThings();
    for(AppearanceElement e:list)
    {
      String className=e.getClass().getName();
      // kvadrato.game.appearance.
      // kvadrato.view.ae.
      // 25
      className="kvadrato.view.ae.View"+className.substring(25);
      try
      {
        Class c=Class.forName(className);
        vae=(ViewAppearanceElement)c.newInstance();
        vae.set(e);
        Renderer.set(context,e);
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
