package kvadrato.game.components;

import java.util.Objects;

import kvadrato.game.Component;
import kvadrato.game.other.BgColor;

public class BgColorComponent extends Component
{
  private BgColor color;
  private BgColor colorNew;

  public BgColorComponent()
  {
    color=BgColor.WHITE;
    colorNew=null;
  }
  public BgColor getColor()
  {
    return color;
  }
  public void setColor(BgColor c)
  {
    colorNew=Objects.requireNonNull(c);
  }

  public void fix(){}
  public void doThings(){}
  public void update()
  {
    if(colorNew!=null)
      color=colorNew;
    colorNew=null;
  }
}
