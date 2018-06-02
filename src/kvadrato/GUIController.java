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

public class GUIController implements Initializable
{
  enum State
  {
    FIRST_SCREEN,
    MENU,
    STARTING_THE_GAME,
    THE_GAME,
    SETTINGS,
    HELP,
  }
  State state;
  private Model model;
  private Stage window;
  long lastAnimationUpdate=0;
  double stateClock;
  double sizeMultiplier;

  public StackPane theRoot;

  public StackPane almostRoot;

  public Group bigSquare;
  public double bigSquareTranslateTarget;
  public double bigSquareScaleTarget;

  public Group settingsSquare;
  public double settingsSquareTranslateTarget;

  public Group helpSquare;
  public double helpSquareTranslateTarget;

  public Group quitSquare;
  public double quitSquareTranslateTarget;

  AnimationTimer animationTimer;

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
    bigSquareScaleTarget=1.0;
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
        almostRoot.setPrefWidth(800.0*w/h);
        almostRoot.setPrefHeight(600.0);
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
    animationTimer.start();
  }
  /**
   * Następna inicjalizacja, ale taka później.
   */
  void anotherInitialization()
  {
    window.setOnCloseRequest(e->{e.consume();closeRequest();});
  }
  /**
   * Funkcja zamykająca okno, a więc i program.
   */
  public void closeProgram()
  {
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
        sizeMultiplier=1.125;
        bigSquareTranslateTarget=0.0;
        settingsSquareTranslateTarget=0.0;
        helpSquareTranslateTarget=0.0;
        quitSquareTranslateTarget=0.0;
        break;
      case MENU:
        settingsSquare.setVisible(true);
        helpSquare.setVisible(true);
        quitSquare.setVisible(true);
        sizeMultiplier=1.0625;
        bigSquareScaleTarget=sizeMultiplier;
        bigSquareTranslateTarget=-60.0;
        settingsSquareTranslateTarget=150.0;
        helpSquareTranslateTarget=150.0;
        quitSquareTranslateTarget=150.0;
        break;
      case STARTING_THE_GAME:
        sizeMultiplier=3.0;
        bigSquareScaleTarget=sizeMultiplier;
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
    switch(this.state)
    {
      case FIRST_SCREEN:
      case MENU:
      case STARTING_THE_GAME:
        updateBigSquareThings(time);
        updateSettingsSquareThings(time);
        updateHelpSquareThings(time);
        updateQuitSquareThings(time);
        break;
    }
  }
  /**
   * To jest funkcja używana przy przesuwaniu elementów w czasie.
   */
  public static double
    calculateInterpolation(double now,double target,double time)
  {
    {
      if(now!=target)
      {
        double a=target-now;
        double b=Math.signum(a);
        double neww=now+time*(a*6.0+0.12*b);
        if(b*(neww-target)>0)
          neww=target;
        return neww;
      }
      return now;
    }
  }






  //////
  //
  //
  //     Tutaj są funkcje od przesuwania poszczególnych elementów na scenie.
  //
  //
  //////

  public void updateBigSquareThings(double time)
  {
    // Duży kwadrat - wielkość
    double neww=calculateInterpolation
      (bigSquare.getScaleX(),bigSquareScaleTarget,time);
    bigSquare.setScaleX(neww);
    bigSquare.setScaleY(neww);

    // Duży kwadrat - miejsce
    bigSquare.setTranslateY
    (
      calculateInterpolation
        (bigSquare.getTranslateY(),bigSquareTranslateTarget,time)
    );
  }
  public void updateSettingsSquareThings(double time)
  {
    double neww=calculateInterpolation
      (settingsSquare.getTranslateY(),settingsSquareTranslateTarget,time);
    settingsSquare.setTranslateY(neww);
  }
  public void updateHelpSquareThings(double time)
  {
    double neww=calculateInterpolation
      (helpSquare.getTranslateY(),helpSquareTranslateTarget,time);
    helpSquare.setTranslateY(neww);
  }
  public void updateQuitSquareThings(double time)
  {
    double neww=calculateInterpolation
      (quitSquare.getTranslateY(),quitSquareTranslateTarget,time);
    quitSquare.setTranslateY(neww);
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
    bigSquareScaleTarget=sizeMultiplier;
  }
  public void bigSquareExited()
  {
    //System.out.print("   "+theRoot.getHeight()+"   "+theRoot.heightProperty().getValue()+"\n");
    bigSquareScaleTarget=1.0;
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
    settingsSquareTranslateTarget+=20.0;
  }
  public void settingsSquareExited()
  {
    settingsSquareTranslateTarget-=20.0;
  }
  public void settingsSquareClicked()
  {
    System.out.println("To na razie nic nie robi!");
  }

  //helpSquare
  public void helpSquareEntered()
  {
    helpSquareTranslateTarget+=30.0;
  }
  public void helpSquareExited()
  {
    helpSquareTranslateTarget-=30.0;
  }
  public void helpSquareClicked()
  {
    System.out.println("To na razie nic nie robi!");
  }

  //quitSquare
  public void quitSquareEntered()
  {
    quitSquareTranslateTarget+=30.0;
  }
  public void quitSquareExited()
  {
    quitSquareTranslateTarget-=30.0;
  }
  public void quitSquareClicked()
  {
    closeRequest();
  }
}
