package kvadrato.gui;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;

import kvadrato.Model;
import kvadrato.gui.gameview.Hud;

public class GameView extends StackPane implements GuiElement
{
  @FXML
  private Canvas gameCanvas;

  @FXML
  private Hud hud;

  public GameView()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource("/content/view/gameView.fxml"));
    //fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {}
}
