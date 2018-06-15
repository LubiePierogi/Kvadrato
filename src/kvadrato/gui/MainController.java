package kvadrato.gui;

import java.lang.reflect.*;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import kvadrato.Model;
import kvadrato.Renderer;
import kvadrato.utils.GameException;
import kvadrato.game.EventProxy;

public class MainController implements Initializable
{
  private Model model;
  private Stage window;
  private long lastAnimationUpdate;
  private double stateClock;

  private Object target;

  @FXML private StackPane theRoot;

  @FXML private StackPane almostRoot;

  @FXML private Menu menu;

  @FXML private GameView gameView;

  private AnimationTimer animationTimer;


  public MainController()
  {
    target=null;
    lastAnimationUpdate=0;
  }
  public void setModel(Model model)
  {
    this.model=model;
  }
  public void setWindow(Stage window)
  {
    this.window=window;
  }
  /**
   * Inicjalizacja niektórych rzeczy wywoływana przez JavaFX po wczytaniu FXML.
   */
  public void initialize(URL location,ResourceBundle resources)
  {
    animationTimer=new AnimationTimer()
    {
      public void handle(long now)
      {
        long diffNanos;
        double diff;
        if(lastAnimationUpdate<=0)
          lastAnimationUpdate=now;
        diffNanos=now-lastAnimationUpdate;
        diff=(double)(diffNanos)/1000000000.0;
        lastAnimationUpdate=now;
        gameView.animate(diff);
        menu.animate(diff);
      }
    };
    menu.setOnQuitRequest(()->closeRequest());
    menu.setOnStartGameRequest(()->startNewGame());
    menu.setOnResumeGameRequest(()->resumeGame());
    menu.setOnThrowGameRequest(()->throwGame());
    menu.setHiScoreGetter(()->getHiScore());
    gameView.setDrawProc(c->drawGame(c));
    gameView.setScoreGetter(()->getScore());
    gameView.setOnPauseRequest(()->pauseGame());
    gameView.setOnControlRequest((k,v)->gameControl(k,v));
  }
  /**
   * Następna inicjalizacja, ale taka później.
   */
  public void anotherInitialization()
  {
    window.setOnCloseRequest(e->{e.consume();closeRequest();});
    theRoot.getScene().setOnKeyPressed(e->keyPressed(e));
    theRoot.getScene().setOnKeyReleased(e->keyReleased(e));
    ChangeListener<Number> stageSizeListener=(observable,o,n)->
    {
      resize(window.getWidth(),window.getHeight());
    };
    window.widthProperty().addListener(stageSizeListener);
    window.heightProperty().addListener(stageSizeListener);
    begin();
    animationTimer.start();
  }
  /**
   * Funkcja zamykająca okno, a więc i program.
   */
  public void closeProgram()
  {
    try
    {
      model.haltWorld();
    }
    catch(GameException exc)
    {}
    window.close();
  }
  /**
   * Funkcja wywoływana przy próbie zamknięcia okna.
   */
  public void closeRequest()
  {
    closeProgram();
  }
  /**
   * Ta funkcja wywołuje zaczęcie nowej gry w modelu.
   */
  void startNewGame()
  {
    target=gameView;
    menu.setVisible(false);
    gameView.setVisible(true);
    gameView.goToPlaying();
    model.clearHiScore();
    model.getControlProxy().clear();
    try
    {
      model.cookLevelStart();
      model.pushWorld();
      model.getEventProxy().setEventListener(eventListener);
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }
  void pauseGame()
  {
    target=menu;
    menu.setVisible(true);
    gameView.setVisible(true);
    menu.goToPauseMenu();
    try
    {
      model.haltWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }
  private int getScore()
  {
    try
    {
      return model.getScore();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
    return -1;
  }
  private void resumeGame()
  {
    target=gameView;
    menu.setVisible(false);
    gameView.setVisible(true);
    try
    {
      model.pushWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }
  private void throwGame()
  {
    target=menu;
    menu.setVisible(true);
    gameView.setVisible(false);
    menu.goToMainMenu();
    try
    {
      model.haltWorld();
      model.clearTheWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }
  private void makeGameOver()
  {
    target=menu;
    menu.setVisible(true);
    gameView.setVisible(false);
    menu.goToGameOverMenu();
    try
    {
      model.haltWorld();
      model.clearTheWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }

  public void keyPressed(KeyEvent ev)
  {
    if(gameView==target)
      gameView.keyPressed(ev);
    else if(menu==target)
      menu.keyPressed(ev);
  }
  public void keyReleased(KeyEvent ev)
  {
    if(gameView==target)
      gameView.keyReleased(ev);
    else if(menu==target)
      menu.keyReleased(ev);
  }
  public void begin()
  {
    target=menu;
    gameView.setVisible(false);
    menu.setVisible(true);
    menu.goToMainMenu();
  }
  public int getHiScore()
  {
    return model.getHiScore();
  }
  public final EventProxy.EventListener eventListener=s->
  {
    switch(s)
    {
      case "gameOver":
        makeGameOver();
      default:;
    }
  };
  private void resize(double w,double h)
  {
    almostRoot.setScaleY(h/600.0);
    almostRoot.setScaleX(h/600.0);
    almostRoot.setPrefWidth(800.0*w/h);
    almostRoot.setPrefHeight(600.0);
    menu.resize(w,h);
    gameView.resize(w,h);
  }
  private void drawGame(Canvas c)
    throws GameException
  {
    Renderer.draw(c.getGraphicsContext2D(),model,c.getWidth(),c.getHeight());
  }
  private void gameControl(String k,String v)
  {
    model.getControlProxy().set(k,v);
  }

}
