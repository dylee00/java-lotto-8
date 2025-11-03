package lotto.controller;

import lotto.WinningNumbersAndBonusNumber;
import lotto.calculatelotto.LottoCalculator;
import lotto.generatelotto.Lotto;
import lotto.generatelotto.LottoGenerator;
import lotto.generatelotto.LottoRank;
import lotto.io.InputView;
import lotto.io.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView(LOTTO_PRICE);
        this.outputView = new OutputView();
    }

    public void run() {
        int purchaseCount = inputView.inputPurchaseAmount();
        List<Lotto> lottos = generateLottos(purchaseCount);

        outputView.printPurchasedLottos(purchaseCount, lottos);

        WinningNumbersAndBonusNumber winningNumbersAndBonusNumber = inputView.inputWinningAndBonusNumbers();

        int purchaseAmount = purchaseCount * LOTTO_PRICE;
        LottoCalculator lottoCalculator = new LottoCalculator(lottos, winningNumbersAndBonusNumber, purchaseAmount);

        Map<LottoRank, Integer> statistics = lottoCalculator.calculateWinning();
        double returnRate = lottoCalculator.calculateReturnRate(statistics);

        outputView.printStatistics(statistics, returnRate);
    }

    private List<Lotto> generateLottos(int purchaseCount) {
        LottoGenerator lottoGenerator = new LottoGenerator(purchaseCount);
        return lottoGenerator.generateLottos();
    }
}