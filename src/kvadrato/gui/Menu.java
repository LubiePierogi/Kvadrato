package kvadrato.gui;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import kvadrato.Model;
import kvadrato.gui.menu.HelpScreen;
import kvadrato.gui.menu.MainMenu;
import kvadrato.gui.menu.PauseMenu;
import kvadrato.gui.menu.GameOverMenu;

public class Menu extends StackPane implements GuiElement,Initializable
{
  @FXML
  private HelpScreen helpScreen;
  @FXML
  private MainMenu mainMenu;
  @FXML
  private PauseMenu pauseMenu;
  @FXML
  private GameOverMenu gameOverMenu;

  private Object target;

  public Menu()
    throws IOException
  {
    FXMLLoader fxmlLoader=
      new FXMLLoader(getClass().getResource("/content/view/menu.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    fxmlLoader.load();
    target=null;
  }
  public void initialize(URL location,ResourceBundle resources)
  {
    mainMenu.setOnStartGameRequest(()->requestStartGame());
    mainMenu.setOnHelpRequest(()->goToHelpScreen());
    mainMenu.setOnQuitRequest(()->requestQuit());
    helpScreen.setOnBackRequest(()->goToMainMenu());
    pauseMenu.setOnRestartRequest(()->requestStartGame());
    pauseMenu.setOnResumeRequest(()->requestResumeGame());
    pauseMenu.setOnBackRequest(()->requestThrowGame());
    gameOverMenu.setOnBackRequest(()->goToMainMenu());
    gameOverMenu.setOnRestartRequest(()->requestStartGame());
    gameOverMenu.setHiScoreGetter(()->getHiScore());
  }
  public void resize(double w,double h)
  {

  }
  public void animate(double diff)
  {
    if(!isVisible())
      return;
    helpScreen.animate(diff);
    mainMenu.animate(diff);
    pauseMenu.animate(diff);
    gameOverMenu.animate(diff);
  }
  public void keyPressed(KeyEvent ev)
  {
    if(helpScreen==target)helpScreen.keyPressed(ev);
    else if(mainMenu==target)mainMenu.keyPressed(ev);
    else if(pauseMenu==target)pauseMenu.keyPressed(ev);
    else if(gameOverMenu==target)gameOverMenu.keyPressed(ev);
  }
  public void keyReleased(KeyEvent ev)
  {
    if(helpScreen==target)helpScreen.keyReleased(ev);
    else if(mainMenu==target)mainMenu.keyReleased(ev);
    else if(pauseMenu==target)pauseMenu.keyReleased(ev);
    else if(gameOverMenu==target)gameOverMenu.keyReleased(ev);
  }
  public void goToMainMenu()
  {
    target=mainMenu;
    mainMenu.begin();
    mainMenu.setVisible(true);
    pauseMenu.setVisible(false);
    helpScreen.setVisible(false);
    gameOverMenu.setVisible(false);
  }
  public void goToHelpScreen()
  {
    target=helpScreen;
    helpScreen.begin();
    helpScreen.setVisible(true);
    mainMenu.setVisible(false);
    pauseMenu.setVisible(false);
    gameOverMenu.setVisible(false);
  }
  public void goToPauseMenu()
  {
    target=pauseMenu;
    pauseMenu.begin();
    pauseMenu.setVisible(true);
    helpScreen.setVisible(false);
    mainMenu.setVisible(false);
    gameOverMenu.setVisible(false);
  }
  public void goToGameOverMenu()
  {
    target=gameOverMenu;
    gameOverMenu.begin();
    gameOverMenu.setVisible(true);
    pauseMenu.setVisible(false);
    helpScreen.setVisible(false);
    mainMenu.setVisible(false);
  }
  public void begin()
  {
    goToMainMenu();
  }
  private void requestQuit()
    {if(onQuitRequest!=null)onQuitRequest.call();}
  public void setOnQuitRequest(GuiProcedure q){onQuitRequest=q;}
  private GuiProcedure onQuitRequest;

  private void requestStartGame()
    {if(onStartGameRequest!=null)onStartGameRequest.call();}
  public void setOnStartGameRequest(GuiProcedure q){onStartGameRequest=q;}
  private GuiProcedure onStartGameRequest;

  private void requestResumeGame()
    {if(onResumeGameRequest!=null)onResumeGameRequest.call();}
  public void setOnResumeGameRequest(GuiProcedure q){onResumeGameRequest=q;}
  private GuiProcedure onResumeGameRequest;

  private void requestThrowGame()
    {if(onThrowGameRequest!=null)onThrowGameRequest.call();}
  public void setOnThrowGameRequest(GuiProcedure q){onThrowGameRequest=q;}
  private GuiProcedure onThrowGameRequest;

  private int getHiScore()
    {if(hiScoreGetter!=null)return hiScoreGetter.call();return -1;}
  public void setHiScoreGetter(GuiIntFunction q){hiScoreGetter=q;}
  private GuiIntFunction hiScoreGetter;
}
