package kvadrato;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.other.BackgroundColor;
import kvadrato.view.Backgrounds;
import kvadrato.view.ae.ViewAppearanceElement;
import java.lang.Math;
import java.lang.reflect.Constructor;

public final class Renderer
{
  private Renderer(){}

  public static void set(GraphicsContext context,AppearanceElement e)
  {
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.translate(400.+200.*e.x,300.-200.*e.y);
    context.scale(e.scale*200.,-e.scale*200.);
    context.rotate(e.angle*180./Math.PI);

    // To jest i tak do poprawienia.

  }

  public static void draw(GraphicsContext context,Model model)
  {
    //System.out.println("qwertyuiop");
    //context.setFill(new Color(1,0,1,1));
    //context.fillRect(2,2,300,300);
    double width=800;
    double height=600;
    ViewOfWorld view=model.getWorldView();
    BackgroundColor bgColor=view.getBackground();
    ViewAppearanceElement vae;
    context.setTransform(1.,0.,0.,1.,0.,0.);
    context.setFill(Backgrounds.bgToColor(bgColor));
    context.fillRect(0,0,width,height);
    List<AppearanceElement> list=view.getThings();
    for(AppearanceElement e:list)
    {
      String className=e.getClass().getName();
      // kvadrato.game.appearance.
      // kvadrato.view.ae.
      // 25
      className="kvadrato.view.ae.View"+className.substring(25);
      //System.out.println(className);
      try
      {
        Class c=Class.forName(className);
        vae=(ViewAppearanceElement)c.newInstance();
        vae.set(e);
        Renderer.set(context,e);
        vae.draw(context);
      }
      catch(ClassNotFoundException exc)
      {}
      catch(IllegalAccessException exc)
      {}
      catch(InstantiationException exc)
      {}
    }
  }
}
