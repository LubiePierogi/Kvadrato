package kvadrato.game;

import java.util.Objects;

public interface TriConsumer<T,U,R>
{
  void accept(T t,U u,R r);
  default TriConsumer<T,U,R>
    andThen(TriConsumer<? super T,? super U,? super R>after)
  {
    Objects.requireNonNull(after);
    return (q,w,e)->{accept(q,w,e);after.accept(q,w,e);};
  }

}
