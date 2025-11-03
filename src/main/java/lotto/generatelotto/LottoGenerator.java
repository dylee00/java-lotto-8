package lotto.generatelotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.lotto.InvalidLottoCountsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private final int lottoCounts;

    public LottoGenerator(int lottoCounts) {
        validate(lottoCounts);
        this.lottoCounts = lottoCounts;
    }

    public void validate(int lottoCounts){
        if (lottoCounts < 1) {
            throw new InvalidLottoCountsException();
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
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));

        Collections.sort(numbers);

        return new Lotto(numbers);
    }

}
