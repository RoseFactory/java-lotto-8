package lotto.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("1등 결과를 반환한다.")
    void should_returnFirstPrize_whenSixMatch() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        checkRankCountIsOne(Rank.FIRST, lottoResult);
    }

    @Test
    @DisplayName("2등 결과를 반환한다.")
    void should_returnSecondPrize_whenFiveAndBonusMatch() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        checkRankCountIsOne(Rank.SECOND, lottoResult);
    }

    @Test
    @DisplayName("3등 결과를 반환한다.")
    void should_returnThirdPrize_whenFourMatches() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 45));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        checkRankCountIsOne(Rank.THIRD, lottoResult);
    }

    @Test
    @DisplayName("4등 결과를 반환한다.")
    void should_returnFirstPrize_whenSixMatches() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 45, 44));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        checkRankCountIsOne(Rank.FOURTH, lottoResult);
    }

    @Test
    @DisplayName("5등 결과를 반환한다.")
    void should_returnFifthPrize_whenThreeMatch() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 45, 44, 43));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        checkRankCountIsOne(Rank.FIFTH, lottoResult);
    }

    @Test
    @DisplayName("꽝 결과를 반환한다.")
    void should_returnNoPrize_whenLessThanThreeMatch() {
        // given
        List<Integer> winningCombination = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = new Lotto(List.of(1, 2, 45, 44, 43, 42));

        // when
        LottoResult lottoResult = new LottoResult(winningCombination, bonusNumber, List.of(lotto), 1000);

        // then
        assertThat(lottoResult.prizeSpentRatioInPercentWithOneDecimalPlace()).isEqualTo(0);
    }

    private void checkRankCountIsOne(Rank wonRank, LottoResult lottoResult) {
        for (Rank rank : Rank.values()) {
            if (rank.equals(wonRank)) {
                assertThat(lottoResult.rankCount(rank)).isEqualTo(1);
                continue;
            }

            assertThat(lottoResult.rankCount(rank)).isEqualTo(0);
        }
    }
}