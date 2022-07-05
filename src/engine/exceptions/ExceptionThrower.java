package engine.exceptions;

public class ExceptionThrower {
    public static void throwException(Exception exception) {
        try {
            throw exception; // run instantiate() first
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
