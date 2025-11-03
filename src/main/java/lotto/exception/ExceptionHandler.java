package lotto.exception;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ExceptionHandler {
    private static final String ERROR = "[ERROR]";

    public <T> T retry(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            } catch (NoSuchElementException e) {
                System.out.println(ERROR + e.getMessage());
                throw e;
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
    }
}
