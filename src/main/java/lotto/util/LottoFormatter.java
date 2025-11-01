package lotto.util;

import lotto.lotto.Lotto;

import java.util.List;

public class LottoFormatter {

    public String formatLotto(Lotto lotto) {
        List<Integer> sortedNumbers = lotto.getNumbers().stream().sorted().toList();

        return sortedNumbers.toString();
    }
}
