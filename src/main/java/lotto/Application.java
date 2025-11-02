package lotto;

import lotto.generatelotto.LottoGenerator;
import lotto.io.InputView;

public class Application {
    private static int AMOUNT = 1000;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView(AMOUNT);

        int purchaseAmount = inputView.inputPurchaseAmount();

        LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);

        lottoGenerator.printLottos();

        inputView.inputWinningAndBonusNumbers();

    }
}
