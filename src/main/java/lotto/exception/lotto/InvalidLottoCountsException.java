package lotto.exception.lotto;

import lotto.exception.ErrorMessage;

public class InvalidLottoCountsException extends IllegalArgumentException{
    public InvalidLottoCountsException() {
        super(ErrorMessage.INVALID_LOTTO_COUNTS.getMessage());
    }
}
