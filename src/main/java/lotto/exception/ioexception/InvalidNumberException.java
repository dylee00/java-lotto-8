package lotto.exception.ioexception;

import lotto.exception.ErrorMessage;

public class InvalidNumberException extends IllegalArgumentException{
    public InvalidNumberException() {
        super(ErrorMessage.INVALID_NUMBER.getMessage());
    }
}
