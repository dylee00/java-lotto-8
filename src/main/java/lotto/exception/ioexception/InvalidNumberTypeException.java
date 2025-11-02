package lotto.exception.ioexception;

import lotto.exception.ErrorMessage;

public class InvalidNumberTypeException extends IllegalArgumentException{
    public InvalidNumberTypeException() {
        super(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
    }
}
