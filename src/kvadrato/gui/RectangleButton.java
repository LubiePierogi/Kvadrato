package kvadrato.gui;

import java.io.IOException;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class RectangleButton extends Rectangle
{
  private static final RadialGradient squareGradient
    =new RadialGradient
    (
      -51.34,
      1.,
      .7921348314606742,
      .5,
      1.,
      true,
      CycleMethod.NO_CYCLE,
      new Stop(0.,Color.rgb( 71,252,  0)),
      new Stop(0.,Color.rgb(208,248,  5))
    );
  public RectangleButton()
  {
    setFill(squareGradient);
    setWidth(100.0);
    setHeight(100.0);
    setStroke(new Color(0.,0.,0.,.5));
    setStrokeWidth(24.);
    setStrokeType(StrokeType.INSIDE);
  }

}
