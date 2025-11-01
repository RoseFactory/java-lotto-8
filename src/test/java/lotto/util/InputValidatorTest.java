package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @ValueSource(ints = {900, 15342})
    @DisplayName("1000원 단위가 아닌 금액을 입력하면 예외가 발생한다.")
    void should_throwException_when_inputNotDivisibleBy1000(int input) {
        // given = 매개변수

        // when, then
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수를 입력하면 예외가 발생한다.")
    void should_throwException_when_negativeNumberInput() {
        // given
        int input = -1;

        // when, then
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}