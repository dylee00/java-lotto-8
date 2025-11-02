package lotto.exception;

import java.util.function.Supplier;

public class ExceptionHandler {
    private static final String ERROR = "[ERROR]";

    public <T> T retry(Supplier<T> callback) {
        while (true) {
            try {
                return callback.get();
            }catch (Exception e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
    }
}
