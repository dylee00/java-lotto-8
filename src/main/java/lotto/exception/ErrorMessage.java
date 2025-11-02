package lotto.exception;

public enum ErrorMessage {

    INVALID_PURCHASE_UNIT("적절한 금액을 다시 입력해 주세요. "),
    INVALID_NUMBER_TYPE("적절한 숫자의 타입을 다시 입력해 주세요."),
    INVALID_NUMBER_RANGE("적절한 숫자의 범위를 다시 입력해 주세요."),
    DUPLICATE_NUMBER("중복된 숫자를 입력하면 안됩니다. 적절한 숫자를 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
