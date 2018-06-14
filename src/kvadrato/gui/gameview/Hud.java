package kvadrato.gui.gameview;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.gui.GuiElement;

public class Hud extends StackPane implements GuiElement
{
  public Hud()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/gameview/hud.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff){}
}
