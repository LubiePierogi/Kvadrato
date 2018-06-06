package kvadrato.game.components;

import kvadrato.game.Component;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import kvadrato.game.appearance.AppearanceElement;

class Appearance extends Component
{
  Appearance()
  {
    elements=new ArrayList<AppearanceElement>();
  }

  List<AppearanceElement> elements;

  void clear()
  {
    elements.clear();
  }

  void addElement(AppearanceElement e)
  {
    elements.add(e);
  }

  List<AppearanceElement> getElements()
  {
    return Collections.unmodifiableList(elements);
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
