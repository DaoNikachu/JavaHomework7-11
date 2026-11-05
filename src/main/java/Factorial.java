public class Factorial {

    public long factorial(int a) {
        long result = 1;
        if (a < 0) { throw new IllegalArgumentException("Факторил числа, меньшего, чем ноль, недопустим"); }
            for (int i = 1; i <= a; i++) {
                result *= i;
            }
            return result;
        }
    }
