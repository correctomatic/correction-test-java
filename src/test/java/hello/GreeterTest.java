package main.java.hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
// import static org.junit.jupiter.api.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
// import org.junit.Test;
// import org.junit.Description;
// import org.junit.DisplayName;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Description;
// import org.junit.api.DisplayName;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import static org.junit.api.Assert.*;

import main.java.hello.Greeter;

public class GreeterTest {

  private Greeter greeter = new Greeter();

  // @Test
  // public void greeterSaysHello() {
  //   assertThat(greeter.sayHello(), containsString("Hello World"));
  // }

  @Test
  @DisplayName("Banana!!!!")
  // @Description("The description of Banana!!!!")
  // MUST start by test
  public void testFailedTest() {
    assertEquals(2+2, 5);
  }
}
