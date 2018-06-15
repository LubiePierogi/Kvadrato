package kvadrato.gui;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import kvadrato.Model;
import kvadrato.utils.GameException;
import kvadrato.gui.gameview.Hud;

public class GameView extends StackPane implements GuiElement
{
  @FXML
  private Canvas gameCanvas;

  @FXML
  private Hud hud;

  // Tutaj nie ma zmiennej target, bo zawsze "zaznaczona" jest gra.

  public GameView()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource("/content/view/gameView.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
  }
  public void animate(double diff)
  {
    if(!isVisible())return;
    //Renderer.draw
    try
    {
      drawGame();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }

    String score=getScore();
    hud.setScore(score);
  }
  public void resize(double w,double h)
  {
    gameCanvas.setWidth(w);
    gameCanvas.setHeight(h);
    hud.setScaleX(h/600.);
    hud.setScaleY(h/600.);
  }

  public void goToPlaying()
  {
    gameCanvas.setVisible(true);
    hud.setVisible(true);
  }

  public void keyReleased(KeyEvent ev)
  {
    if(onControlRequest!=null)
    {
      String k=null;
      String v=null;
      switch(ev.getCode())
      {
        case UP:
          requestControl("up","");
          break;
        case RIGHT:
          requestControl("right","");
          break;
        case DOWN:
          requestControl("down","");
          break;
        case LEFT:
          requestControl("left","");
          break;
        case Q:
          requestControl("color","");
          break;
        case W:
          requestControl("color","");
          break;
        case E:
          requestControl("color","");
          break;
        case R:
          requestControl("color","");
          break;
        case A:
          requestControl("color","");
          break;
        case S:
          requestControl("color","");
          break;
        case D:
          requestControl("color","");
          break;
        case F:
          requestControl("color","");
          break;
      }
    }
  }
  public void keyPressed(KeyEvent ev)
  {
    if(onControlRequest!=null)
    {
      String k=null;
      String v=null;
      switch(ev.getCode())
      {
        case ESCAPE:
          requestPause();
          break;
        case UP:
          requestControl("up","q");
          break;
        case RIGHT:
          requestControl("right","q");
          break;
        case DOWN:
          requestControl("down","q");
          break;
        case LEFT:
          requestControl("left","q");
          break;
        case Q:
          requestControl("color","q");
          break;
        case W:
          requestControl("color","w");
          break;
        case E:
          requestControl("color","e");
          break;
        case R:
          requestControl("color","r");
          break;
        case A:
          requestControl("color","a");
          break;
        case S:
          requestControl("color","s");
          break;
        case D:
          requestControl("color","d");
          break;
        case F:
          requestControl("color","f");
          break;
      }
    }
  }

  private String getScore()
    {if(scoreGetter!=null)return ""+scoreGetter.call();return "";}
  private GuiIntFunction scoreGetter;
  public void setScoreGetter(GuiIntFunction q){scoreGetter=q;}

  private void drawGame()
    throws GameException
  {
    if(drawProc==null)
      return;
    drawProc.call(gameCanvas);
  }
  private GuiDrawGameProcedure drawProc;
  public void setDrawProc(GuiDrawGameProcedure q){drawProc=q;}

  private void requestControl(String k,String v)
  {
    if(onControlRequest==null)return;
    onControlRequest.call(k,v);
  }
  private GuiControlProcedure onControlRequest;
  public void setOnControlRequest(GuiControlProcedure q){onControlRequest=q;}

  private void requestPause(){if(onPauseRequest!=null)onPauseRequest.call();}
  private GuiProcedure onPauseRequest;
  public void setOnPauseRequest(GuiProcedure q){onPauseRequest=q;}

}
