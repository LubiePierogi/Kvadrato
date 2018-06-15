package kvadrato.game.components;

import kvadrato.game.Entity;
import kvadrato.game.Component;

public class Health extends Component
{
  private double current;
  private double currentNew;

  private double max;
  private double maxNew;

  private OnDeathFnType onDeathFn;
  public static interface OnDeathFnType
  {void call(Entity e);}

  private OverTimeFnType overTimeFn;
  public static interface OverTimeFnType
  {void call(Entity e);}

  public Health()
  {
    current=100.;
    currentNew=100.;
    max=100.;
    maxNew=100.;
    onDeathFn=null;
  }
  public boolean isAlive()
  {
    return current>0.0;
  }
  public void setOnDeathFn(OnDeathFnType func)
  {
    onDeathFn=func;
  }
  public void setOverTime(OverTimeFnType func)
  {
    overTimeFn=func;
  }
  public void change(double q)
  {
    currentNew+=q;
  }
  public void setMax(double q)
  {
    maxNew=q;
  }
  public void fullHeal()
  {
    currentNew=1./0.;
  }
  public double getMax()
  {
    return max;
  }
  public double getCurrent()
  {
    return current;
  }

  public void fix(){}
  public void doThings()
  {
    if(overTimeFn!=null)
      overTimeFn.call(getEntity());
  }
  public void update()
  {
    max=maxNew;
    boolean wasAlive=current>0.0;
    if(currentNew>max)
      currentNew=max;
    current=currentNew;
    if(current<=0.0&&wasAlive)
    {
      if(onDeathFn!=null)
        onDeathFn.call(getEntity());
    }
  }
}
