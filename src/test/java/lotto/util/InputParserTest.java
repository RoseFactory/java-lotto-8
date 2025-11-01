package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    private final InputParser inputParser = new InputParser();

    @Test
    @DisplayName("정상 입력은 정수 리스트를 반환한다.")
    void should_returnListOfNumbers_when_parseValidChosenNumberString() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> chosenNumbers = inputParser.parseChosenNumbers(input);

        // then
        assertThat(chosenNumbers).containsAll(List.of(1,2,3,4,5,6));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123,a,b,c,2,3", "1.1,2.2,3.3,4,5,6"})
    @DisplayName("정수가 아닌 입력은 예외를 던진다.")
    void should_throwException_when_inputNotInteger(String input) {
        // given = parameter

        // when, then
        assertThatThrownBy(() -> inputParser.parseChosenNumbers(input))
            .isInstanceOf(NumberFormatException.class);
    }
}