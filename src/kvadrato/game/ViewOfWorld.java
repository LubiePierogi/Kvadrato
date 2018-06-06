package kvadrato.game;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import kvadrato.game.appearance.AppearanceElement;

public class ViewOfWorld
{
  Color backgroundColor;
  List<AppearanceElement> things;

  public Color getBackground()
  {
    return backgroundColor;
  }
  public List<AppearanceElement> getThings()
  {
    return Collections.unmodifiableList(things);
  }
  ViewOfWorld()
  {
    backgroundColor=new Color(255,255,255); // Domyślny kolor to biały.
    things=new ArrayList();
  }
  void setBackground(Color c)
  {
    if(c==null)
      return;
    backgroundColor=c;
  }
  void addElement(AppearanceElement e)
  {
    things.add(e);
  }
}
