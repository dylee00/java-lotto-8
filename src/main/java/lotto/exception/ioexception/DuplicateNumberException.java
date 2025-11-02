package lotto.exception.ioexception;

import lotto.exception.ErrorMessage;

public class DuplicateNumberException extends IllegalArgumentException {
    public DuplicateNumberException() {
        super(ErrorMessage.DUPLICATE_NUMBER.getMessage());
    }
}
