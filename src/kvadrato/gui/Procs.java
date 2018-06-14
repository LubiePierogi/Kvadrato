package kvadrato.gui;

import java.lang.reflect.*;

public class Procs
{
  private Procs(){}
  /**
   * To jest funkcja używana przy przesuwaniu elementów w czasie.
   */
  public static double
    calculateInterpolation(double now,double target,double time)
  {
    if(now!=target)
    {
      double a=target-now;
      double b=Math.signum(a);
      double next=now+time*(a*9.0+0.24*b);
      if(b*(next-target)>0)
        next=target;
      return next;
    }
    return now;
  }

  /**
   * Funkcja do zmieniania warotści jakichś rzeczy z interpolacją.
   */
  public static void changeWithInterpolation
    (Object thing,double target,double time,String ... numbers)
  {
    try
    {
      Class c=thing.getClass();
      Class[] params=new Class[0];
      Method meth=c.getMethod
      (
        "get"+
        numbers[0].substring(0,1).toUpperCase()+
        numbers[0].substring(1),
        params
      );
      Object[] arglist=new Object[0];
      Object ret=meth.invoke(thing,arglist);
      Double retv=(Double)ret;
      // Do wyliczenia początku brany jest tylko pierszy element z tablicy.
      double x=Double.valueOf(retv);
      double y=calculateInterpolation(x,target,time);
      for(int i=0;i<numbers.length;++i)
      {
        params=new Class[1];
        params[0]=Double.TYPE;
        meth=c.getMethod
        (
          "set"+
          numbers[i].substring(0,1).toUpperCase()+
          numbers[i].substring(1),
          params
        );
        arglist=new Object[1];
        arglist[0]=new Double(y);
        ret=meth.invoke(thing,arglist);
      }
    }
    catch(NoSuchMethodException|IllegalAccessException|InvocationTargetException
     exc){}
  }

  public static void changeImmediately
    (Object thing,double target,String ... numbers)
  {
    try
    {
      Class c=thing.getClass();
      Class[] params;
      Method meth;
      Object[] arglist;
      Object ret;
      for(int i=0;i<numbers.length;++i)
      {
        params=new Class[1];
        params[0]=Double.TYPE;
        meth=c.getMethod
        (
          "set"+
          numbers[i].substring(0,1).toUpperCase()+
          numbers[i].substring(1),
          params
        );
        arglist=new Object[1];
        arglist[0]=new Double(target);
        ret=meth.invoke(thing,arglist);
      }
    }
    catch(NoSuchMethodException|IllegalAccessException|InvocationTargetException
     exc){}
  }
}
