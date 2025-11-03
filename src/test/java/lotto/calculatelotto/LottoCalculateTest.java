package lotto.calculatelotto;

import lotto.WinningNumbersAndBonusNumber;
import lotto.generatelotto.Lotto;
import lotto.generatelotto.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoCalculateTest {

    @Test
    @DisplayName("6개 일치 - 1등")
    void 로또_등수_판별_6개일치() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 - 2등")
    void 로또_등수_판별_5개일치_보너스O() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("5개 일치 - 3등")
    void 로또_등수_판별_5개일치_보너스X() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("4개 일치 - 4등")
    void 로또_등수_판별_4개일치() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 10, 11));
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 - 5등")
    void 로또_등수_판별_3개일치() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 - 낙첨")
    void 로또_등수_판별_낙첨() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        Lotto lotto = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // 0개 일치
        List<Lotto> lottos = List.of(lotto);

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        LottoRank rank = calculator.findWinningRank(lotto);

        // then
        assertThat(rank).isEqualTo(LottoRank.MISS);
    }

    @Test
    @DisplayName("당첨 통계 계산")
    void 로또_당첨통계_여러등수() {
        // given
        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),  // 3개 일치 -> 5등
                new Lotto(List.of(1, 2, 3, 4, 11, 12)),   // 4개 일치 -> 4등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 낙첨
        );

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 3000);

        // when
        Map<LottoRank, Integer> statistics = calculator.calculateWinning();

        // then
        assertThat(statistics.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(statistics.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(statistics.get(LottoRank.THIRD)).isEqualTo(0);
        assertThat(statistics.get(LottoRank.SECOND)).isEqualTo(0);
        assertThat(statistics.get(LottoRank.FIRST)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익률 계산 - 5등 1개")
    void 로또_수익률_5등1개() {
        // given
        Map<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);
        statistics.put(LottoRank.FIFTH, 1);
        statistics.put(LottoRank.FOURTH, 0);
        statistics.put(LottoRank.THIRD, 0);
        statistics.put(LottoRank.SECOND, 0);
        statistics.put(LottoRank.FIRST, 0);

        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 10, 11, 12)));

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 3000);

        // when
        double returnRate = calculator.calculateReturnRate(statistics);

        // then
        assertThat(returnRate).isEqualTo(166.67);
    }

    @Test
    @DisplayName("수익률 계산 - 4등 1개, 5등 2개")
    void 로또_수익률_여러등수() {
        // given
        Map<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);
        statistics.put(LottoRank.FIFTH, 2);
        statistics.put(LottoRank.FOURTH, 1);
        statistics.put(LottoRank.THIRD, 0);
        statistics.put(LottoRank.SECOND, 0);
        statistics.put(LottoRank.FIRST, 0);

        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11))
        );

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 3000);

        // when
        double returnRate = calculator.calculateReturnRate(statistics);

        // then
        assertThat(returnRate).isEqualTo(2000.0);
    }

    @Test
    @DisplayName("수익률 계산 - 모두 낙첨")
    void 로또_수익률_모두낙첨() {
        // given
        Map<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);
        statistics.put(LottoRank.FIFTH, 0);
        statistics.put(LottoRank.FOURTH, 0);
        statistics.put(LottoRank.THIRD, 0);
        statistics.put(LottoRank.SECOND, 0);
        statistics.put(LottoRank.FIRST, 0);

        WinningNumbersAndBonusNumber winning = new WinningNumbersAndBonusNumber(
                List.of(1, 2, 3, 4, 5, 6), 7
        );
        List<Lotto> lottos = List.of(new Lotto(List.of(10, 11, 12, 13, 14, 15)));

        LottoCalculator calculator = new LottoCalculator(lottos, winning, 1000);

        // when
        double returnRate = calculator.calculateReturnRate(statistics);

        // then
        assertThat(returnRate).isEqualTo(0.0);
    }
}
