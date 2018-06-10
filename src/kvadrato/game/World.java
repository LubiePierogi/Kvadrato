package kvadrato.game;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kvadrato.utils.GameException;
import kvadrato.game.Entity;
import kvadrato.game.WorldAccess;
import kvadrato.game.Component;
import kvadrato.game.other.BackgroundColor;
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
  //private Entity[] ents;
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
  //private Lock conditionLock;
  /**
   * Zmienna warunkowa taka fajna.
   */
  //private Condition condition;
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
   * Najzwyklejszy i jedyny potrzebny konstruktor.
   */
  public World()
  {
    ender=false;
    worldLock=new ReentrantLock();

    //condition=worldLock.newCondition();
    thread=null;
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
      ents=new ArrayList<Entity>();

      // Ustawiamy ilość odświeżeń świata przez jedną sekundę.
      tickrate=DefaultTickrate;

      // Najpierw świat jest zatrzymany, więc ustawiamy jego szybkość na zero.
      speed=0.0;

      // Obliczenie czasu czekania na później.
      //tickNanos=(int)(1000000000/tickrate);
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
  }/*
  void await() throws InterruptedException
  {
    condition.await();
  }
  long awaitNanos(long x) throws InterruptedException
  {
    return condition.awaitNanos(x);
  }*/
  boolean decImmediatelyTicks()
  {
    if(immediatelyTicks>0)
    {
      --immediatelyTicks;
      return true;
    }
    return false;
  }
  public WorldAccess getAccess()
  {
    return new WorldAccess(this);
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
    catch(ClassNotFoundException exc)
    {
      throw new GameException();
    }
    catch(InstantiationException exc)
    {
      throw new GameException();
    }
    catch(IllegalAccessException exc)
    {
      throw new GameException();
    }
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
  void removeAllEntities()
  {
    for(int i=0;i<ents.size();++i)
    {
      ents.get(i).remove();
    }
    updateAll();
  }
  /**
   * Ta funkcja robi jeden krok na świecie, nic skomplikowanego.
   */
  void oneTick()
  {
    //System.out.println("p[ikoiopopikoiopiojoijofd]");
    fixAll();
    computeCollisions();
    doThingsAll();
    //System.out.println("ZXCCZXXCZCXZXCZXCZ");
    updateAll();
    //System.out.println("pierogi");
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
  private void doThingsAll()
  {
    Entity ent;
    //System.out.println("DOTHINGSALL");
    for(int i=0;i<ents.size();++i)
    {
      //System.out.println("-- "+i+" --");
      ent=ents.get(i);
      ent.doThings();
    }
  }
  /**
   */
  private void fixAll()
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
  void updateAll()
  {
    Entity ent;
    for(int i=0;i<ents.size();++i)
    {
      ent=ents.get(i);
      if(ent.isRemoved())
      {
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
  {
    int theEnd1=ents.size()-1;
    int theEnd2=ents.size();
    Entity ent1;
    Entity ent2;
    Collider col1;
    Collider col2;
    Collision collision;
    for(int i=0;i<theEnd1;++i)
    {
      ent1=ents.get(i);
      col1=(Collider)ent1.getComponent("Collider");
      if(col1==null)
        continue;
      for(int j=i+1;j<theEnd2;++j)
      {
        ent2=ents.get(i);
        col2=(Collider)ent2.getComponent("Collider");
        if(col2==null)
          continue;
        // Teraz wiemy, że mamy parę dwóch możliwych do zderzenia obiektów.
        collision=Collision.compute
        (
          col1.getTransformedShape(),
          col2.getTransformedShape()
        );
        if(collision!=null)
        {
          col1.addCollision(ent2,collision);
          col2.addCollision(ent1,collision);
        }
      }
    }
  }

}
