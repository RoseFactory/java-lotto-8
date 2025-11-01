package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.lotto.*;
import lotto.util.InputConverter;
import lotto.util.InputParser;
import lotto.util.InputProcessor;
import lotto.util.InputValidator;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        InputValidator inputValidator = new InputValidator();
        InputConverter inputConverter = new InputConverter();
        InputProcessor inputProcessor = new InputProcessor(inputParser, inputValidator, inputConverter);

        LottoGenerator lottoGenerator = new LottoGenerator();

        LottoWinningChecker lottoWinningChecker = new LottoWinningChecker();

        int purchaseQuantity;
        System.out.println("구입금액을 입력해주세요.");
        while (true) {
            String input = Console.readLine();
            try {
                purchaseQuantity = inputProcessor.getPurchaseQuantity(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Lotto> lottos = lottoGenerator.issue(purchaseQuantity);
        System.out.println(purchaseQuantity + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }

        System.out.println("당첨 번호를 입력해주세요.");
        List<Integer> winningCombination;
        while (true) {
            String input = Console.readLine();
            try {
                winningCombination = inputProcessor.chooseWinningCombination(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("보너스 번호를 입력해주세요.");
        int bonusNumber;
        while (true) {
            String input = Console.readLine();
            try {
                bonusNumber = inputProcessor.chooseBonusNumber(winningCombination, input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("당첨통계");
        System.out.println("---");
        LottoResult result = lottoWinningChecker.checkResult(winningCombination, bonusNumber, lottos, lottos.size() * 1000);
        for (Rank rank : Rank.values()) {
            System.out.println(rank.getInfo() + " - " + result.rankCount(rank) + "개");
        }
        System.out.println("총 수익률은 " + result.prizeSpentRatioInPercentWithOneDecimalPlace() + "%입니다.");
    }
}
