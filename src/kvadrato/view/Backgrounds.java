package kvadrato.view;

import javafx.scene.paint.Color;

import kvadrato.game.other.BgColor;

public final class Backgrounds
{
  private Backgrounds(){}
  public static Color bgToBackground(BgColor bg)
  {
    if(bg==null)
    {
      return Color.WHITE;
    }
    switch(bg)
    {
      case WHITE:return new Color(0.9,0.9,0.9,1.0);
      case BLUE:return new Color(0.0,0.1,0.92,1.0);
      case GREEN:return new Color(0.08,0.96,0.03,1.0);
      case YELLOW:return new Color(0.9,0.9,0.03,1.0);
      case CYAN:return new Color(0.3,0.88,0.92,1.0);
      case PURPLE:return new Color(0.80,0.03,0.83,1.0);
      case RED:return new Color(0.89,0.0,0.0,1.0);
      case BROWN:return new Color(0.56,0.3,0.01,1.0);
      case ORANGE:return new Color(0.92,0.45,0.01,1.0);
      default:
        assert(false); // xD
        return null;
    }
  }
  public static Color bgToSquare(BgColor bg)
  {
    if(bg==null)
    {
      return new Color(.1,.2,.3,1.);
    }
    switch(bg)
    {
      case WHITE:return new Color(.3,.3,.3,1.);
      case BLUE:return new Color(.4,.2,0.,1.);
      case GREEN:return new Color(.36,0.,.36,1.);
      case YELLOW:return new Color(.33,.18,0.,1.);
      case CYAN:return new Color(.0,.25,.0,1.);
      case PURPLE:return new Color(0.92,0.88,0.92,1.);
      case RED:return new Color(0.1,0.1,0.1,1.);
      case BROWN:return new Color(0.5,0.5,0.5,1.);
      case ORANGE:return new Color(0.93,0.93,0.04,1.);
      default:
        assert(false);
        return null;
    }
  }
}
