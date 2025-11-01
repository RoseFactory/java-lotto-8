package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("1000원 단위가 아닌 구입 금액을 입력하면 예외가 발생한다.")
    void should_throwException_when_inputNotDivisibleBy1000(String input) {
        // given = parameter

        // when, then
        assertThatThrownBy(() -> inputProcessor.getPurchaseQuantity(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되지 않는 1-45까지의 6개 숫자를 입력하면 당첨 번호를 반환한다.")
    void should_returnWinningNumbers_when_validInput() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> chosenCombination = inputProcessor.chooseWinningCombination(input);

        // then
        assertThat(chosenCombination).containsAll(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("당첨 번호에 중복되는 숫자가 있으면 예외를 던진다.")
    void should_throwException_when_duplicateNumber() {
        // given
        String input = "1,1,2,3,4,5";

        // when, then
        assertThatThrownBy(() -> inputProcessor.chooseWinningCombination(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,999", "-1,1,2,3,4,5"})
    @DisplayName("당첨 번호가 1-45까지의 범위의 숫자가 아니면 예외를 던진다.")
    void should_throwException_when_NumberNotFromOneToFortyFive(String input) {
        // given = parameter

        // when, then
        assertThatThrownBy(() -> inputProcessor.chooseWinningCombination(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호와 중복되지 않는 1-45의 보너스 번호를 입력하면 보너스 번호가 반환된다.")
    void should_returnBonusNumber_when_validBonusNumber() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        String input = "7";

        // when
        int bonusNumber = inputProcessor.chooseBonusNumber(winningCombination, input);

        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("당첨 번호와 중복인 보너스 번호를 입력하면 예외를 던진다.")
    void should_throwException_when_bonusNumberInWinningCombination() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        String input = "5";

        // when, then
        assertThatThrownBy(() -> inputProcessor.chooseBonusNumber(winningCombination, input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}