package lotto.util;

import java.util.List;

public class InputProcessor {

    private final InputParser inputParser;
    private final InputValidator inputValidator;
    private final InputConverter inputConverter;

    public InputProcessor(InputParser inputParser, InputValidator inputValidator,
        InputConverter inputConverter) {
        this.inputParser = inputParser;
        this.inputValidator = inputValidator;
        this.inputConverter = inputConverter;
    }

    public int getPurchaseQuantity(String input) {
        int purchaseAmount = inputParser.parsePurchaseAmount(input);
        inputValidator.validatePurchaseAmount(purchaseAmount);
        return inputConverter.toPurchaseQuantity(purchaseAmount);
    }

    public List<Integer> chooseWinningCombination(String input) {
        List<Integer> chosenNumbers = inputParser.parseChosenNumbers(input);
        inputValidator.validateWinningCombination(chosenNumbers);
        return chosenNumbers;
    }
}
