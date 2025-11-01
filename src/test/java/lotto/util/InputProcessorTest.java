package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputProcessorTest {

    private final InputParser inputParser = new InputParser();
    private final InputValidator inputValidator = new InputValidator();
    private final InputConverter inputConverter = new InputConverter();
    private final InputProcessor inputProcessor = new InputProcessor(inputParser, inputValidator,
        inputConverter);

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "5000, 5",
        "1000, 1"
    })
    @DisplayName("1000원 단위 구입 금액을 입력하면 구입 개수가 반환된다.")
    void should_returnPurchaseQuantity_when_inputDivisibleBy1000(String input, int expected) {
        // given = parameter

        // when
        int purchaseQuantity = inputProcessor.getPurchaseQuantity(input);

        // then
        assertThat(purchaseQuantity).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"900", "15342"})
    @DisplayName("1000원 단위가 아닌 금액을 입력하면 예외가 발생한다.")
    void should_throwException_when_inputNotDivisibleBy1000(String input) {
        // given = parameter

        // when, then
        assertThatThrownBy(() -> inputProcessor.getPurchaseQuantity(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "1.23", "abc"})
    @DisplayName("양의 정수 또는 0이 아닌 값을 입력하면 예외가 발생한다.")
    void should_throwException_when_inputIsInvalid(String input) {
        // given = parameter

        // when
        assertThatThrownBy(() -> inputProcessor.getPurchaseQuantity(input));
    }
}