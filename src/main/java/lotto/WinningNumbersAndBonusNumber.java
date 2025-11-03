package lotto;

import java.util.List;

public class WinningNumbersAndBonusNumber {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbersAndBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
