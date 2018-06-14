package kvadrato.gui;

import java.lang.reflect.*;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.Canvas;
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
  enum State
  {
    FIRST_SCREEN,
    MENU,
    RETURN_TO_FIRST_SCREEN,
    STARTING_THE_GAME,
    THE_GAME,
    THE_GAME_PAUSE,
    SETTINGS,
    HELP,
    RESUME_GAME,
    GAME_OVER,
  }
  State state;
  private Model model;
  private Stage window;
  long lastAnimationUpdate=0;
  double stateClock;

  @FXML
  private StackPane theRoot;

  @FXML
  private StackPane almostRoot;

  //@FXML private Menu menu;

  //@FXML private GameView gameView;

//  private Canvas gameCanvas;





  private AnimationTimer animationTimer;

  /**
   * Ta zmienna wskazuje, co jest teraz zaznaczone, czy tam na czym jest myszka.
   */
  private Object theTarget;

  public MainController()
  {}
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
    System.err.println("WQEEWWQEQWEQWE");
    goToState(State.FIRST_SCREEN);
    animationTimer=new AnimationTimer()
    {
      public void handle(long now)
      {
        long diffNanos;
        double diff;
        double w=theRoot.getWidth();
        double h=theRoot.getHeight();
        almostRoot.setScaleY(h/600.0);
        almostRoot.setScaleX(h/600.0);
        //almostRoot.setPrefWidth(800.0*w/h);
        //almostRoot.setPrefHeight(600.0);

        //gameCanvas.setWidth(800.0);
        //gameCanvas.setHeight(600.0);


        /*try
        {
          Renderer.draw(gameCanvas.getGraphicsContext2D(),model);
        }
        catch(GameException exc)
        {
          System.out.println
            ("Próbowano narysować świat, ale świat dał wyjątek");
          exc.printStackTrace(System.out);
        }*/
        if(lastAnimationUpdate<=0)
        {
          lastAnimationUpdate=now;
        }
        diffNanos=now-lastAnimationUpdate;
        diff=(double)(diffNanos)/1000000000.0;
        //doStateWork(diff);
        //lastAnimationUpdate=now;
      //  gameView.animate(diff);
    //    menu.animate(diff);
      }
    };
  }
  /**
   * Następna inicjalizacja, ale taka później.
   */
  public void anotherInitialization()
  {
    window.setOnCloseRequest(e->{e.consume();closeRequest();});
    theRoot.getScene().setOnKeyPressed(e->keyPressed(e));
    theRoot.getScene().setOnKeyReleased(e->keyReleased(e));
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
   * Funkcja do ustawiania stanu, w którym jest menu.
   */
  // Jest ważna.
  void goToState(State state)
  {
    this.state=state;
    switch(state)
    {
      case FIRST_SCREEN:
        //mainMenu.setOpacity(1.);
        //mainMenu.setScaleX(1.);
        //mainMenu.setScaleY(1.);
        //menu.setVisible(true);
        //gameView.setVisible(false);
        break;
      case MENU:
        break;
      case STARTING_THE_GAME:
        //gameView.setVisible(true);
        startNewGame();
        break;
      case THE_GAME:
        break;
      case THE_GAME_PAUSE:
        //menu.setVisible(true);
        pauseGame();
        break;
      case RESUME_GAME:
        resumeGame();
        goToState(State.THE_GAME);
        break;
      case GAME_OVER:
        pauseGame();
        goToState(State.FIRST_SCREEN);
        break;
    }
    stateClock=0.0;
  }
  /**
   * To jest funkcja, która robi rzeczy odpowiednie dla każdego stanu w menu.
   */
  void doStateWork(double time)
  {
    stateClock+=time;
    double x,y;
  }
  /**
   * Ta funkcja wywołuje zaczęcie nowej gry w modelu.
   */
  void startNewGame()
  {
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
    try
    {
      model.haltWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }
  void resumeGame()
  {
    try
    {
      model.pushWorld();
    }
    catch(GameException exc)
    {
      exc.printStackTrace(System.err);
    }
  }


  //////
  //
  //
  //     Tutaj są funkcje wywoływane przez klikanie na różne rzeczy
  //     i najeżdżanie i tak dalej.
  //
  //
  //////

  // bigSquare
  public void bigSquareEntered()
  {
    //theTarget=bigSquare;
  }
  public void bigSquareExited()
  {
    if(state==State.FIRST_SCREEN)
    theTarget=null;
  }
  public void bigSquareClicked()
  {
    if(state==State.FIRST_SCREEN)
    {
      goToState(State.MENU);
    }
    else if(state==State.MENU)
    {
      goToState(State.STARTING_THE_GAME);
    }
  }

  //settingsSquare
  public void settingsSquareEntered()
  {
    //theTarget=settingsSquare;
  }
  public void settingsSquareExited()
  {
  }
  public void settingsSquareClicked()
  {
    System.out.println("To na razie nic nie robi!");
  }

  //helpSquare
  public void helpSquareEntered()
  {
    //theTarget=helpSquare;
  }
  public void helpSquareExited()
  {
    return;
  }
  public void helpSquareClicked()
  {
    System.out.println("To na razie nic nie robi!");
  }

  //quitSquare
  public void quitSquareEntered()
  {
    //theTarget=quitSquare;
  }
  public void quitSquareExited()
  {
    return;
  }
  public void quitSquareClicked()
  {
    closeRequest();
  }


  public void pauseRestartEntered()
  {
    //theTarget=pauseRestart;
  }
  public void pauseRestartExited()
  {
  }
  public void pauseRestartClicked()
  {
  }


  public void pauseResumeEntered()
  {
    //theTarget=pauseResume;
  }
  public void pauseResumeExited()
  {
  }
  public void pauseResumeClicked()
  {
  }


  public void pauseQuitEntered()
  {
    //theTarget=pauseQuit;
  }
  public void pauseQuitExited()
  {
  }
  public void pauseQuitClicked()
  {
  }



  public void keyPressed(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case ESCAPE:
        if(state==State.FIRST_SCREEN)
        {
          goToState(State.MENU);
        }
        else if(state==State.MENU)
        {
      //    theTarget=quitSquare;
        }
        else if(state==State.THE_GAME||state==State.STARTING_THE_GAME)
        {
          goToState(State.THE_GAME_PAUSE);
        }
        else if(state==State.THE_GAME_PAUSE)
        {
          goToState(State.RESUME_GAME);
        }
        break;
      case ENTER:
        if(state==State.FIRST_SCREEN||
           state==State.MENU)
        {
        //  if(theTarget==quitSquare)
        //      quitSquareClicked();
        }
        break;
      case UP:
        model.getControlProxy().set("up","q");
        break;
      case RIGHT:
        model.getControlProxy().set("right","q");
        break;
      case DOWN:
        model.getControlProxy().set("down","q");
        break;
      case LEFT:
        model.getControlProxy().set("left","q");
        break;
      case Q:
        model.getControlProxy().set("color","q");
        break;
      case W:
        model.getControlProxy().set("color","w");
        break;
      case E:
        model.getControlProxy().set("color","e");
        break;
      case R:
        model.getControlProxy().set("color","r");
        break;
      case A:
        model.getControlProxy().set("color","a");
        break;
      case S:
        model.getControlProxy().set("color","s");
        break;
      case D:
        model.getControlProxy().set("color","d");
        break;
      case F:
        model.getControlProxy().set("color","f");
        break;
    }
  }
  public void keyReleased(KeyEvent ev)
  {
    switch(ev.getCode())
    {
      case UP:
        model.getControlProxy().set("up",null);
        break;
      case RIGHT:
        model.getControlProxy().set("right",null);
        break;
      case DOWN:
        model.getControlProxy().set("down",null);
        break;
      case LEFT:
        model.getControlProxy().set("left",null);
        break;
      case Q:
        if(model.getControlProxy().get("color").equals("q"))
          model.getControlProxy().set("color","");
        break;
      case W:
        if(model.getControlProxy().get("color").equals("w"))
          model.getControlProxy().set("color","");
        break;
      case E:
        if(model.getControlProxy().get("color").equals("e"))
          model.getControlProxy().set("color","");
        break;
      case R:
        if(model.getControlProxy().get("color").equals("r"))
          model.getControlProxy().set("color","");
        break;
      case A:
        if(model.getControlProxy().get("color").equals("a"))
          model.getControlProxy().set("color","");
        break;
      case S:
        if(model.getControlProxy().get("color").equals("s"))
          model.getControlProxy().set("color","");
        break;
      case D:
        if(model.getControlProxy().get("color").equals("d"))
          model.getControlProxy().set("color","");
        break;
      case F:
        if(model.getControlProxy().get("color").equals("f"))
          model.getControlProxy().set("color","");
        break;
    }
  }
  public final EventProxy.EventListener eventListener=s->
  {
    switch(s)
    {
      case "gameOver":
        goToState(State.GAME_OVER);
      default:;
    }
  };
  private final ChangeListener<Number> stageSizeListener=(observable,o,n)->
  {

  };
}
