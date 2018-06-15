package kvadrato.gui;

import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class RectangleText extends Text
{
  final static Font font=new Font(32.0);
  public RectangleText()
  {
    setFill(new Color(0.,.45,0.,1.));
    setFont(font);
  }
}
