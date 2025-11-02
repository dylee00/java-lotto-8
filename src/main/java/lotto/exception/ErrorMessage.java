package lotto.exception;

public enum ErrorMessage {

    INVALID_PURCHASE_UNIT("적절한 금액을 다시 입력해 주세요. "),
    INVALID_NUMBER("적절한 숫자를 다시 입력해 주세요");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
