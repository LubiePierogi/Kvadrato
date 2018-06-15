package kvadrato.worldview;

import javafx.scene.paint.Color;

import kvadrato.game.other.BgColor;
import kvadrato.game.other.WallColor;

public final class Colors
{
  private Colors(){}
  public static Color bgToBackground(BgColor bg)
  {
    switch(bg!=null?bg:BgColor.WHITE)
    {
      default:
      case WHITE:return new Color (0.9  ,0.9  ,0.9  ,1.0  );
      case BLUE:return new Color  (0.08 ,0.18 ,0.91 ,1.0  );
      case GREEN:return new Color (0.18 ,0.77 ,0.10 ,1.0  );
      case YELLOW:return new Color(0.86 ,0.86 ,0.03 ,1.0  );
      case CYAN:return new Color  (0.24 ,0.84 ,0.90 ,1.0  );
      case PURPLE:return new Color(0.80 ,0.03 ,0.83 ,1.0  );
      case RED:return new Color   (0.89 ,0.0  ,0.0  ,1.0  );
      case BROWN:return new Color (0.56 ,0.3  ,0.01 ,1.0  );
      case ORANGE:return new Color(0.92 ,0.45 ,0.01 ,1.0  );
    }
  }
  public static Color bgToSquare(BgColor bg)
  {
    switch(bg!=null?bg:BgColor.WHITE)
    {
      default:
      case WHITE:return new Color (.3   ,.3   ,.3   ,1.   );
      case BLUE:return new Color  (.0   ,.01  ,0.32 ,1.   );
      case GREEN:return new Color (.55  ,0.93 ,.44  ,1.   );
      case YELLOW:return new Color(.99  ,.90  ,.70  ,1.   );
      case CYAN:return new Color  (.78  ,.95  ,.99  ,1.   );
      case PURPLE:return new Color(0.92 ,0.88 ,0.92 ,1.   );
      case RED:return new Color   (0.1  ,0.1  ,0.1  ,1.   );
      case BROWN:return new Color (0.5  ,0.5  ,0.5  ,1.   );
      case ORANGE:return new Color(0.96 ,0.93 ,0.04 ,1.   );
    }
  }
  public static Color bgToSquareTriangle(BgColor bg)
  {
    switch(bg!=null?bg:BgColor.WHITE)
    {
      default:
      case WHITE:return new Color (.8   ,.8   ,.8   ,1.   );
      case BLUE:return new Color  (.81  ,.82  ,.99  ,1.   );
      case GREEN:return new Color (.0   ,.23  ,.0   ,1.   );
      case YELLOW:return new Color(.0   ,.0   ,.0   ,1.   );
      case CYAN:return new Color  (.34  ,.75  ,.96  ,1.   );
      case PURPLE:return new Color(0.72 ,0.70 ,0.79 ,1.   );
      case RED:return new Color   (0.9  ,0.9  ,0.9  ,1.   );
      case BROWN:return new Color (0.63 ,0.63 ,0.63 ,1.   );
      case ORANGE:return new Color(0.96 ,0.96 ,0.90 ,1.   );
    }
  }
  public static Color bgToObstacle(BgColor bg)
  {
    switch(bg!=null?bg:BgColor.WHITE)
    {
      default:
      case WHITE:return new Color (0.7  ,0.7  ,0.7  ,1.0);
      case BLUE:return new Color  (0.0  ,0.08 ,0.85 ,1.0);
      case GREEN:return new Color (0.05 ,0.8  ,0.022,1.0);
      case YELLOW:return new Color(0.73 ,0.73 ,0.022,1.0);
      case CYAN:return new Color  (0.23 ,0.7  ,0.8  ,1.0);
      case PURPLE:return new Color(0.68 ,0.021,0.7  ,1.0);
      case RED:return new Color   (0.76 ,0.0  ,0.0  ,1.0);
      case BROWN:return new Color (0.4  ,0.21 ,0.0  ,1.0);
      case ORANGE:return new Color(0.8  ,0.4  ,0.0  ,1.0);
    }
  }
  public static Color getWallColor(WallColor q)
  {
    switch(q!=null?q:WallColor.INFINITE)
    {
      default:
      case INFINITE:return new Color(0.03 ,0.03 ,0.03 ,1.   );
      case MOVING:return new Color  (0.05 ,0.05 ,0.05 ,.7   );
    }
  }
}
