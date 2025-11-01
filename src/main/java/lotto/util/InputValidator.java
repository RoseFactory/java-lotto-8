package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class InputValidator {

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 0 || purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public void validateWinningCombination(List<Integer> numbers) {
        requireSizeOfSix(numbers);
        requireNoDuplicate(numbers);
        requireNumbersInRangeOneToFortyFive(numbers);
    }

    private void requireSizeOfSix(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void requireNoDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void requireNumbersInRangeOneToFortyFive(List<Integer> numbers) {
        Optional<Integer> optionalNumberNotInRange = numbers.stream()
                                                            .filter(n -> !(n >= 1 && n <= 45))
                                                            .findFirst();
        if (optionalNumberNotInRange.isPresent()) {
            throw new IllegalArgumentException();
        }
    }
}
