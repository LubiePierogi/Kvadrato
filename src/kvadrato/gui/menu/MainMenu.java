package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.gui.GuiElement;

public class MainMenu extends StackPane implements GuiElement
{
  @FXML
  private int x;

  public MainMenu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/mainMenu.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {}
}
