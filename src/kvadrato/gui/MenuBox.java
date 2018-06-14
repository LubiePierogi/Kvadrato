package kvadrato.gui;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.Model;

public class MenuBox extends StackPane implements Controller
{
  @FXML
  private int x;

  public MenuBox()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource("/content/menu.fxml"));
    //fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }

}
