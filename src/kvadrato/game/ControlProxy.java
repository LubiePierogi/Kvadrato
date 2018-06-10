package kvadrato.game;

import java.util.Objects;
import java.util.Map;
import java.util.TreeMap;

public class ControlProxy
{
  private Map<String,String> q;
  public ControlProxy()
  {
    q=new TreeMap<String,String>();
  }
  public synchronized String get(String what)
  {
    String x=q.get(what);
    if(x==null)
      return "";
    return x;
  }
  public synchronized void set(String k,String v)
  {
    Objects.requireNonNull(k);
    if(v!=null&&v.equals(""))
      v=null;
    q.put(k,v);
  }
  public synchronized void clear()
  {
    q.clear();
  }
}
