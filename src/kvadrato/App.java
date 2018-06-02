package kvadrato;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


//
public class App extends Application
{
  static Model model;
  static Stage win;
  public static void main(String[] args)
  {
    launch(args);
  }
  @Override
  public void start(Stage mainStage) throws IOException
  {
    model=new Model();
    win=mainStage;
    win.setTitle("Kvadrato");
    FXMLLoader loader=new FXMLLoader(getClass().getResource("view/gui.fxml"));
    Parent root=loader.load();
    GUIController controller=loader.<GUIController>getController();
    controller.setModel(model);
    controller.setWindow(win);
    controller.anotherInitialization();
    win.setScene(new Scene(root));
    win.show();
  }
}
