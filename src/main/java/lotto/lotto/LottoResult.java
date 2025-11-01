package lotto.lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LottoResult {

    private final Map<Rank, Integer> rankCountMap = new HashMap<>();
    private final long totalPrizeMoney;
    private final int moneySpent;

    public LottoResult(List<Integer> winningCombination, int bonusNumber, List<Lotto> lottos, int moneySpent) {
        long totalPrizeMoney = 0;

        for (Lotto lotto : lottos) {
            Optional<Rank> rankOptional = computeRank(winningCombination, bonusNumber, lotto);
            if (rankOptional.isPresent()) {
                Rank rank = rankOptional.get();
                totalPrizeMoney += rank.getPrizeMoney();
                rankCountMap.put(rank, rankCountMap.getOrDefault(rank, 0) + 1);
            }
        }

        this.totalPrizeMoney = totalPrizeMoney;
        this.moneySpent = moneySpent;
    }

    // Todo refactor
    private Optional<Rank> computeRank(List<Integer> winningCombination, int bonusNumber, Lotto lotto) {
        List<Integer> chosenNumbers = lotto.getNumbers();
        int winningCombinationMatchCount = countMatch(winningCombination, chosenNumbers);

        if (winningCombinationMatchCount == 6) {
            return Optional.of(Rank.FIRST);
        }

        if (winningCombinationMatchCount == 5 && chosenNumbers.contains(bonusNumber)) {
            return Optional.of(Rank.SECOND);
        }

        if (chosenNumbers.contains(bonusNumber)) {
            winningCombinationMatchCount++;
        }

        if (winningCombinationMatchCount == 5) {
            return Optional.of(Rank.THIRD);
        }

        if (winningCombinationMatchCount == 4) {
            return Optional.of(Rank.FOURTH);
        }

        if (winningCombinationMatchCount == 3) {
            return Optional.of(Rank.FIFTH);
        }

        return Optional.empty();
    }

    private int countMatch(List<Integer> numbers, List<Integer> winningCombination) {
        int count = 0;

        for (int number : numbers) {
            if (winningCombination.contains(number)) {
                count++;
            }
        }

        return count;
    }

    public int rankCount (Rank rank) {
        return rankCountMap.getOrDefault(rank, 0);
    }

    public double prizeSpentRatioInPercentWithOneDecimalPlace() {
        double prizeSpentRatio = (double) totalPrizeMoney / moneySpent * 100;
        return Math.round(prizeSpentRatio * 10) / 10.0;
    }
}
