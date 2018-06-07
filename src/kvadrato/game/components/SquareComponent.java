package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.other.BackgroundColor;

public class SquareComponent extends Component
{

  private BackgroundColor color;
  private BackgroundColor colorNew;

  public SquareComponent()
  {
    color=BackgroundColor.WHITE;
    colorNew=null;
  }
  public BackgroundColor getColor()
  {
    return color;
  }
  public void setColor(BackgroundColor c)
  {
    colorNew=c;
  }



  public void fix(){}
  public void doThings(){}
  public void update()
  {
    color=colorNew;
    colorNew=null;
  }
}
