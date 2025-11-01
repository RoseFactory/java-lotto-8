package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @ValueSource(ints = {900, 15342})
    @DisplayName("구입 금액으로 1000원 단위가 아닌 금액을 입력하면 예외가 발생한다.")
    void should_throwException_when_inputNotDivisibleBy1000(int input) {
        // given = 매개변수

        // when, then
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액으로 음수를 입력하면 예외가 발생한다.")
    void should_throwException_when_negativeNumberInput() {
        // given
        int input = -1;

        // when, then
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1-45의 6개를 당첨 번호로 입력하면 예외가 발생하지 않는다.")
    void should_doNothing_when_validWinningCombinationChosen() {
        // given
        List<Integer> input = List.of(1, 4, 23, 22, 21, 35);

        // when
        inputValidator.validateWinningCombination(input);

        // then
    }

    @Test
    @DisplayName("중복인 번호가 있으면 예외를 던진다.")
    void should_throwException_when_duplicate() {
        // given
        List<Integer> input = List.of(1,1,2,3,4,5);

        // when, then
        assertThatThrownBy(() -> inputValidator.validateWinningCombination(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1-45가 아닌 번호가 포함되면 예외를 던진다.")
    void should_throwException_when_numberNotInRange() {
        // given
        List<Integer> containNegative = List.of(-1, 2, 3, 4, 5, 6);
        List<Integer> containBiggerThanFortyFive = List.of(1, 2, 3, 4, 5, 46);

        // when, then
        assertThatThrownBy(() -> inputValidator.validateWinningCombination(containNegative))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.validateWinningCombination(containBiggerThanFortyFive))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 개수가 6개가 아니면 예외를 던진다")
    void should_throwException_when_inputSizeIsNotSix() {
        // given
        List<Integer> fiveIntegers = List.of(1,2,3,4,5);
        List<Integer> sevenIntegers = List.of(1,2,3,4,5,6,7);

        // when, then
        assertThatThrownBy(() -> inputValidator.validateWinningCombination(fiveIntegers))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.validateWinningCombination(sevenIntegers))
            .isInstanceOf(IllegalArgumentException.class);
    }
}