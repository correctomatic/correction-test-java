import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BananaTest1 {

    @Test
    public void testPeel() {
        Banana banana = new Banana();
        boolean peeled = banana.peel();
        assertTrue(peeled, "Banana should be peeled");
    }

    @Test
    public void testEat() {
        Banana banana = new Banana();
        boolean eaten = banana.eat();
        assertTrue(eaten, "Banana should be eaten");
    }
}
