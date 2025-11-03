package lotto;

import lotto.calculatelotto.LottoCalculator;
import lotto.generatelotto.Lotto;
import lotto.generatelotto.LottoGenerator;
import lotto.io.InputView;

import java.util.List;

public class Application {
    private static int AMOUNT = 1000;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView(AMOUNT);

        int purchaseAmount = inputView.inputPurchaseAmount();

        LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);

        List<Lotto> lottos = lottoGenerator.generateLottos();

        lottoGenerator.printLottos();

        WinningNumbersAndBonusNumber winningNumbersAndBonusNumber = inputView.inputWinningAndBonusNumbers();

        LottoCalculator lottoCalculator = new LottoCalculator(lottos,winningNumbersAndBonusNumber,purchaseAmount);

    }
}
