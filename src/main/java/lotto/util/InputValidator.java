package lotto.util;

public class InputValidator {

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 0 || purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }
}
