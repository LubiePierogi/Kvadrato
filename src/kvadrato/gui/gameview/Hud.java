package kvadrato.gui.gameview;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import kvadrato.gui.GuiElement;

public class Hud extends StackPane
{
  @FXML Label scoreQuantity;

  public Hud()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/gameview/hud.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void setScore(String s)
  {
    scoreQuantity.setText(s);
  }
}
