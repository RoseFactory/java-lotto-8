package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public int parsePurchaseAmount(String input) {
        return Integer.parseInt(input);
    }

    /**
     *
     * @param input comma-separated numbers in range 1 to 45 with no duplicate (e.g. "1,2,3,4,5,6")
     * @return list of integers of input numbers
     */
    public List<Integer> parseChosenNumbers(String input) {
        return Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
    }
}
