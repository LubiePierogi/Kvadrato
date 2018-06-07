package kvadrato;
import java.io.IOException;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import java.net.URL;
import java.util.ResourceBundle;
import java.lang.Math;
import javafx.fxml.FXML;
import javafx.scene.Group;
import java.lang.reflect.*;
import kvadrato.game.GameException;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.Canvas;

/*
import javafx.embed.swing.SwingNode;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import java.awt.BorderLayout;
*/

public class GUIController implements Initializable
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
  }
  State state;
  private Model model;
  private Stage window;
  long lastAnimationUpdate=0;
  double stateClock;

  public StackPane theRoot;

  public StackPane almostRoot;

  public StackPane mainMenu;

/*
  public SwingNode backgroundSwing;

  public SwingOpenGLCanvas oglCanvas;
*/

  public Group bigSquare;
  //public double bigSquareTranslateTarget;
  //public double bigSquareScaleTarget;

  public Group settingsSquare;
  //public double settingsSquareTranslateTarget;

  public Group helpSquare;
  //public double helpSquareTranslateTarget;

  public Group quitSquare;
  //public double quitSquareTranslateTarget;

  public Canvas gameCanvas;

  AnimationTimer animationTimer;

  /**
   * Ta zmienna wskazuje, co jest teraz zaznaczone, czy tam na czym jest myszka.
   */
  public Object theTarget;

  public GUIController()
  {
  }
  void setModel(Model model)
  {
    this.model=model;
  }
  void setWindow(Stage window)
  {
    this.window=window;
  }
  /**
   * Inicjalizacja niektórych rzeczy wywoływana przez JavaFX po wczytaniu FXML.
   */
  public void initialize(URL location,ResourceBundle resources)
  {
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

        gameCanvas.setWidth(800.0);
        gameCanvas.setHeight(600.0);


        Renderer.draw(gameCanvas.getGraphicsContext2D(),model);


        if(lastAnimationUpdate<=0)
        {
          lastAnimationUpdate=now;
          return;
        }
        diffNanos=now-lastAnimationUpdate;
        diff=(double)(diffNanos)/1000000000.0;
        doStateWork(diff);
        lastAnimationUpdate=now;
      }
    };
    /*oglCanvas=new SwingOpenGLCanvas();
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {

        JComponent jc=new JRootPane();
        jc.add(oglCanvas.getGlCanvas(),BorderLayout.CENTER);
        backgroundSwing.setContent(jc);

      }
    });*/
    //theRoot.requestFocus();
  }
  /**
   * Następna inicjalizacja, ale taka później.
   */
  void anotherInitialization()
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
        settingsSquare.setVisible(false);
        helpSquare.setVisible(false);
        quitSquare.setVisible(false);
        break;
      case MENU:
        settingsSquare.setVisible(true);
        helpSquare.setVisible(true);
        quitSquare.setVisible(true);
        break;
      case STARTING_THE_GAME:
        startNewGame();
        break;
      case THE_GAME:
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
    switch(this.state)
    {
      case FIRST_SCREEN:
        {
          y=theTarget==bigSquare?1.125:1.0;
          x=bigSquare.getScaleX();
          makeInterpolation(bigSquare,y,time,"scaleX","scaleY");
          makeInterpolation(bigSquare,0.0,time,"translateY");
          /*
          // Duży kwadrat - wielkość
          y=calculateInterpolation
            (x,y,time);
          bigSquare.setScaleX(y);
          bigSquare.setScaleY(y);

          // Duży kwadrat - miejsce
          y=0.0;
          x=bigSquare.getTranslateY();
          bigSquare.setTranslateY(calculateInterpolation(x,y,time));
          */
        }
        break;
      case MENU:
        {
          y=theTarget==bigSquare?1.0625:1.0;
          makeInterpolation(bigSquare,y,time,"scaleX","scaleY");
          makeInterpolation(bigSquare,-60.0,time,"translateY");
        }
        {
          // settingsSquare
          y=theTarget==settingsSquare?180.0:150;
          makeInterpolation(settingsSquare,y,time,"translateY");

          // helpSquare
          y=theTarget==helpSquare?180.0:150;
          makeInterpolation(helpSquare,y,time,"translateY");

          // quitSquare
          y=theTarget==quitSquare?180.0:150;
          makeInterpolation(quitSquare,y,time,"translateY");
          /*
          x=quitSquare.getTranslateY();
          y=calculateInterpolation(x,y,time);
          quitSquare.setTranslateY(y);
          */
        }
        break;
      case STARTING_THE_GAME:
        {
          x=mainMenu.getScaleX();
          y=2.7;
          y=calculateInterpolation(x,y,time);
          mainMenu.setScaleX(y);
          mainMenu.setScaleY(y);

          x=mainMenu.getOpacity();
          y=0.0;
          y=calculateInterpolation(x,y,time);
          mainMenu.setOpacity(y);
          if(y==0.0)
          {
            mainMenu.setVisible(false);
            goToState(State.THE_GAME);
          }
        }
        break;
      case THE_GAME:
        break;
    }
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
      System.out.println("Tutaj jest");
    }
    catch(GameException exc)
    {
      System.out.println("ojhjuijkkjioi");
    }
  }
  /**
   * To jest funkcja używana przy przesuwaniu elementów w czasie.
   */
  public static double
    calculateInterpolation(double now,double target,double time)
  {
    if(now!=target)
    {
      double a=target-now;
      double b=Math.signum(a);
      double neww=now+time*(a*9.0+0.24*b);
      if(b*(neww-target)>0)
        neww=target;
      return neww;
    }
    return now;
  }
  /**
   * Funkcja do zmieniania warotści jakichś rzeczy z interpolacją.
   */
  public void makeInterpolation
    (Object thing,double target,double time,String ... numbers)
  {
    try
    {
      Class c=thing.getClass();
      Class[] params=new Class[0];
      Method meth=c.getMethod
      (
        "get"+
        numbers[0].substring(0,1).toUpperCase()+
        numbers[0].substring(1),
        params
      );
      Object[] arglist=new Object[0];
      Object ret=meth.invoke(thing,arglist);
      Double retv=(Double)ret;
      // Do wyliczenia początku brany jest tylko pierszy element z tablicy.
      double x=Double.valueOf(retv);
      double y=calculateInterpolation(x,target,time);
      for(int i=0;i<numbers.length;++i)
      {
        params=new Class[1];
        params[0]=Double.TYPE;
        meth=c.getMethod
        (
          "set"+
          numbers[i].substring(0,1).toUpperCase()+
          numbers[i].substring(1),
          params
        );
        arglist=new Object[1];
        arglist[0]=new Double(y);
        ret=meth.invoke(thing,arglist);
      }
    }
    catch(NoSuchMethodException exc){}
    catch(IllegalAccessException exc){}
    catch(InvocationTargetException exc){}
    finally
    {

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
    theTarget=bigSquare;
  }
  public void bigSquareExited()
  {
    if(state==State.FIRST_SCREEN)
    theTarget=null;
  }
  public void bigSquareClicked()
  {
    System.out.println("zxc");
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
    theTarget=settingsSquare;
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
    theTarget=helpSquare;
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
    theTarget=quitSquare;
  }
  public void quitSquareExited()
  {
    return;
  }
  public void quitSquareClicked()
  {
    closeRequest();
  }
  public void keyPressed(KeyEvent ev)
  {
    System.out.println("Key pressed.");
    switch(ev.getCode())
    {
      case ESCAPE:
        if(state==State.FIRST_SCREEN)
        {
          goToState(State.MENU);
          break;
        }
        if(state==State.MENU)
        {
          theTarget=quitSquare;
        }
        break;
      case ENTER:
        if(state==State.FIRST_SCREEN||
           state==State.MENU)
        {
          if(theTarget==quitSquare)
            quitSquareClicked();
        }
    }
  }
  public void keyReleased(KeyEvent ev)
  {
    System.out.println("Key released.");
  }
}
