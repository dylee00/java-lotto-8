package lotto.io;

import lotto.generatelotto.Lotto;
import lotto.generatelotto.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(int count, List<Lotto> lottos) {
        System.out.println(count + "개를 구매했습니다.");

        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printStatistics(Map<LottoRank, Integer> statistics, double returnRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        printRankStatistics(LottoRank.FIFTH, statistics);
        printRankStatistics(LottoRank.FOURTH, statistics);
        printRankStatistics(LottoRank.THIRD, statistics);
        printRankStatistics(LottoRank.SECOND, statistics);
        printRankStatistics(LottoRank.FIRST, statistics);

        System.out.println("총 수익률은 " + returnRate + "%입니다.");
    }

    private void printRankStatistics(LottoRank rank, Map<LottoRank, Integer> statistics) {
        int count = statistics.getOrDefault(rank, 0);
        System.out.println(rank.getDescription() + " - " + count + "개");
    }
}