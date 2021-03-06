package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;

import kvadrato.gui.GuiElement;
import kvadrato.gui.GuiProcedure;
import kvadrato.gui.Procs;

public class HelpScreen extends StackPane implements GuiElement
{
  @FXML Group backSquare;

  private Object target;

  public HelpScreen()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/helpScreen.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {
    if(!isVisible())return;
    Procs.changeWithInterpolation
      (backSquare,backSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
  }

  public void begin()
  {
    target=backSquare;
    Procs.changeImmediately(backSquare,1.0,"scaleX","scaleY");
  }
  public void keyPressed(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case ESCAPE:
        requestBack();
        break;
      case ENTER:
      case SPACE:
        if(target==backSquare)requestBack();
        break;
    }
  }
  public void keyReleased(KeyEvent ev){}

  @FXML private void backClick(){requestBack();}

  private void requestBack(){if(onBackRequest!=null)onBackRequest.call();}
  private GuiProcedure onBackRequest;
  public void setOnBackRequest(GuiProcedure q){onBackRequest=q;}
}
