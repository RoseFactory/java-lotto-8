package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해야 합니다.");
        }
    }

    /**
     *
     * @param input comma-separated numbers in range 1 to 45 with no duplicate (e.g. "1,2,3,4,5,6")
     * @return list of integers of input numbers
     */
    public List<Integer> parseChosenNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 당첨번호는 정수여야 합니다.");
        }
    }


    public int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 정수를 입력해야 합니다.");
        }
    }
}
