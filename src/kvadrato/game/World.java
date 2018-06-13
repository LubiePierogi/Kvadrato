package kvadrato.game;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;
import kvadrato.game.Entity;
import kvadrato.game.WorldAccess;
import kvadrato.game.Component;
import kvadrato.game.other.BgColor;
import kvadrato.game.components.Collider;
import kvadrato.game.components.Camera;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.collision.*;
/**
 * Chyba najbardziej ważna klasa w programie.
 * Jest w niej cały stan świata gry.
 */
public final class World
{
  /**
   * Domyślna ilość odświeżeń świata w sekundzie przy szybkości 1.
   */
  public final static int DefaultTickrate=120;
  /**
   * Najmniejszy możliwy do ustawienia tickrate.
   */
  public final static int MinTickrate=1;
  /**
   * W tym są wszystkie jednostki na świecie.
   */
  ArrayList<Entity> ents;
  /**
   * Ilość odświeżeń świata na sekundę przy szybkości ustawionej na jeden.
   */
  private int tickrate;
  /**
   * Mnożnik szybkości świata, działa tak, że przy wpisaniu 2.0 cały świat
   * będzie działał dwa razy szybciej, no chyba że komputer nie da rady tego tak
   * szybko zrobić.
   */
  private double speed;
  /**
   * Wyliczana przy zmianie tickrate'u i szybkości ilość nanosekund, którą ma
   * czekać wątek, zanim zrobi następny krok.
   */
  private int tickNanosReal;
  /**
   * Wyliczana przy zmianie tickrate'u i szybkości ilość nanosekund, która
   * mija co krok w świecie gry.
   */
  private int tickNanosWorld;
  /**
   * Ilość kroków, którą ma zrobić na świecie wątek w pętli, zanim będzie n
   * cokolwiek czekać.
   */
  private int immediatelyTicks;
  /**
   * Muteks od świata, jest zajmowany przy chyba prawie każdej funkcji
   * wywoływanej z zewnątrz.
   */
  private Lock worldLock;
  /**
   * Wątek, który robi wszystko na świecie.
   */
  private WorldThread thread;
  /**
   * Zmienna, która jest ma prawdę tylko, gdzy wątek ma być zamknięty, finalizer
   * ustawia ją.
   */
  private boolean ender;
  /**
   * zmienna przechowująca numer, który dostanie następna jednostka.
   */
  private int nextEntId;
  /**
   * Najzwyklejszy i jedyny potrzebny konstruktor.
   */
  public World()
  {
    ender=false;
    worldLock=new ReentrantLock();

    thread=null;
    nextEntId=1;

    ents=new ArrayList<Entity>();

  }
  public void init() throws GameException
  {
    worldLock.lock();
    if(thread!=null)
    {
      throw new GameException();
    }
    try
    {
      // Ustawiamy ilość odświeżeń świata przez jedną sekundę.
      tickrate=DefaultTickrate;

      // Najpierw świat jest zatrzymany, więc ustawiamy jego szybkość na zero.
      speed=0.0;

      // Ustawiamy, że następna jednostka będzie miała numer 1.
      nextEntId=1;

      // Obliczenie czasu czekania na później.
      updateTickNanos();

      // Nie skaczemy od razu.
      immediatelyTicks=0;

      // Tworzymy wątek, który zajmuje się tym światem.
      thread=new WorldThread(this);

    }
    finally
    {
      worldLock.unlock();
    }
  }
  void lock()
  {
    worldLock.lock();
  }
  void unlock()
  {
    worldLock.unlock();
  }
  boolean decImmediatelyTicks()
  {
    if(immediatelyTicks>0)
    {
      --immediatelyTicks;
      return true;
    }
    return false;
  }
  public int[] doWorkAndReturn(WorldWorkerWhichReturns func)
    throws GameException
  {
    worldLock.lock();
    try
    {
      return func.call(new WorldAccess(this));
    }
    finally
    {
      worldLock.unlock();
    }
  }
  public void doWork(WorldWorker func)
    throws GameException
  {
    worldLock.lock();
    try
    {
      func.call(new WorldAccess(this));
    }
    finally
    {
      worldLock.unlock();
    }
  }
  public ViewOfWorld getView(int entId,double dist)
    throws GameException
  {
    worldLock.lock();
    try
    {
      return new ViewOfWorld(this,entId,dist);
    }
    finally
    {
      worldLock.unlock();
    }
  }
  /**
   * Funkcja, która wylicza czas, jaki ma być czekany co krok i jaki czas mija
   * co krok na świecie.
   */
  private void updateTickNanos()
  {
    tickNanosWorld=1000000000/tickrate;

    // Gdy prędkość jest zerem, to wyjdzie bezużyteczna liczba, ale i tak wtedy
    // nie jest używana, więc to nie przeszkadza.
    tickNanosReal=(int)(1000000000/speed/tickrate);
  }
  boolean getEnder()
  {
    return ender;
  }
  /**
   * Funkcja do zamykania wątku. Jeśli rzuci wyjątek GameException, to znaczy,
   * że wątek jest zamknięty.
   */
  public void close() throws GameException
  {
    worldLock.lock();
    try
    {
      if(thread==null)
      {
        throw new GameException();
      }
      clear();
      thread.halt();
      thread.shutdown();
    }
    finally
    {
      worldLock.unlock();
    }
  }
  /**
   * To wywołuje GARBAGE COLLECTOR, jak kasuje świat. Jest to taka funkcja
   * do kończenia wątku i tak dalej.
   */
  protected void finalize()
  {
    try
    {
      close();
    }
    catch(GameException exc)
    {
      // To, że jest wyjątek nie zmienia tego, że świat jest zamknięty, możemy
      // nic z tym nie robić.
    }
  }
  /**
   * Ustawia ilość zmian świata na sekundę.
   * @param tr ilość do ustawienia
   */
  void setTickrate(int tr)
   throws GameException
  {
    if(tr<MinTickrate)
      throw new GameException();
    tickrate=tr;
    updateTickNanos();
    thread.reschedule();
  }
  /**
   * Ustawia mnożnik szybkości świata, ogólnie to jeśli jest zero, to mamy
   * zatrzymany świat.
   * @param s mnożnik
   */
  void setSpeed(double s)
   throws GameException
  {
    if(s<0.0)
      throw new GameException();
    speed=s;
    updateTickNanos();
    thread.reschedule();
  }
  private Entity createEntity()
  {
    Entity ent=new Entity();
    ents.add(ent);
    ent.world=this;
    ent.id=nextEntId;
    ++nextEntId;
    return ent;
  }
  Entity spawn(String name) throws GameException
  {
    try
    {
      Class c=Class.forName("kvadrato.game.prefabs."+name);
      Prefab x=(Prefab)c.newInstance();
      Entity ent=createEntity();
      ent.prefabName=name;
      x.makeEntity(ent);
      return ent;
    }
    catch(ClassNotFoundException|InstantiationException|
      IllegalAccessException exc)
    {
      throw new GameException(exc);
    }
  }
  /**
   * Zwraca jednostkę o podanym id.
   */
  Entity getEntById(int q)
  {
    for(Entity x:ents)
      if(x.getId()==q)
        return x;
    return null;
  }
  Entity getFirstEntByPrefab(String q)
  {
    for(Entity x:ents)
      if(x.getName().equals(q))
        return x;
    return null;
  }
  /**
   * Usuwa jednostkę ze świata, tylko ze świata.
   * @param ent należąca do danego świata jednostka
   */
  void removeEntity(Entity ent)
    throws GameException
  {
    if(ent.world!=this)
    {
      throw new GameException();
    }
    ent.remove();
    return;
  }
  /**
   * Ta funkcja usuwa wszystkie jednostki na świecie.
   */
  void clear()
  {
    for(int i=0;i<ents.size();++i)
    {
      ents.get(i).remove();
    }
    updateWorld();
  }
  /**
   * Ta funkcja robi jeden krok na świecie, nic skomplikowanego.
   */
  void oneTick()
  {
    fixWorld();
    try
    {
      computeCollisions();
    }
    catch(GameException exc){}
    doThingsWorld();
    updateWorld();
  }
  /**
   */
  void doImmediatelyTicks(int x) throws GameException
  {
    if(x<0)
      throw new GameException();
    immediatelyTicks+=x;
    thread.reschedule();
  }
  /**
   */
  private void doThingsWorld()
  {
    Entity ent;
    for(int i=0;i<ents.size();++i)
    {
      ent=ents.get(i);
      ent.doThings();
    }
  }
  /**
   */
  private void fixWorld()
  {
    Entity ent;
    for(int i=0;i<ents.size();++i)
    {
      ent=ents.get(i);
      ent.fix();
    }
  }
  /**
   * Ta funkcja zamienia stan wszystkich jednostek na nowy.
   */
  void updateWorld()
  {
    Entity ent;
    for(int i=0;i<ents.size();++i)
    {
      ent=ents.get(i);
      if(ent.isRemoved())
      {
        ent.world=null;
        ents.remove(i);
        --i;
        continue;
      }
      ent.update();
    }
  }
  /**
   * Ta funkcja zwraca ile czasu mija co krok. Colowo nie nazywa się
   * getTickNanosWorld, bo to nie jest getter, a jest ładnie, gdy gettery
   * są publiczne.
   */
  int getDeltaTime()
  {
    return tickNanosWorld;
  }
  /**
   * Czas czekania co krok. Celowo nie nazywa się getTickNanosReal, bo nie jest
   * to publiczny getter.
   */
  int getWaitTime()
  {
    return tickNanosReal;
  }
  /**
   * Ta funkcja zwraca, czy świat nie jest zatrzymany
   */
  boolean runs()
  {
    return speed!=0.0;
  }
  /**
   * Ta funkcja oblicza kolizje wszystkich możliwych obiektów na świecie
   * i wpisuje je do tych obiektów.
   */
  private void computeCollisions()
    throws GameException
  {
    //System.err.println("WQQERE");
    //if(ents==null)System.err.println("$#@$#@");
    int theEnd1;
    //System.err.print("Xzxc");
    int theEnd2;
    //System.err.print("zxc");
    Entity ent1;
    Entity ent2;
    Collider col1;
    Collider col2;
    BakedShape sh1;
    BakedShape sh2;
    Physics ph1;
    Physics ph2;
    CollisionOccurrence collision;
    theEnd1=ents.size()-1;
    theEnd2=ents.size();
    System.err.println("dfgdfg");
    for(int i=0;i<theEnd1;++i)
    {
      ent1=ents.get(i);
      col1=(Collider)ent1.getComponent("Collider");
      if(col1==null)
        continue;
      ph1=(Physics)ent1.getComponent("Physics");
      for(int j=i+1;j<theEnd2;++j)
      {
        //System.out.print("qweqw");
        ent2=ents.get(j);
        col2=(Collider)ent2.getComponent("Collider");
        if(col2==null)
          continue;
        ph2=(Physics)ent2.getComponent("Physics");
        // Teraz wiemy, że mamy parę dwóch możliwych do zderzenia obiektów.
        ElementaryShape s1=col1.getShape();
        ElementaryShape s2=col2.getShape();
        if(s1==null||s2==null)
          continue;
        sh1=new BakedShape(s1,new Vec2drs(ph1.getPlace()));
        sh2=new BakedShape(s2,new Vec2drs(ph2.getPlace()));
        collision=CollisionComputer.computeFromBaked(sh1,sh2);
        if(collision!=null)
        {
          col1.addCollision(ent2,collision);
          col2.addCollision(ent1,collision);
        }
      }
    }
  }
}
