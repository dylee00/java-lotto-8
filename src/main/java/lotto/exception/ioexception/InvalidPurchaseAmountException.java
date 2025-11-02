package lotto.exception.ioexception;

import lotto.exception.ErrorMessage;

public class InvalidPurchaseAmountException extends IllegalArgumentException {
    public InvalidPurchaseAmountException(int amountUnit) {
        super(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage() + "단위는 " + amountUnit + " 입니다.");
    }
}
