package lotto.generatelotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.lotto.InvalidLottoCountsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private final int lottoCounts;

    public LottoGenerator(int lottoCounts) {
        this.lottoCounts = lottoCounts;
    }

    public void printLottos() {
        System.out.println(lottoCounts + "개를 구매했습니다.");

        List<Lotto> lottos = generateLottos();

        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public List<Lotto> generateLottos() {
        List<Lotto> lottos = new ArrayList<>();

        if (lottoCounts <= 0) {
            throw new InvalidLottoCountsException();
        }
        for(int count = 1; count <= lottoCounts; count++) {
            lottos.add(generateRandomNumbers());
        }

        return lottos;
    }

    public Lotto generateRandomNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        Collections.sort(numbers);

        return new Lotto(numbers);
    }

}
