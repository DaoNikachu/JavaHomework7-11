import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {

    @Test
    public void testFactorial() {
        Factorial factorial = new Factorial();
        Assert.assertEquals(factorial.factorial(5), 120L);
    }
    @Test
    public void testFactorialLessThanZero() {
        Factorial factorial = new Factorial();
        Exception e = Assert.expectThrows(IllegalArgumentException.class, () -> factorial.factorial(-1));
        Assert.assertEquals(e.getMessage(), "Факторил числа, меньшего, чем ноль, недопустим");
    }
}