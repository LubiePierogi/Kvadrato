package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.gui.GuiElement;

public class HelpScreen extends StackPane implements GuiElement
{
  public HelpScreen()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/helpScreen.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {}
}
