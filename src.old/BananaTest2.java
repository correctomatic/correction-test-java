import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BananaTest2 {

    @Test
    public void testIsRipe() {
        Banana banana = new Banana();
        banana.ripen();
        assertTrue(banana.isRipe(), "Banana should be ripe after ripening");
    }

    @Test
    public void testSpoil() {
        Banana banana = new Banana();
        banana.ripen();
        banana.spoil();
        assertFalse(banana.isRipe(), "Banana should spoil after ripening");
    }
}
