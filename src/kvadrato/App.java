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
import kvadrato.gui.MainController;

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
      System.err.println("Nie udało się włączyć gry.");
    }
    finally
    {
      model.close();
    }
  }
  @Override
  public void start(Stage mainStage) throws IOException,GameException
  {
    model.init();
    win=mainStage;
    win.setTitle("Kvadrato");
    MainController controller=new MainController();
    FXMLLoader loader=new
      FXMLLoader(getClass().getResource("/content/view/gui.fxml"));
    loader.setController(controller);
    Parent root=loader.load();
    controller.setModel(model);
    controller.setWindow(win);
    win.setScene(new Scene(root));
    controller.anotherInitialization();
    win.show();
  }
}
