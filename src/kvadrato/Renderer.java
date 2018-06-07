package kvadrato;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Renderer
{
  private Renderer(){}

  public static void draw(GraphicsContext context,Model model)
  {
    //System.out.println("qwertyuiop");
    context.setFill(new Color(1,0,1,1));
    context.fillRect(2,2,300,300);
  }
}
