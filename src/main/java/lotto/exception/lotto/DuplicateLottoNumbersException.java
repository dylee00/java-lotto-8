package lotto.exception.lotto;

import lotto.exception.ErrorMessage;

public class DuplicateLottoNumbersException extends IllegalArgumentException {
    public DuplicateLottoNumbersException() {
        super(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
    }
}
