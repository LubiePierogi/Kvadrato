package kvadrato;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import kvadrato.utils.GameException;

//
public class App extends Application
{
  static Model model;
  static Stage win;
  public static void main(String[] args)
  {
    try
    {
      model=new Model();
      launch(args);
    }
    catch(GameException exc)
    {
      //System.out.println("Nie udało się włączyć gry.");
    }
    finally
    {
      model.close();
    }
  }
  @Override
  public void start(Stage mainStage) throws IOException
  {
    try
    {
      model.init();
    }
    catch(GameException exc)
    {}
    win=mainStage;
    win.setTitle("Kvadrato");
    FXMLLoader loader=new FXMLLoader(getClass().getResource("view/gui.fxml"));
    Parent root=loader.load();
    GUIController controller=loader.<GUIController>getController();
    controller.setModel(model);
    controller.setWindow(win);
    win.setScene(new Scene(root));
    controller.anotherInitialization();
    win.show();
  }
}
