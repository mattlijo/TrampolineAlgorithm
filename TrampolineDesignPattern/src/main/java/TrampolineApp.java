



/**
 * <p>Trampoline pattern allows to do tail recursion by just iteration </p>
 * <p>it is good way at times because Java does not optimise tail recursion which can get you 
 * into Stackoverflow exception. </p>
 */

public class TrampolineApp {

  /**
   * Main program for showing pattern. It does loop with factorial function.
   * */
  public static void main(String[] args) {
    System.out.println("start pattern");
    Integer result = recurse(10, 1).result();
    System.out.println("result {} "+ result);

  }

  /**
   * Recursion manager: 
   */
  public static Trampoline<Integer> recurse(int times, int prod) {
    if (times == 0) {
      return Trampoline.done(prod);
    } else {
    	 return Trampoline.more(() -> recurse(times - 1, prod * times));
   /* 	 
    * if you are new to JAVA 8 and having a hard time following the lamda functions.
    * you can go with the below implementation
    * 
      return Trampoline.more(new Trampoline<Trampoline<Integer>>() {

		@Override
		public Trampoline<Integer> get() {
			
			return recurse(times-1,prod*times);
		}
		 
	});
    */
    	

	
    }
  }

}
