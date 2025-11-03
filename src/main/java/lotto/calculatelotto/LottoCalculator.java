package lotto.calculatelotto;

import lotto.WinningNumbersAndBonusNumber;
import lotto.generatelotto.Lotto;
import lotto.generatelotto.LottoRank;

import java.util.*;

public class LottoCalculator {
    private final List<Lotto> lottos;
    private final WinningNumbersAndBonusNumber winningNumbersAndBonusNumber;
    private final int purchaseAmount;

    public LottoCalculator(List<Lotto> lottos, WinningNumbersAndBonusNumber winningNumbersAndBonusNumber, int purchaseAmount) {
        this.lottos = lottos;
        this.winningNumbersAndBonusNumber = winningNumbersAndBonusNumber;
        this.purchaseAmount = purchaseAmount;
    }

    public Map<LottoRank, Integer> calculateWinning() {
        Map<LottoRank, Integer> winningStatistics = new EnumMap<>(LottoRank.class);

        //초기화
        for (LottoRank rank : LottoRank.values()) {
                winningStatistics.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            LottoRank rank = findWinningRank(lotto);
            if (rank != LottoRank.MISS) {
                winningStatistics.put(rank, winningStatistics.get(rank) + 1);
            }
        }

        return winningStatistics;
    }

    public LottoRank findWinningRank(Lotto lotto) {
        List<Integer> winningNumbers = winningNumbersAndBonusNumber.getWinningNumbers();
        int bonusNumber = winningNumbersAndBonusNumber.getBonusNumber();
        List<Integer> lottoNumbers = lotto.getNumbers();

        int matchCount = (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(matchCount, hasBonusNumber);
    }


    public double calculateReturnRate(Map<LottoRank, Integer> statistics) {
        long totalWinning = 0;

        for (Map.Entry<LottoRank, Integer> entry : statistics.entrySet()) {
            LottoRank rank = entry.getKey();
            int count = entry.getValue();
            totalWinning += rank.getPrize() * count;
        }

        // 수익률 = (총 당첨금액 / 구입금액) * 100
        double returnRate = (double) totalWinning / purchaseAmount * 100;
        return Math.round(returnRate * 100) / 100.0;
    }
}
