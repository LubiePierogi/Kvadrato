package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.gui.GuiElement;

public class PauseMenu extends StackPane implements GuiElement
{
  @FXML
  private int x;

  public PauseMenu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/pauseMenu.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {}
}
