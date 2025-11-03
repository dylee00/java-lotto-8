package lotto.generatelotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.lotto.DuplicateLottoNumbersException;
import lotto.exception.lotto.InvalidLottoCountsException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoGenerateFailureTest{

    @Test
    void 로또_번호의_개수가_6개가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 3, 4, 5, 6)))
                .isInstanceOf(DuplicateLottoNumbersException.class);
    }

    @Test
    void 로또_발급_개수가_양수가_아니라면_예외가_발생한다() {
        assertThatThrownBy(()->{
            int purchaseAmount = -1;
            LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);
        }).isInstanceOf(InvalidLottoCountsException.class);
    }

}
