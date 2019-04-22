

//import org.junit.Test;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test for trampoline pattern.
 * */
public class TrampolineAppTest {


  @Test
  public void testTrampolineWithFactorialFunction() {
    int result = TrampolineApp.recurse(10, 1).result();
    assertEquals("is equal?", 3628800, result);
  }

}