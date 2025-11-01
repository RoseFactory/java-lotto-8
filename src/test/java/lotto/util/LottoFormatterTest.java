package lotto.util;

import lotto.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFormatterTest {

    private final LottoFormatter lottoFormatter = new LottoFormatter();

    @Test
    @DisplayName("로또의 출력 형식과 일치해야 한다.")
    void should_matchFormat_when_formatLotto() {
        // given
        Lotto lotto = new Lotto(List.of(6, 5, 3, 4, 1, 2));

        // when
        String formattedLotto = lottoFormatter.formatLotto(lotto);

        // then
        assertThat(formattedLotto).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}