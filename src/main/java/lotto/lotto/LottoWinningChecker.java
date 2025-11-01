package lotto.lotto;

import java.util.List;

public class LottoWinningChecker {

    public LottoResult checkResult(List<Integer> winningCombination, int bonusNumber, List<Lotto> lottos, int moneySpent) {
        return new LottoResult(winningCombination, bonusNumber, lottos, moneySpent);
    }
}
