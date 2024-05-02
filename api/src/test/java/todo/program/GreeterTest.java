package todo.program;

import static org.junit.Assert.*;
import org.junit.Test;

public class GreeterTest {
    private Greeter greeter = new Greeter();

    @Test
    public void greeterSayingHello() {
        assertEquals("Hello", greeter.sayHello());
    }

    @Test
    public void greeterRespondingToName() {
        String personName = "Person A";
        assertEquals("Hello ".concat(personName), greeter.greetPerson(personName));
    }
}
