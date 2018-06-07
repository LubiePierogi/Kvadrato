package kvadrato;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kvadrato.game.ViewOfWorld;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.other.BackgroundColor;
import kvadrato.view.Backgrounds;

public final class Renderer
{
  private Renderer(){}




  public static void draw(GraphicsContext context,Model model)
  {
    //System.out.println("qwertyuiop");
    //context.setFill(new Color(1,0,1,1));
    //context.fillRect(2,2,300,300);
    ViewOfWorld view=model.getWorldView();
    BackgroundColor bgColor=view.getBackground();
    context.setFill(Backgrounds.bgToColor(bgColor));
    context.fillRect(0,0,800,600);
    List<AppearanceElement> list=view.getThings();
    for(AppearanceElement x:list)
    {
      String className=x.getClass().getName();
      System.out.println(className);
      // kvadrato.game.appearance.
      // kvadrato.view.ae.
      // 25
      className="kvadrato.view.ae."+className.substring(25);
      try
      {
        Class c=Class.forName(className);
      }
      catch(ClassNotFoundException exc)
      {}
      //catch(IllegalAccessException exc)
      //{}
    }

  }
}
