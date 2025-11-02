package lotto.input;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.exception.ErrorMessage;
import lotto.io.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class inputWinningAndBonusNumbersTest extends NsTest {
    private int amountWon;
    @BeforeEach
    void setUp() {
        amountWon = 1000;
    }

    @Test
    void 로또_당첨_번호_입력_테스트() {
        assertSimpleTest(() -> {
            run("1,2,3,4,5,6", "7");
            assertThat(output().contains("1,2,3,4,5,6,7"));
        });
    }

    @DisplayName("로또 당첨번호 입력이 적절하지 않으면 예외가 발생한다.")
    @Test
    void 잘못된_로또_번호를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException(" ", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2,3,4,5*6", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2,3,4,5,a", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });
    }

    @Test
    void 잘못된_로또_보너스_번호를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("1,2,3,4,5,6", " ", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2,3,4,5,6", "*", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_TYPE.getMessage());
        });
    }

    @Test
    void 로또_번호에_1보다_작고_45보다_큰_번호를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("0,2,3,4,5,6","1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("46,2,3,4,5,6","1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        });
    }

    @Test
    void 보너스_번호에_1보다_작고_45보다_큰_번호를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("1,2,3,4,5,6","0","1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2,3,4,5,6","46","1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        });
    }

    @Test
    void 중복된_로또_번호를_입력하면_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("1,2,3,4,6,6", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        });

        assertSimpleTest(() -> {
            runException("1,2,3,4,5,6", "6", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ErrorMessage.DUPLICATE_NUMBER.getMessage());
        });
    }


    @Override
    public void runMain() {
        InputView inputView = new InputView(amountWon);
        inputView.inputWinningAndBonusNumbers();
    }

}
