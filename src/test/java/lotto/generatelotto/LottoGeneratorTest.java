package lotto.generatelotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    public static int purchaseAmount = 5;
    LottoGenerator lottoGenerator = new LottoGenerator(purchaseAmount);
    List<Lotto> lottos = lottoGenerator.generateLottos();

    @Test
    void 생성자로_주입받은_숫자만큼_로또가_발급되는_기능_테스트() {
        assertThat(lottos).hasSize(purchaseAmount);
    }

    @Test
    void 발급된_로또가_6개의_번호를_가지는지_테스트() {
        for(Lotto lotto : lottos) {
            assertThat(lotto.getNumbers()).hasSize(6);
        }
    }

    @Test
    void 발급된_로또의_6개의_번호가_중복되지_않는지_테스트() {
        boolean isDuplicate = false;
        long distinctCount = lottos.stream().map(Lotto::getNumbers).distinct().count();
        if(distinctCount != lottos.size()) {
            isDuplicate = true;
        }
        assertThat(isDuplicate).isFalse();
    }

}
