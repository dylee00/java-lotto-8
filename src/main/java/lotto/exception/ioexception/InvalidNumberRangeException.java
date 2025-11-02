package lotto.exception.ioexception;

import lotto.exception.ErrorMessage;

public class InvalidNumberRangeException extends IllegalArgumentException {
    public InvalidNumberRangeException() {
        super(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
    }
}
