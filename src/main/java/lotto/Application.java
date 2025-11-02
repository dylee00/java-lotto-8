package lotto;

import lotto.io.InputView;

public class Application {
    private static int AMOUNT;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //로또 금액 단위
        AMOUNT = 1000;

        InputView inputView = new InputView(AMOUNT);
        int purchaseAmount = inputView.inputPurchaseAmount();

        inputView.inputWinningAndBonusNumbers();

    }
}
