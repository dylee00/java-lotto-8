package lotto.input;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import lotto.exception.ErrorMessage;

import lotto.io.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InputPurchaseAmountTest extends NsTest {
    private int amountWon;
    @BeforeEach
    void setUp() {
        amountWon = 1000;
    }

    @Test
    void 로또_구매_금액_입력_테스트() {
        assertSimpleTest(() -> {
            run("8000");
            assertThat(output().contains("8"));
        });
    }

    @DisplayName("로또 구매 금액이 적절하지 않으면 예외가 발생한다.")
    @Test
    void 잘못된_금액을_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("1000j", "8000");
            assertThat(output()).contains(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1500", "8000");
            assertThat(output()).contains(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage());
        });
    }

    @Override
    public void runMain() {
        InputView inputView = new InputView(amountWon);
        inputView.inputPurchaseAmount();
    }

}
