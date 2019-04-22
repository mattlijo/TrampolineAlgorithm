

import java.util.stream.Stream;

public interface Trampoline<T> {
  T get();


  /**
   * @return next stage
   */
  default Trampoline<T> jump() {
    return this;
  }

  default T result() {
    return get();
  }

  /**
   * @return true if complete
   */
  default boolean complete() {
    return true;
  }

  /**
   * Created a completed Trampoline
   *
   * @param result Completed result
   * @return Completed Trampoline
   */
  static <T> Trampoline<T> done(final T result) {
    return () -> result;
  }


  /**
   * Create a Trampoline that has more work to do 
   *
   * @param trampoline Next stage in Trampoline
   * @return Trampoline with more work
   */
  static <T> Trampoline<T> more(final Trampoline<Trampoline<T>> trampoline) {
    return new Trampoline<T>() {
      @Override
      public boolean complete() {
        return false;
      }

      @Override
      public Trampoline<T> jump() {
        return trampoline.result();
      }

      @Override
      public T get() {

        return trampoline(this);
      }

      T trampoline(final Trampoline<T> trampoline) {

    	  Trampoline<T> temp=trampoline;
         
    	  while(!temp.complete()) {
    		  
    		  temp=temp.jump();
    	  }
    	  
    	  T t = temp.result();
    	  /*
    
    	   * You can use the below implemantation for using the java 8 Stream api and making the code more 
    	   * readable.
    	   * 
        	T t =Stream.iterate(trampoline, Trampoline :: jump)
            .filter(Trampoline::complete)
            .findFirst()
            .get()
            .result();
	    */
       
    	  return t;
       
      }
    };
  }


}
