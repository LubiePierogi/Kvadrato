package kvadrato.gui;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.Model;
import kvadrato.gui.menu.HelpScreen;
import kvadrato.gui.menu.MainMenu;
import kvadrato.gui.menu.PauseMenu;

public class Menu extends StackPane implements GuiElement
{
  @FXML
  private HelpScreen helpScreen;
  @FXML
  private MainMenu mainMenu;
  @FXML
  private PauseMenu pauseMenu;

  public Menu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource("/content/view/menu.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {

  }
}
