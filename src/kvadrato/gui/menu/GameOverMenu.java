package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.text.Text;

import kvadrato.gui.GuiElement;
import kvadrato.gui.GuiProcedure;
import kvadrato.gui.GuiIntFunction;
import kvadrato.gui.Procs;

public class GameOverMenu extends StackPane implements GuiElement
{
  @FXML private Group restartSquare;
  @FXML private Group backSquare;
  @FXML private Text score;

  Object target;

  public GameOverMenu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/gameOverMenu.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {
    Procs.changeWithInterpolation
      (restartSquare,restartSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
    Procs.changeWithInterpolation
      (backSquare,backSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
  }

  public void begin()
  {
    target=restartSquare;
    Procs.changeImmediately(restartSquare,1.0,"scaleX","scaleY");
    Procs.changeImmediately(backSquare,1.0,"scaleX","scaleY");
    score.setText(""+getSavedScore());
  }

  public void keyPressed(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case ESCAPE:
        requestBack();
        break;
      case SPACE:
      case ENTER:
        if(target==restartSquare)restartClick();
        else if(target==backSquare)backClick();
        break;
      case RIGHT:
        target=backSquare;
        break;
      case LEFT:
        target=restartSquare;
        break;
    }
  }
  public void keyReleased(KeyEvent ev){}



  @FXML void restartClick(){requestRestart();}
  @FXML void restartEnter(){target=restartSquare;}

  @FXML void backClick(){requestBack();}
  @FXML void backEnter(){target=backSquare;}

  private void requestBack(){if(onBackRequest!=null)onBackRequest.call();}
  public void setOnBackRequest(GuiProcedure q){onBackRequest=q;}
  private GuiProcedure onBackRequest;

  private void requestRestart()
    {if(onRestartRequest!=null)onRestartRequest.call();}
  public void setOnRestartRequest(GuiProcedure q){onRestartRequest=q;}
  private GuiProcedure onRestartRequest;

  private int getSavedScore()
    {if(savedScoreGetter!=null)return savedScoreGetter.call();return -1;}
  public void setSavedScoreGetter(GuiIntFunction q){savedScoreGetter=q;}
  private GuiIntFunction savedScoreGetter;

}
