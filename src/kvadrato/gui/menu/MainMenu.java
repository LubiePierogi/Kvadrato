package kvadrato.gui.menu;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import kvadrato.gui.*;

public class MainMenu extends StackPane implements GuiElement
{
  @FXML private Group helpSquare,quitSquare,playSquare;

  private boolean menuIsOpen;

  private Object target;

  public MainMenu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource
      ("/content/view/menu/mainMenu.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
    menuIsOpen=false;
    target=null;
  }
  public void animate(double diff)
  {
    if(!isVisible())return;
    if(menuIsOpen)
    {
      Procs.changeWithInterpolation
        (playSquare,-60.0,diff,"translateY");
      Procs.changeWithInterpolation
        (playSquare,playSquare==target?1.0625:1.0,diff,"scaleX","scaleY");
      Procs.changeWithInterpolation
        (helpSquare,helpSquare==target?180.0:150.0,diff,"translateY");
      Procs.changeWithInterpolation
        (quitSquare,quitSquare==target?180.0:150.0,diff,"translateY");
    }
    else
    {
      Procs.changeWithInterpolation
        (playSquare,0.0,diff,"translateY");
      Procs.changeWithInterpolation
        (playSquare,playSquare==target?1.125:1.0,diff,"scaleX","scaleY");

    }
  }

  public void begin()
  {
    target=null;
    menuIsOpen=false;
    Procs.changeImmediately(playSquare,0.0,"translateY");
    Procs.changeImmediately(playSquare,1.0,"scaleX","scaleY");
    Procs.changeImmediately(helpSquare,0.0,"translateY");
    Procs.changeImmediately(quitSquare,0.0,"translateY");
  }

  public void keyPressed(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case ESCAPE:
        if(!menuIsOpen)openMenu();
        else target=quitSquare;
        break;
      case ENTER:
      case SPACE:
        if(!menuIsOpen)openMenu();
        else if(target==playSquare)playClick();
        else if(target==helpSquare)helpClick();
        else if(target==quitSquare)quitClick();
        break;
      case UP:
        target=playSquare;
        break;
      case DOWN:
        if(target==playSquare||target==null)target=helpSquare;
        break;
      case LEFT:
        if(target==quitSquare)target=helpSquare;
        break;
      case RIGHT:
        if(target==helpSquare)target=quitSquare;
        break;
    }
  }
  public void keyReleased(KeyEvent ev){}

  @FXML private void helpClick()
  {
    requestHelp();
  }
  @FXML private void helpEnter()
  {
    target=helpSquare;
  }

  @FXML private void quitClick()
  {
    requestQuit();
  }
  @FXML private void quitEnter()
  {
    target=quitSquare;
  }

  @FXML private void playClick()
  {
    if(menuIsOpen) requestStartGame();
    else openMenu();
  }
  @FXML private void playEnter()
  {
    target=playSquare;
  }
  @FXML private void playExit()
  {
    if(!menuIsOpen)target=null;
  }

  private void openMenu()
  {
    menuIsOpen=true;
    target=playSquare;
  }
  private void closeMenu()
  {
    menuIsOpen=false;
    target=null;
  }
  private void requestStartGame()
    {if(onStartGameRequest!=null)onStartGameRequest.call();}
  private void requestHelp()
    {if(onHelpRequest!=null)onHelpRequest.call();}
  private void requestQuit()
    {if(onQuitRequest!=null)onQuitRequest.call();}

  private GuiProcedure onStartGameRequest;
  public void setOnStartGameRequest(GuiProcedure q){onStartGameRequest=q;}
  private GuiProcedure onHelpRequest;
  public void setOnHelpRequest(GuiProcedure q){onHelpRequest=q;}
  private GuiProcedure onQuitRequest;
  public void setOnQuitRequest(GuiProcedure q){onQuitRequest=q;}

}
