package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.other.BackgroundColor;

public class SquareComponent extends Component
{
  public void fix(){}
  public void doThings(){}
  public void update(){}

  BackgroundColor color;


  SquareComponent()
  {
    color=BackgroundColor.WHITE;
  }
}
