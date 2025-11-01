package lotto.lotto;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoWinningCheckerTest {

    private final LottoWinningChecker lottoWinningChecker = new LottoWinningChecker();

    @Test
    @DisplayName("8개 구매 후 5등 당첨 1개 결과")
    void should_returnLottoResult_when_checkLotto() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        final int LOTTO_PRICE = 1000;

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 41, 42, 43)), // 5등
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45))
        );

        // when
        LottoResult lottoResult = lottoWinningChecker.checkResult(winningCombination, bonusNumber, lottos, LOTTO_PRICE * lottos.size());

        // then
        assertThat(lottoResult.prizeSpentRatioInPercentWithOneDecimalPlace()).isCloseTo(62.5, Offset.offset(0.1));
        for (Rank rank : Rank.values()) {
            if (rank.equals(Rank.FIFTH)) {
                assertThat(lottoResult.rankCount(rank)).isEqualTo(1);
                continue;
            }

            assertThat(lottoResult.rankCount(rank)).isEqualTo(0);
        }
    }
}