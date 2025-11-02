package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ExceptionHandler;
import lotto.exception.ioexception.InvalidPurchaseAmountException;

public class InputView {
    private final int amountWon;
    private final ExceptionHandler exceptionHandler;

    public InputView(int amountWon) {
        this.amountWon = amountWon;
        this.exceptionHandler = new ExceptionHandler();
    }

    public int inputPurchaseAmount() {
        return exceptionHandler.retry(this::readPurchaseAmount);
    }

    public int readPurchaseAmount() {
        System.out.println("구매 금액을 입력해 주세요.");

        try{
            int purchaseAmount = Integer.parseInt(Console.readLine());
            if (purchaseAmount % amountWon != 0) {
                throw new InvalidPurchaseAmountException(amountWon);
            }
            return purchaseAmount;
        }catch(Exception e){
            throw new InvalidPurchaseAmountException(amountWon);
        }
    }

}
