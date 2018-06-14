package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;

import kvadrato.gui.Procs;
import kvadrato.gui.GuiElement;
import kvadrato.gui.GuiProcedure;

public class PauseMenu extends StackPane implements GuiElement
{
  @FXML private Group restartSquare;
  @FXML private Group resumeSquare;
  @FXML private Group backSquare;

  Object target;

  public PauseMenu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/pauseMenu.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {
    Procs.changeWithInterpolation
      (restartSquare,restartSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
    Procs.changeWithInterpolation
      (resumeSquare,resumeSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
    Procs.changeWithInterpolation
      (backSquare,backSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
  }

  public void begin()
  {
    target=resumeSquare;
    Procs.changeImmediately(restartSquare,1.0,"scaleX","scaleY");
    Procs.changeImmediately(resumeSquare,1.0,"scaleX","scaleY");
    Procs.changeImmediately(backSquare,1.0,"scaleX","scaleY");
  }

  public void keyPressed(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case ESCAPE:
        requestResume();
        break;
      case ENTER:
      case SPACE:
        if(target==restartSquare)restartClick();
        else if(target==resumeSquare)resumeClick();
        else if(target==backSquare)backClick();
      case RIGHT:
        if(target==restartSquare)target=resumeSquare;
        else if(target==resumeSquare)target=backSquare;
        break;
      case LEFT:
        if(target==resumeSquare)target=restartSquare;
        else if(target==backSquare)target=resumeSquare;
        break;
    }
  }
  public void keyReleased(KeyEvent ev){}



  @FXML private void restartClick(){requestRestart();}
  @FXML private void restartEnter(){target=restartSquare;}

  @FXML private void resumeClick(){requestResume();}
  @FXML private void resumeEnter(){target=resumeSquare;}

  @FXML private void backClick(){requestBack();}
  @FXML private void backEnter(){target=backSquare;}

  private void requestRestart()
    {if(onRestartRequest!=null)onRestartRequest.call();}
  private GuiProcedure onRestartRequest;
  public void setOnRestartRequest(GuiProcedure q){onRestartRequest=q;}

  private void requestResume()
    {if(onResumeRequest!=null)onResumeRequest.call();}
  private GuiProcedure onResumeRequest;
  public void setOnResumeRequest(GuiProcedure q){onResumeRequest=q;}

  private void requestBack()
    {if(onBackRequest!=null)onBackRequest.call();}
  private GuiProcedure onBackRequest;
  public void setOnBackRequest(GuiProcedure q){onBackRequest=q;}


}
